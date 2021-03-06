/**
 * 
 */
package br.atech.workshop.duplicateCode.dry;

import java.awt.event.ActionEvent;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import br.atech.workshop.duplicateCode.gui.Controller;
import br.atech.workshop.duplicateCode.validation.GuiValidator;

/**
 * @author spac2
 * 
 */
public class AbstractControler<T> implements Controller<T> {

	private T model;

	/**
	 * 
	 * @param modelType
	 */
	public AbstractControler(Class<T> modelType) {
		try {
			setModel(modelType.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.duplicateCode.gui.Controller#getModel()
	 */
	@Override
	public T getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(T model) {
		this.model = model;
	}

	/**
	 * 
	 * @param event
	 * @throws ConstraintViolationException
	 */
	public void onValidate(ActionEvent event)
			throws ConstraintViolationException {
		@SuppressWarnings("rawtypes")
		Set<ConstraintViolation<Controller>> constraintViolations = new GuiValidator()
				.validate((Controller) this);

		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(
					"Dados incompletos ou inconsistentes.",
					constraintViolations);
		}
	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void onRequest(ActionEvent event) throws Exception {

	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void onResponse(ActionEvent event) throws Exception {

	}

}
