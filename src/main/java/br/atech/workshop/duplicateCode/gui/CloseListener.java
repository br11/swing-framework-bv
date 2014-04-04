package br.atech.workshop.duplicateCode.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * 
 * @author spac2
 * 
 */
public class CloseListener implements WindowListener {

	/**
	 * 
	 */
	private final AbstractGui gui;

	/**
	 * @param abstractGui
	 */
	public CloseListener(AbstractGui gui) {
		this.gui = gui;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		gui.hide();
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}
}