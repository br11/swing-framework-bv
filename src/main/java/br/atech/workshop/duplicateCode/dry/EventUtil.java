package br.atech.workshop.duplicateCode.dry;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;

import br.atech.workshop.duplicateCode.gui.Gui;

/**
 * Instrumenta as telas para adição do comportamento padrão.<br/>
 * Tags: Código sujo. Faz a mágica. Refletion.
 * 
 * @author marcio
 * 
 * @param <T>
 */
public class EventUtil<T extends Gui<?>> {

	private Map<Object, Set<Method>> methods = new HashMap<Object, Set<Method>>();

	private final T gui;

	private GenericEventListener<T> actionListener;

	private boolean active;

	/**
	 * 
	 * @param gui
	 * @param actionListener
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public EventUtil(T gui, GenericEventListener<T> actionListener)
			throws NoSuchMethodException, SecurityException,
			IllegalArgumentException, IllegalAccessException {

		this.gui = gui;
		this.setActionListener(actionListener);
	}

	/**
	 * 
	 */
	public void activate() {
		if (!isActive()) {
			try {
				addListener();
			} catch (NoSuchMethodException | SecurityException
					| IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 
	 */
	public void deactivate() {
		try {
			removeListener();
		} catch (NoSuchMethodException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private void addListener() throws NoSuchMethodException, SecurityException,
			IllegalArgumentException, IllegalAccessException {

		Class<?> guiType = getGui().getClass();
		Class<?> controllerType = getGui().getController().getClass();

		Class<?> type = guiType;
		while (type != null && !type.equals(Object.class)) {
			for (Field field : type.getDeclaredFields()) {
				if (JComponent.class.isAssignableFrom(field.getType())) {
					field.setAccessible(true);
					JComponent component = (JComponent) field.get(gui);

					if (component instanceof JButton) {
						findActionMethod(guiType, field, component);
						findActionMethod(controllerType, field, component);
					} else if (component instanceof JTextField) {
						findChangeMethod(guiType, field, component);
						findChangeMethod(controllerType, field, component);
					}
				}
			}
			type = type.getSuperclass();
		}

		active = true;
	}

	/**
	 * 
	 * @param controllerType
	 * @param field
	 * @param component
	 * @throws NoSuchMethodException
	 */
	protected void findChangeMethod(Class<?> controllerType, Field field,
			JComponent component) throws NoSuchMethodException {
		if (findEventMethod(controllerType,
				((JTextField) component).getDocument(), field.getName()
						+ "OnChange", DocumentEvent.class)
				|| findEventMethod(controllerType,
						((JTextField) component).getDocument(), "anyOnChange",
						DocumentEvent.class)) {
			((JTextField) component).getDocument().addDocumentListener(
					getActionListener());
		}
	}

	/**
	 * 
	 * @param controllerType
	 * @param field
	 * @param component
	 * @throws NoSuchMethodException
	 */
	protected void findActionMethod(Class<?> controllerType, Field field,
			JComponent component) throws NoSuchMethodException {
		if (findEventMethod(controllerType, component, field.getName()
				+ "OnClick", ActionEvent.class)
				|| findEventMethod(controllerType, component, "anyOnClick",
						ActionEvent.class)) {
			((JButton) component).addActionListener(getActionListener());
		}
	}

	/**
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private void removeListener() throws NoSuchMethodException,
			SecurityException, IllegalArgumentException, IllegalAccessException {

		Class<?> guiType = getGui().getClass();
		Class<?> type = guiType;
		while (type != null && !type.equals(Object.class)) {
			for (Field field : type.getDeclaredFields()) {
				if (JComponent.class.isAssignableFrom(field.getType())) {
					field.setAccessible(true);
					JComponent component = (JComponent) field.get(gui);

					if (component instanceof JButton) {
						((JButton) component)
								.removeActionListener(getActionListener());
					} else if (component instanceof JTextField) {
						((JTextField) component).getDocument()
								.removeDocumentListener(getActionListener());
					}
				}
			}
			type = type.getSuperclass();
		}

		active = false;
	}

	protected boolean findEventMethod(Class<?> controllerType, Object source,
			String command, Class<?>... eventType) throws NoSuchMethodException {
		Method method = null;

		Class<?> type = controllerType;
		while (type != null && !type.equals(Object.class) && method == null) {
			try {
				// System.out.println(String.format("[%s] // [%s]",
				// type.getName(), command));
				method = type.getDeclaredMethod(command, eventType);
				method.setAccessible(true);

				if (!methods.containsKey(source)) {
					methods.put(source, new LinkedHashSet<Method>());
				}
				methods.get(source).add(method);
				break;
			} catch (NoSuchMethodException e) {
			}
			type = type.getSuperclass();
		}

		return method != null;
	}

	/**
	 * 
	 * @param e
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public Object execute(ActionEvent e) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		return execute((JComponent) e.getSource(), e);
	}

	/**
	 * 
	 * @param e
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public Object execute(DocumentEvent e) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		return execute(e.getDocument(), e);
	}

	/**
	 * 
	 * @param command
	 * @param param
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private Object execute(Object source, Object param)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		if (methods.containsKey(source)) {
			Object result = null;
			for (Method method : methods.get(source)) {
				if (method.getParameterTypes().length == 1) {
					if (Gui.class.isAssignableFrom(method.getDeclaringClass())) {
						result = method.invoke(getGui(), param);
					} else {
						result = method.invoke(getGui().getController(), param);
					}
				} else {
					if (Gui.class.isAssignableFrom(method.getDeclaringClass())) {
						result = method.invoke(getGui());
					} else {
						result = method.invoke(getGui().getController());
					}
				}
			}
			return result;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public T getGui() {
		return gui;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @return the actionListener
	 */
	public GenericEventListener<T> getActionListener() {
		return actionListener;
	}

	/**
	 * @param actionListener
	 *            the actionListener to set
	 */
	public void setActionListener(GenericEventListener<T> actionListener) {
		this.actionListener = actionListener;
	}
}
