package br.atech.workshop.duplicateCode.validation;

import javax.swing.JTextField;

import br.atech.workshop.duplicateCode.binding.Binding;
import br.atech.workshop.duplicateCode.validation.Domain.PreDef;

@Binding(model = Object.class, assertValid = true)
public class TestGui {

	@Required
	@Domain(PreDef.ALFA)
	private JTextField name = new JTextField();

	@Required
	@Domain(PreDef.NUM)
	private JTextField age = new JTextField();

	public TestGui(String nameVal, String ageVal) {
		name.setText(nameVal);
		age.setText(ageVal);
	}
	
}