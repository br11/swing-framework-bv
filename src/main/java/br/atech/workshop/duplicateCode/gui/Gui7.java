package br.atech.workshop.duplicateCode.gui;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.atech.workshop.duplicateCode.app.App;
import br.atech.workshop.duplicateCode.app.AppException;
import br.atech.workshop.duplicateCode.dry.AdvancedGui;

/**
 * Devido ao seu alto nível de sofisticação, uma ferramenta pode demandar certa
 * calibragem antes de ser usada. Isto se faz para que esta se ajuste à maneira
 * como ela será usada. Em desenvolvimento isso pode significar dispor de
 * criatividade e programação avançada.<br/>
 * O segredo é isolar bem o código que faz o trabalho "sujo".
 * 
 * @author marcio
 * 
 */
public class Gui7 extends AdvancedGui {

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
	public Gui7(App app) {
		this.app = app;

		namelbl = addContent(new JLabel("Nome:"));
		namefield = addContent(new JTextField());
		resultlbl = addContent(new JLabel("Resultado:"));
		resultfield = addContent(new JLabel(""));

		btn1 = addAction(new JButton("Dia"));
		btn2 = addAction(new JButton("Tarde"));
		btn3 = addAction(new JButton("Noite"));
	}

	/**
	 * 
	 * @param event
	 * @throws AppException
	 */
	protected void btn1OnClick(ActionEvent event) throws AppException {
		resultfield.setText(app.feature1(namefield.getText()));
	}

	/**
	 * 
	 * @param event
	 * @throws AppException
	 */
	protected void btn2OnClick(ActionEvent event) throws AppException {
		resultfield.setText(app.feature2(namefield.getText()));
	}

	/**
	 * 
	 * @param event
	 * @throws AppException
	 */
	protected void btn3OnClick(ActionEvent event) throws AppException {
		resultfield.setText(app.feature3(namefield.getText()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.bestpractices.gui.AbstractGui#reset()
	 */
	@Override
	public void reset() {
		resultfield.setText("");
		super.reset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.atech.workshop.duplicateCode.dry.ExtensibleGui#beforeHide()
	 */
	@Override
	protected void beforeHide() {
		if (!confirm("Deseja realmente encerrar?")) {
			abort();
		}

		super.beforeHide();
	}
}