package br.atech.workshop.duplicateCode.validation;

import javax.swing.JTextField;

import br.atech.workshop.duplicateCode.binding.Binding;
import br.atech.workshop.duplicateCode.validation.Domain.PreDef;

/**
 * 
 * @author marcio
 * 
 */
@Binding(model = TestBean.class)
public class TestGui {

	@Required
	@Domain(PreDef.Alfa)
	private JTextField name = new JTextField();

	@Required
	@Domain(value = PreDef.Int)
	private JTextField age = new JTextField();

	/**
	 * 
	 * @param nameVal
	 * @param ageVal
	 */
	public TestGui(String nameVal, String ageVal) {
		name.setText(nameVal);
		age.setText(ageVal);
	}

}