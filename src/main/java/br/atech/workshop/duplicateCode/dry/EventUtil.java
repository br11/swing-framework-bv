package br.atech.workshop.duplicateCode.dry;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
public class EventUtil<T extends Gui> {

	private Map<Object, Method> methods = new HashMap<Object, Method>();

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
		this.actionListener = actionListener;
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

		Class<?> guiType = getGui().getController().getClass();
		Class<?> type = guiType;
		while (type != null && !type.equals(Object.class)) {
			for (Field field : type.getDeclaredFields()) {
				if (JComponent.class.isAssignableFrom(field.getType())) {
					field.setAccessible(true);
					JComponent component = (JComponent) field.get(gui);

					if (component instanceof JButton) {
						if (findEventMethod(guiType, component,
								field.getName() + "OnClick", ActionEvent.class)
								|| findEventMethod(guiType, component,
										"anyOnClick", ActionEvent.class)) {
							((JButton) component)
									.addActionListener(actionListener);
						}
					} else if (component instanceof JTextField) {
						if (findEventMethod(guiType,
								((JTextField) component).getDocument(),
								field.getName() + "OnChange",
								DocumentEvent.class)
								|| findEventMethod(guiType,
										((JTextField) component).getDocument(),
										"anyOnChange", DocumentEvent.class)) {
							((JTextField) component).getDocument()
									.addDocumentListener(actionListener);
						}
					}
				}
			}
			type = type.getSuperclass();
		}

		active = true;
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
								.removeActionListener(actionListener);
					} else if (component instanceof JTextField) {
						((JTextField) component).getDocument()
								.removeDocumentListener(actionListener);
					}
				}
			}
			type = type.getSuperclass();
		}

		active = false;
	}

	private boolean findEventMethod(Class<?> guiType, Object source,
			String command, Class<?> eventType) throws NoSuchMethodException {
		Method method = null;

		Class<?> type = guiType;
		while (type != null && !type.equals(Object.class) && method == null) {
			try {
//				System.out.println(String.format("[%s] // [%s]",
//						type.getName(), command));
				method = type.getDeclaredMethod(command, eventType);
				method.setAccessible(true);
				methods.put(source, method);
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
	 * @param e
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private Object execute(Object source, Object e)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		if (methods.containsKey(source)) {
			return methods.get(source).invoke(getGui().getController(), e);
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
}
