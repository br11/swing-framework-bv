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
public class SimpleEventListener<T extends Gui> extends
		ExtendedEventListener<T> {

	/**
	 * 
	 * @param gui
	 * @param exHandler
	 */
	public SimpleEventListener(T gui, ExceptionHandler exHandler) {
		super(gui, exHandler);
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
			// TODO implementar validação de campos da tela.

			getGui().getFrame().setCursor(
					Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			super.onAction(event);
		} finally {
			getGui().getFrame().setCursor(
					Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
}
