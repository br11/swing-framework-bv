package br.atech.workshop.duplicateCode.dry;

import br.atech.workshop.duplicateCode.gui.Controller;

/**
 * Tela com comportamento mais especializado. Programação do tipo
 * "Crianças, não tentem fazer isso em casa".
 * 
 * @author marcio
 * 
 */
public abstract class SimpleGui<T> extends AdvancedGui<T> {

	private T model;

	/**
	 * 
	 * @param controller
	 */
	public SimpleGui() {
		this(null);
	}

	/**
	 * 
	 * @param controller
	 */
	public SimpleGui(Controller<T> controller) {
		super(controller);

		setActionListener(new SimpleEventListener<>(this,
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.duplicateCode.gui.Controller#getModel()
	 */
	@Override
	public T getModel() {
		if (getController() == this) {
			return model;
		} else {
			return getController().getModel();
		}
	}

	public void onRequest() {

	}

	public void onResponse() {

	}
}