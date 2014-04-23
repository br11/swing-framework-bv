package br.atech.workshop.duplicateCode.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.atech.workshop.duplicateCode.app.MyEntity;
import br.atech.workshop.duplicateCode.binding.Binding;
import br.atech.workshop.duplicateCode.dry.SimpleGui;
import br.atech.workshop.duplicateCode.validation.Domain;
import br.atech.workshop.duplicateCode.validation.Domain.PreDef;

/**
 * 
 * Vemos aqui como o código legado pode evoluir para acompanhar as <i>constantes
 * variações</i> da especificação.
 * 
 * @author marcio
 * 
 */
@Binding(model = MyEntity.class)
public class Gui3 extends SimpleGui<MyEntity> {

	final JLabel namelbl;
	final JLabel resultlbl;

	@Domain(PreDef.Name)
	final JTextField name;

	final JLabel result;

	final JButton goodMorning;
	final JButton goodAfternoon;
	final JButton goodNight;

	/**
	 * 
	 * @param app
	 */
	public Gui3() {
		super(new Gui3Controller());

		namelbl = addContent(new JLabel("Nome:"));
		name = addContent(new JTextField());
		resultlbl = addContent(new JLabel("Resultado:"));
		result = addContent(new JLabel(""));

		goodMorning = addAction(new JButton("Dia"));
		goodAfternoon = addAction(new JButton("Tarde"));
		goodNight = addAction(new JButton("Noite"));
	}
}