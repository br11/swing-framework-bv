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
	@Domain(PreDef.ALFA)
	private JTextField name = new JTextField();

	@Required
	@Domain(PreDef.NUM)
	private JTextField ages = new JTextField();

	/**
	 * 
	 * @param nameVal
	 * @param ageVal
	 */
	public TestGui(String nameVal, String ageVal) {
		name.setText(nameVal);
		ages.setText(ageVal);
	}

}