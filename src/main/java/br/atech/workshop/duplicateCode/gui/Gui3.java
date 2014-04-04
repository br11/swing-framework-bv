package br.atech.workshop.duplicateCode.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.atech.workshop.duplicateCode.app.App;
import br.atech.workshop.duplicateCode.app.AppException;

/**
 * Eliminação de parte do código duplicado.
 * 
 * @author marcio
 * 
 */
public class Gui3 extends AbstractGui implements Gui, Controller {

	final JLabel namelbl;
	final JTextField namefield;
	final JLabel resultlbl;
	final JLabel resultfield;

	final JButton btn1;
	final JButton btn2;
	final JButton btn3;

	private final App app;

	/**
	 * 
	 * @param app
	 */
	public Gui3(App app) {
		this.app = app;

		namelbl = addContent(new JLabel("Nome:"));
		namefield = addContent(new JTextField());
		resultlbl = addContent(new JLabel("Resultado:"));
		resultfield = addContent(new JLabel(""));

		btn1 = addAction(new JButton("Dia"));
		btn2 = addAction(new JButton("Tarde"));
		btn3 = addAction(new JButton("Noite"));

		addNamefieldListeners();
		addBtn1Listeners();
		addBtn2Listeners();
		addBtn3Listeners();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.duplicateCode.gui.AbstractGui#reset()
	 */
	@Override
	public void reset() {
		resultfield.setText("");
		super.reset();
	}

	/**
	 * 
	 */
	protected void addBtn1Listeners() {
		btn1.addActionListener(new AbstractActionListener(this) {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * br.atech.workshop.duplicateCode.gui.Gui3.Gui3Listener#onAction()
			 */
			@Override
			protected void onAction() throws AppException {
				resultfield.setText(app.feature1(namefield.getText()));
			}
		});
	}

	/**
	 * 
	 */
	protected void addBtn2Listeners() {
		btn2.addActionListener(new AbstractActionListener(this) {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * br.atech.workshop.duplicateCode.gui.Gui3.Gui3Listener#onAction()
			 */
			@Override
			protected void onAction() throws AppException {
				resultfield.setText(app.feature2(namefield.getText()));
			}
		});
	}

	/**
	 * 
	 */
	protected void addBtn3Listeners() {
		btn3.addActionListener(new AbstractActionListener(this) {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * br.atech.workshop.duplicateCode.gui.Gui3.Gui3Listener#onAction()
			 */
			@Override
			protected void onAction() throws AppException {
				resultfield.setText(app.feature3(namefield.getText()));
			}
		});
	}

	/**
	 * 
	 */
	protected void addNamefieldListeners() {
		namefield.getDocument().addDocumentListener(
				new DefaultDocumentListener(this));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.duplicateCode.gui.Gui#getController()
	 */
	@Override
	public Controller getController() {
		return this;
	}
}