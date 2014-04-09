package br.atech.workshop.duplicateCode.dry;

import java.awt.event.ActionEvent;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;
import javax.validation.ConstraintViolationException;

import br.atech.workshop.duplicateCode.dry.listeners.RequestListener;
import br.atech.workshop.duplicateCode.dry.listeners.ResponseListener;
import br.atech.workshop.duplicateCode.dry.listeners.ValidationListener;
import br.atech.workshop.duplicateCode.gui.Controller;
import br.atech.workshop.duplicateCode.validation.GuiValidator;

/**
 * Tela com comportamento mais especializado. Programação do tipo
 * "Crianças, não tentem fazer isso em casa".
 * 
 * @author marcio
 * 
 */
public abstract class SimpleGui<T> extends AdvancedGui<T> implements
		RequestListener, ResponseListener, ValidationListener {

	private T model;

	private GuiValidator validator = new GuiValidator();

	/**
	 * 
	 * @param controller
	 */
	public SimpleGui() {
		this(null);
	}

	/**
	 * 
	 * @param controller
	 */
	public SimpleGui(Controller<T> controller) {
		super(controller);

		setActionListener(new SimpleEventListener<>(this,
				new AdvancedExceptionHandler(this)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.atech.workshop.duplicateCode.dry.ExtensibleGui#getActionListener()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public SimpleEventListener getActionListener() {
		return (SimpleEventListener) super.getActionListener();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.duplicateCode.dry.ExtensibleGui#beforeHide()
	 */
	@Override
	protected void beforeHide() {
		if (!confirm("Deseja realmente encerrar?")) {
			abort();
		}

		super.beforeHide();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.duplicateCode.gui.Controller#getModel()
	 */
	@Override
	public T getModel() {
		if (getController() == this) {
			return model;
		} else {
			return getController().getModel();
		}
	}

	public void setModel(T model) {
		if (getController() == this) {
			this.model = model;
		} else {
			getController().setModel(model);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.atech.workshop.duplicateCode.dry.ValidationListener#onValidate(java
	 * .awt.event.ActionEvent)
	 */
	@Override
	public void onValidate(ActionEvent event)
			throws ConstraintViolationException {
		validator.assertValid(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.atech.workshop.duplicateCode.dry.RequestListener#onRequest(java.awt
	 * .event.ActionEvent)
	 */
	@Override
	public void onRequest(ActionEvent event) throws Exception {
		readInput();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.atech.workshop.duplicateCode.dry.listeners.ResponseListener#onResponse
	 * (java.awt.event.ActionEvent)
	 */
	@Override
	public void onResponse(ActionEvent event) throws Exception {
		writeOutput();
	}

	private void readInput() throws IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		Map<String, Object> componentValues = SimpleEventUtil
				.getComponentValues(this);

		PropertyDescriptor[] propDescs = Introspector.getBeanInfo(
				getController().getModel().getClass()).getPropertyDescriptors();
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
								getController().getModel()), value)) {
					propertyDescriptor.getWriteMethod().invoke(
							getController().getModel(), value);
				}
			}
		}
	}

	private void writeOutput() throws IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		Map<String, JComponent> components = SimpleEventUtil
				.getComponents(this);

		PropertyDescriptor[] propDescs = Introspector.getBeanInfo(
				getController().getModel().getClass()).getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : propDescs) {

			if (propertyDescriptor.getReadMethod() != null
					&& components.containsKey(propertyDescriptor.getName())) {
				Object value = propertyDescriptor.getReadMethod().invoke(
						getController().getModel());
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

	boolean differ(Object val1, Object val2) {
		String str1 = val1 == null ? "" : val1.toString().trim();
		String str2 = val2 == null ? "" : val2.toString().trim();
		return !str1.equals(str2);
	}

}