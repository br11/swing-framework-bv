/**
 * 
 */
package br.atech.workshop.duplicateCode.dry;

import java.awt.Cursor;
import java.awt.event.ActionEvent;

import br.atech.workshop.duplicateCode.gui.Gui;

/**
 * 
 * 
 * @author marcio
 * 
 * @param <T>
 */
public class SimpleEventListener<T extends Gui<?>> extends
		ExtendedEventListener<T> {

	private SimpleEventUtil<T> util;

	/**
	 * 
	 * @param gui
	 * @param exHandler
	 */
	public SimpleEventListener(T gui, ExceptionHandler exHandler) {
		super(gui, exHandler);
		try {
			util = new SimpleEventUtil<T>(gui, this);
		} catch (NoSuchMethodException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.atech.workshop.bestpractices.util.AbstractActionListener#onAction(
	 * java.awt.event.ActionEvent)
	 */
	@Override
	public void onAction(ActionEvent event) throws Exception {
		try {
			getGui().getFrame().setCursor(
					Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

			/* */
			if (getGui() instanceof SimpleGui<?>) {
				((SimpleGui<?>) getGui()).onRequest();
			}

			/* */
			if (getGui().getController() instanceof AbstractControler<?>) {
				((AbstractControler<?>) getGui().getController()).onValidate();
			}

			super.onAction(event);
		} finally {

			/* */
			if (getGui() instanceof SimpleGui<?>) {
				((SimpleGui<?>) getGui()).onResponse();
			}

			getGui().getFrame().setCursor(
					Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.duplicateCode.dry.GenericEventListener#getUtil()
	 */
	@Override
	public EventUtil<T> getUtil() {
		return util;
	}
}
