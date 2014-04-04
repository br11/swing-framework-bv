/**
 * 
 */
package br.atech.workshop.duplicateCode.dry;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import br.atech.workshop.duplicateCode.gui.Gui;
import br.atech.workshop.duplicateCode.validation.GuiValidator;

/**
 * 
 * 
 * @author marcio
 * 
 * @param <T>
 */
public class SimpleEventListener<T extends Gui> extends
		ExtendedEventListener<T> {

	/**
	 * 
	 * @param gui
	 * @param exHandler
	 */
	public SimpleEventListener(T gui, ExceptionHandler exHandler) {
		super(gui, exHandler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.atech.workshop.bestpractices.util.AbstractActionListener#onAction(
	 * java.awt.event.ActionEvent)
	 */
	@Override
	public void onAction(ActionEvent event) throws Exception {
		try {
			Set<ConstraintViolation<T>> constraintViolations = new GuiValidator()
					.validate(getGui());
			if (!constraintViolations.isEmpty()) {
				throw new ConstraintViolationException(
						"Dados incompletos ou inconsistentes.",
						constraintViolations);
			}

			getGui().getFrame().setCursor(
					Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			super.onAction(event);
		} finally {
			getGui().getFrame().setCursor(
					Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
}
