package br.atech.workshop.duplicateCode.dry;

import javax.swing.JOptionPane;

import br.atech.workshop.duplicateCode.gui.Controller;

/**
 * Tela com comportamento mais especializado.
 * 
 * @author marcio
 * 
 */
public abstract class ExtendedGui<T> extends ExtensibleGui<T> {

	/**
	 * 
	 * @param controller
	 */
	public ExtendedGui(Controller<T> controller) {
		super(controller);

		setActionListener(new ExtendedEventListener<>(this,
				new ExceptionHandler(this)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.duplicateCode.dry.ExtensibleGui#afterHide()
	 */
	@Override
	protected void afterHide() {
		super.afterHide();
		JOptionPane.showMessageDialog(null, "Tchau!");
	}

	/**
	 * 
	 * @param message
	 * @return
	 */
	protected boolean confirm(String message) {
		return JOptionPane.showConfirmDialog(getFrame(), message,
				"Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	}
}