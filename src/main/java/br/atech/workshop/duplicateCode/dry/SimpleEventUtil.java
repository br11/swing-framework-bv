/**
 * 
 */
package br.atech.workshop.duplicateCode.dry;

import java.awt.event.ActionEvent;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import br.atech.workshop.duplicateCode.binding.Binding;
import br.atech.workshop.duplicateCode.gui.Gui;

/**
 * @author marcio
 * 
 */
public class SimpleEventUtil<T extends Gui<?>> extends EventUtil<T> {

	/**
	 * 
	 * @param gui
	 * @param actionListener
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public SimpleEventUtil(T gui, GenericEventListener<T> actionListener)
			throws NoSuchMethodException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		super(gui, actionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.atech.workshop.duplicateCode.dry.EventUtil#findActionMethod(java.lang
	 * .Class, java.lang.reflect.Field, javax.swing.JComponent)
	 */
	@Override
	protected void findActionMethod(Class<?> controllerType, Field field,
			JComponent component) throws NoSuchMethodException {
		if (findEventMethod(controllerType, component, field.getName()
				+ "OnClick", ActionEvent.class)
				|| findEventMethod(controllerType, component, field.getName(),
						new Class[0])) {
			((JButton) component).addActionListener(getActionListener());
		} else {
			super.findActionMethod(controllerType, field, component);
		}
	}

	@Override
	protected void findChangeMethod(Class<?> controllerType, Field field,
			JComponent component) throws NoSuchMethodException {
		if (findEventMethod(controllerType,
				((JTextField) component).getDocument(), field.getName()
						+ "OnChange", new Class[0])) {
			((JTextField) component).getDocument().addDocumentListener(
					getActionListener());
		} else {
			super.findChangeMethod(controllerType, field, component);
		}
	}

	public static Map<String, Object> getComponentValues(Gui<?> gui) {
		try {
			Map<String, Object> values = new HashMap<>();

			Class<?> type = gui.getClass();

			while (!type.equals(Object.class)) {

				for (Field field : type.getDeclaredFields()) {
					field.setAccessible(true);
					if (field.get(gui) instanceof JTextComponent
							|| field.get(gui) instanceof JComboBox<?>) {
						Object value = getComponentValue(field.get(gui));
						String property = getComponentMapping(field);
						values.put(property, value);
					}
				}

				type = type.getSuperclass();
			}

			return values;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static Map<String, JComponent> getComponents(Gui<?> gui) {
		try {
			Map<String, JComponent> components = new HashMap<>();

			Class<?> type = gui.getClass();

			while (!type.equals(Object.class)) {

				for (Field field : type.getDeclaredFields()) {
					field.setAccessible(true);
					if (field.get(gui) instanceof JComponent) {
						JComponent component = (JComponent) field.get(gui);
						String property = getComponentMapping(field);
						components.put(property, component);
					}

				}

				type = type.getSuperclass();
			}

			return components;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static Object getComponentValue(Object valueOrComponent) {
		Object value = valueOrComponent;

		if (value instanceof JTextComponent) {
			value = ((JTextComponent) value).getText();
		} else if (value instanceof JComboBox<?>) {
			value = ((JComboBox<?>) value).getModel().getSelectedItem();
		} else if (value instanceof ButtonGroup) {
			value = ((ButtonGroup) value).getSelection().getSelectedObjects();
		}
		return value;
	}

	public static String getComponentMapping(Field field) {
		if (field.getAnnotation(Binding.class) != null
				&& !field.getAnnotation(Binding.class).value().isEmpty()) {
			return field.getAnnotation(Binding.class).value();
		} else {
			return field.getName().replaceAll("_", ".");
		}
	}

	public static void readInput(Gui<?> gui) throws IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		Map<String, Object> componentValues = SimpleEventUtil
				.getComponentValues(gui);

		PropertyDescriptor[] propDescs = Introspector.getBeanInfo(
				gui.getController().getModel().getClass())
				.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : propDescs) {

			if (propertyDescriptor.getWriteMethod() != null
					&& componentValues
							.containsKey(propertyDescriptor.getName())) {
				Object value = componentValues
						.get(propertyDescriptor.getName());

				if (value != null && value.toString().trim().isEmpty()) {
					value = null;
				}

				if (value != null) {
					if (propertyDescriptor.getPropertyType().equals(
							Integer.class)) {
						value = new Integer(value.toString());
					} else if (propertyDescriptor.getPropertyType().equals(
							Double.class)) {
						value = new Double(value.toString());
					}
				}

				if (differ(
						propertyDescriptor.getReadMethod().invoke(
								gui.getController().getModel()), value)) {
					propertyDescriptor.getWriteMethod().invoke(
							gui.getController().getModel(), value);
				}
			}
		}
	}

	public static void writeOutput(Gui<?> gui) throws IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		Map<String, JComponent> components = SimpleEventUtil.getComponents(gui);

		PropertyDescriptor[] propDescs = Introspector.getBeanInfo(
				gui.getController().getModel().getClass())
				.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : propDescs) {

			if (propertyDescriptor.getReadMethod() != null
					&& components.containsKey(propertyDescriptor.getName())) {
				Object value = propertyDescriptor.getReadMethod().invoke(
						gui.getController().getModel());
				if (value != null) {
					if (!propertyDescriptor.getPropertyType().equals(
							String.class)) {
						value = value.toString();
					}
				} else {
					value = "";
				}

				JComponent component = components.get(propertyDescriptor
						.getName());

				if (component instanceof JTextComponent) {
					if (differ(((JTextComponent) component).getText(), value)) {
						((JTextComponent) component).setText(value.toString());
					}
				} else if (component instanceof JComboBox<?>) {
					if (differ(((JComboBox<?>) component).getSelectedItem(),
							value)) {
						((JComboBox<?>) component).setSelectedItem(value
								.toString());
					}
				} else if (component instanceof JLabel) {
					if (differ(((JLabel) component).getText(), value)) {
						((JLabel) component).setText(value.toString());
					}
				}

			}
		}
	}

	public static boolean differ(Object val1, Object val2) {
		String str1 = val1 == null ? "" : val1.toString().trim();
		String str2 = val2 == null ? "" : val2.toString().trim();
		return !str1.equals(str2);
	}
}
