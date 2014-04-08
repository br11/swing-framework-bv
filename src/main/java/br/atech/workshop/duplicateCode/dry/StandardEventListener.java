/**
 * 
 */
package br.atech.workshop.duplicateCode.dry;

import java.awt.event.ActionEvent;

import br.atech.workshop.duplicateCode.gui.Gui;

/**
 * Implementa a paronização do comportamento de tela.
 * 
 * @author marcio
 * 
 * @param <T>
 */
public class StandardEventListener<T extends Gui<?>> extends
		GenericEventListener<T> {

	/**
	 * 
	 * @param gui
	 * @param exHandler
	 */
	public StandardEventListener(T gui, ExceptionHandler exHandler) {
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
		getGui().reset();
		super.onAction(event);
	}
}
