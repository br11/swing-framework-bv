package br.atech.workshop.duplicateCode.gui;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.atech.workshop.duplicateCode.app.App;
import br.atech.workshop.duplicateCode.app.AppException;
import br.atech.workshop.duplicateCode.app.MyEntity;

/**
 * Analisando isoladamente esta implementação está correta. Contudo é inviável
 * construir as telas de um sistema desta maneira.
 * 
 * @author marcio
 * 
 */
public class Gui1 extends AbstractGui {

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
	public Gui1(App app) {
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

		addCloseListener();
	}

	/**
	 * 
	 */
	protected void addCloseListener() {
		getFrame().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getFrame().addWindowListener(new WindowListener() {

			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(getFrame(),
						"Deseja realmente encerrar?", "Confirmação",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					hide();
					JOptionPane.showMessageDialog(null, "Tchau!");
					getFrame().dispose();
				}
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
		});
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
					if (validateFields()) {
						waitCursor();
						resultfield.setText("");
						reset();
						MyEntity bean = new MyEntity();
						bean.setName(namefield.getText());
						resultfield.setText(app.feature1(bean).getResult());
					} else {
						print("Campo nome é obrigatório.");
					}
				} catch (AppException e) {
					e.printStackTrace();
					print("System Error");
				} finally {
					defaultCursor();
				}
			}
		});
	}

	private boolean validateFields() {
		return namefield.getText() != null && !namefield.getText().isEmpty();
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
					if (validateFields()) {
						waitCursor();
						resultfield.setText("");
						reset();
						MyEntity bean = new MyEntity();
						bean.setName(namefield.getText());
						resultfield.setText(app.feature2(bean).getResult());
					} else {
						print("Campo nome é obrigatório.");
					}
				} catch (AppException e) {
					e.printStackTrace();
					print("System Error");
				} finally {
					defaultCursor();
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
					if (validateFields()) {
						waitCursor();
						resultfield.setText("");
						reset();
						MyEntity bean = new MyEntity();
						bean.setName(namefield.getText());
						resultfield.setText(app.feature3(bean).getResult());
					} else {
						print("Campo nome é obrigatório.");
					}
				} catch (AppException e) {
					e.printStackTrace();
					print("System Error");
				} finally {
					defaultCursor();
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
				resultfield.setText("");
				reset();
			}
		});
	}

	/**
	 * 
	 */
	private void waitCursor() {
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}

	/**
	 * 
	 */
	private void defaultCursor() {
		getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}