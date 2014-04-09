package br.atech.workshop.duplicateCode.dry;

import java.awt.event.ActionEvent;

import javax.validation.ConstraintViolationException;

import br.atech.workshop.duplicateCode.dry.listeners.RequestListener;
import br.atech.workshop.duplicateCode.dry.listeners.ResponseListener;
import br.atech.workshop.duplicateCode.dry.listeners.ValidationListener;
import br.atech.workshop.duplicateCode.gui.Controller;
import br.atech.workshop.duplicateCode.validation.GuiValidator;

/**
 * Tela com comportamento mais especializado. Programação do tipo
 * "Crianças, não tentem fazer isso em casa".
 * 
 * @author marcio
 * 
 */
public abstract class SimpleGui<T> extends AdvancedGui<T> implements
		RequestListener, ResponseListener, ValidationListener {

	private T model;

	private GuiValidator validator = new GuiValidator();

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
	 * @see
	 * br.atech.workshop.duplicateCode.dry.ExtensibleGui#getActionListener()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public SimpleEventListener getActionListener() {
		return (SimpleEventListener) super.getActionListener();
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

	public void setModel(T model) {
		if (getController() == this) {
			this.model = model;
		} else {
			getController().setModel(model);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.atech.workshop.duplicateCode.dry.ValidationListener#onValidate(java
	 * .awt.event.ActionEvent)
	 */
	@Override
	public void onValidate(ActionEvent event)
			throws ConstraintViolationException {
		validator.assertValid(this);
		validator.assertValid(this.getController());
		validator.assertValid(this.getController().getModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.atech.workshop.duplicateCode.dry.RequestListener#onRequest(java.awt
	 * .event.ActionEvent)
	 */
	@Override
	public void onRequest(ActionEvent event) throws Exception {
		SimpleEventUtil.readInput(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.atech.workshop.duplicateCode.dry.listeners.ResponseListener#onResponse
	 * (java.awt.event.ActionEvent)
	 */
	@Override
	public void onResponse(ActionEvent event) throws Exception {
		SimpleEventUtil.writeOutput(this);
	}

}