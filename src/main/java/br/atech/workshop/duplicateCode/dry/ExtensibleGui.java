package br.atech.workshop.duplicateCode.dry;

import javax.swing.event.DocumentEvent;

import br.atech.workshop.duplicateCode.app.AppException;
import br.atech.workshop.duplicateCode.gui.AbstractGui;
import br.atech.workshop.duplicateCode.gui.CloseListener;
import br.atech.workshop.duplicateCode.gui.Controller;
import br.atech.workshop.duplicateCode.gui.Gui;

/**
 * Implementa o comportamento que é padão nas telas. Permite extender e/ou
 * sobrescrever o comportamento padrão.
 * 
 * @author marcio
 * 
 */
public abstract class ExtensibleGui<T> extends AbstractGui implements Gui<T>,
		Controller<T> {

	private StandardEventListener<? extends ExtensibleGui<?>> actionListener = new StandardEventListener<>(
			this, new ExceptionHandler(this));

	/*
	 * 
	 */
	private Controller<T> controller;

	/**
	 * 
	 * @param controller
	 */
	public ExtensibleGui(Controller<T> controller) {
		this.controller = controller;
		getFrame().addWindowListener(new CloseListener(this));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.duplicateCode.gui.AbstractGui#show()
	 */
	@Override
	public final void show() {
		beforeShow();
		onShow();
		afterShow();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.duplicateCode.gui.AbstractGui#hide()
	 */
	@Override
	public final void hide() {
		beforeHide();
		onHide();
		afterHide();
	}

	/**
	 * 
	 */
	protected void activateListeners() {
		getActionListener().activate();
	}

	/**
	 * 
	 */
	protected void deactivateListeners() {
		getActionListener().deactivate();
	}

	/**
	 * 
	 * @param event
	 * @throws AppException
	 */
	protected void anyOnChange(DocumentEvent event) throws AppException {
		reset();
	}

	/**
	 * 
	 */
	protected void onShow() {
		super.show();
	}

	/**
	 * 
	 */
	protected void onHide() {
		super.hide();
	}

	/**
	 * 
	 */
	protected void beforeShow() {
		activateListeners();
	}

	/**
	 * 
	 */
	protected void afterShow() {
	}

	/**
	 * 
	 */
	protected void beforeHide() {
	}

	/**
	 * 
	 */
	protected void afterHide() {
	}

	/**
	 * @return the actionListener
	 */
	@SuppressWarnings("unchecked")
	public StandardEventListener<ExtensibleGui<?>> getActionListener() {
		return (StandardEventListener<ExtensibleGui<?>>) actionListener;
	}

	/**
	 * @param actionListener
	 *            the actionListener to set
	 */
	public void setActionListener(
			StandardEventListener<? extends ExtensibleGui<?>> actionListener) {
		if (this.actionListener != null) {
			this.actionListener.deactivate();
		}
		this.actionListener = actionListener;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.duplicateCode.gui.Gui#getController()
	 */
	@Override
	public Controller<T> getController() {
		if (controller == null) {
			controller = this;
		}
		return controller;
	}

}