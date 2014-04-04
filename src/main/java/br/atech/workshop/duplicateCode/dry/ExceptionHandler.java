package br.atech.workshop.duplicateCode.dry;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import br.atech.workshop.duplicateCode.app.AppException;
import br.atech.workshop.duplicateCode.gui.Gui;

/**
 * Tratamento de exceção de toda a aplicação.
 * 
 * @author marcio
 * 
 */
public class ExceptionHandler {

	public Gui gui;

	/**
	 * 
	 * @param gui
	 */
	public ExceptionHandler(Gui gui) {
		this.gui = gui;
	}

	/**
	 * 
	 * @param t
	 */
	public void handle(Throwable t) {
		t.printStackTrace();

		String msg = translate(t);

		gui.print(msg);
	}

	/**
	 * 
	 * @param t
	 * @return
	 */
	private String translate(Throwable t) {
		Throwable err = t;
		while (err.getCause() != null
				&& err.getClass().equals(RuntimeException.class)) {
			err = err.getCause();
		}

		if (err instanceof ConstraintViolationException) {
			StringBuilder sb = new StringBuilder();
			Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) err)
					.getConstraintViolations();
			for (ConstraintViolation<?> constraintViolation : constraintViolations) {
				sb.append(constraintViolation.getMessage()).append("; ");
			}
			return sb.toString();
		} else if (err instanceof AppException) {
			return "Não foi possível processar sua requisição.";
		} else if (err instanceof RuntimeException) {
			return "Falha interna. Notifique o administrador do sistema.";
		} else {
			return "Falha de processamento.";
		}
	}
}