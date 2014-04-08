package br.atech.workshop.duplicateCode.dry;

import br.atech.workshop.duplicateCode.gui.Controller;

/**
 * Tela com comportamento mais especializado. Programação do tipo
 * "Crianças, não tentem fazer isso em casa".
 * 
 * @author marcio
 * 
 */
public abstract class AdvancedGui<T> extends ExtendedGui<T> {

	/**
	 * 
	 * @param controller
	 */
	public AdvancedGui(Controller<T> controller) {
		super(controller);

		setActionListener(new ExtendedEventListener<>(this,
				new AdvancedExceptionHandler(this)));
	}

	/**
	 * 
	 */
	protected void abort() {
		throw new AbortSignal();
	}
}