package br.atech.workshop.duplicateCode.dry.listeners;

import java.awt.event.ActionEvent;

import javax.validation.ConstraintViolationException;

/**
 * 
 * @author marcio
 * 
 */
public interface ValidationListener {

	/**
	 * 
	 * @param event
	 * @throws ConstraintViolationException
	 */
	public void onValidate(ActionEvent event)
			throws ConstraintViolationException;
}
