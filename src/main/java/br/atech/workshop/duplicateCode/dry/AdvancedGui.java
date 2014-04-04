package br.atech.workshop.duplicateCode.dry;

/**
 * Tela com comportamento mais especializado. Programação do tipo
 * "Crianças, não tentem fazer isso em casa".
 * 
 * @author marcio
 * 
 */
public class AdvancedGui extends ExtendedGui {

	/**
	 * 
	 */
	public AdvancedGui() {
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