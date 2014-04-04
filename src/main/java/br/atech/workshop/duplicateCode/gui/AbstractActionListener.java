package br.atech.workshop.duplicateCode.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.atech.workshop.duplicateCode.app.AppException;
import br.atech.workshop.duplicateCode.dry.ExceptionHandler;

/**
 * Padronização do comportamento de tela.
 * 
 * @author marcio
 * 
 */
public abstract class AbstractActionListener implements ActionListener {

	private Gui gui;
	private ExceptionHandler exHandler;

	/**
	 * 
	 * @param gui
	 */
	public AbstractActionListener(Gui gui) {
		this.gui = gui;
		this.exHandler = new ExceptionHandler(gui);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
	 * ActionEvent)
	 */
	@Override
	public final void actionPerformed(ActionEvent event) {
		try {
			gui.reset();
			onAction();
		} catch (AppException e) {
			exHandler.handle(e);
		}
	}

	/**
	 * 
	 * @throws AppException
	 */
	protected abstract void onAction() throws AppException;
}