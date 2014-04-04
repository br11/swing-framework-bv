package br.atech.workshop.duplicateCode.dry;

import javax.swing.event.DocumentEvent;

import br.atech.workshop.duplicateCode.app.AppException;
import br.atech.workshop.duplicateCode.gui.AbstractGui;
import br.atech.workshop.duplicateCode.gui.Controller;
import br.atech.workshop.duplicateCode.gui.Gui;

/**
 * Implementa o comportamento que é padrão nas telas. Permite desenvolver num
 * nível mais abstrato (Elevado).
 * 
 * @author marcio
 * 
 */
public abstract class StandardGui extends AbstractGui implements Gui,
		Controller {

	private StandardEventListener<StandardGui> actionListener = new StandardEventListener<>(
			this, new ExceptionHandler(this));

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.bestpractices.gui.iGui#show()
	 */
	@Override
	public void show() {
		actionListener.activate();
		super.show();
	}

	/**
	 * 
	 * @param event
	 * @throws AppException
	 */
	protected void anyOnChange(DocumentEvent event) throws AppException {
		reset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.duplicateCode.gui.Gui#getController()
	 */
	@Override
	public Controller getController() {
		return this;
	}
}