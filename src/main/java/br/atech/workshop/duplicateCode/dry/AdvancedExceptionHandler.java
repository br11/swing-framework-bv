package br.atech.workshop.duplicateCode.dry;

import br.atech.workshop.duplicateCode.gui.Gui;

/**
 * Tratamento de exceção de toda a aplicação.
 * 
 * @author marcio
 * 
 */
public class AdvancedExceptionHandler extends ExceptionHandler {
	/**
	 * 
	 * @param gui
	 */
	public AdvancedExceptionHandler(Gui<?> gui) {
		super(gui);
	}

	/*
	 * (non-Javadoc)
	 * @see br.atech.workshop.duplicateCode.dry.ExceptionHandler#handle(java.lang.Throwable)
	 */
	@Override
	public void handle(Throwable t) {
		if (t instanceof AbortSignal) {
			return;
		}
		super.handle(t);
	}
}