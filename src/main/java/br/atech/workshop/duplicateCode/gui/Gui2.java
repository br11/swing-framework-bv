package br.atech.workshop.duplicateCode.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.atech.workshop.duplicateCode.app.App;
import br.atech.workshop.duplicateCode.app.AppException;
import br.atech.workshop.duplicateCode.dry.ExceptionHandler;

/**
 * Melhoria no tratamento de exceção.
 * 
 * @author marcio
 * 
 */
public class Gui2 extends AbstractGui implements Gui, Controller {

	final JLabel namelbl;
	final JTextField namefield;
	final JLabel resultlbl;
	final JLabel resultfield;

	final JButton btn1;
	final JButton btn2;
	final JButton btn3;

	private final App app;

	private ExceptionHandler exHandler = new ExceptionHandler(this);

	/**
	 * 
	 * @param app
	 */
	public Gui2(App app) {
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
		btn1.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					reset();
					resultfield.setText(app.feature1(namefield.getText()));
				} catch (AppException e) {
					exHandler.handle(e);
				}
			}
		});
	}

	/**
	 * 
	 */
	protected void addBtn2Listeners() {
		btn2.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					reset();
					resultfield.setText(app.feature2(namefield.getText()));
				} catch (AppException e) {
					exHandler.handle(e);
				}
			}
		});
	}

	/**
	 * 
	 */
	protected void addBtn3Listeners() {
		btn3.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					reset();
					resultfield.setText(app.feature3(namefield.getText()));
				} catch (AppException e) {
					exHandler.handle(e);
				}
			}
		});
	}

	/**
	 * 
	 */
	protected void addNamefieldListeners() {
		namefield.getDocument().addDocumentListener(new DocumentListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * javax.swing.event.DocumentListener#removeUpdate(javax.swing.event
			 * .DocumentEvent)
			 */
			@Override
			public void removeUpdate(DocumentEvent e) {
				onChange(e);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * javax.swing.event.DocumentListener#insertUpdate(javax.swing.event
			 * .DocumentEvent)
			 */
			@Override
			public void insertUpdate(DocumentEvent e) {
				onChange(e);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * javax.swing.event.DocumentListener#changedUpdate(javax.swing.
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
				reset();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see br.atech.workshop.duplicateCode.gui.Gui#getController()
	 */
	@Override
	public Controller getController() {
		return this;
	}
}