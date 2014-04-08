/**
 * 
 */
package br.atech.workshop.duplicateCode.dry;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JComponent;

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
	protected void findActionMethod(Class<?> guiType, Field field,
			JComponent component) throws NoSuchMethodException {
		if (findEventMethod(guiType, component, field.getName() + "OnClick",
				ActionEvent.class)
				|| findEventMethod(guiType, component, field.getName(),
						new Class[0])) {
			((JButton) component).addActionListener(getActionListener());
		} else {
			super.findActionMethod(guiType, field, component);
		}
	}
}
