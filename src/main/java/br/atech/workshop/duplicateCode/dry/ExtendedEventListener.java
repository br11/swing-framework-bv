/**
 * 
 */
package br.atech.workshop.duplicateCode.dry;

import java.awt.Cursor;
import java.awt.event.ActionEvent;

import br.atech.workshop.duplicateCode.gui.Gui;

/**
 * Implementa a paronização do comportamento de tela.
 * 
 * @author marcio
 * 
 * @param <T>
 */
public class ExtendedEventListener<T extends Gui> extends
		StandardEventListener<T> {

	/**
	 * 
	 * @param gui
	 * @param exHandler
	 */
	public ExtendedEventListener(T gui, ExceptionHandler exHandler) {
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
			getGui().getFrame().setCursor(
					Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			super.onAction(event);
		} finally {
			getGui().getFrame().setCursor(
					Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
}
