package br.atech.workshop.duplicateCode.dry;

/**
 * Tela com comportamento mais especializado. Programação do tipo
 * "Crianças, não tentem fazer isso em casa".
 * 
 * @author marcio
 * 
 */
public class SimpleGui extends AdvancedGui {

	/**
	 * 
	 */
	public SimpleGui() {
		setActionListener(new ExtendedEventListener<>(this,
				new AdvancedExceptionHandler(this)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.duplicateCode.dry.ExtensibleGui#beforeHide()
	 */
	@Override
	protected void beforeHide() {
		if (!confirm("Deseja realmente encerrar?")) {
			abort();
		}

		super.beforeHide();
	}
}