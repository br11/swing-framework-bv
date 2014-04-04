package br.atech.workshop.duplicateCode.gui;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * 
 * @author marcio
 * 
 */
public class DefaultDocumentListener implements DocumentListener {

	private final AbstractGui gui;

	/**
	 * @param gui
	 */
	DefaultDocumentListener(AbstractGui gui) {
		this.gui = gui;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event
	 * .DocumentEvent)
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		onChange(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event
	 * .DocumentEvent)
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		onChange(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.
	 * event.DocumentEvent)
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
		onChange(e);
	}

	/**
	 * 
	 * @param e
	 */
	public void onChange(DocumentEvent e) {
		gui.reset();
	}
}