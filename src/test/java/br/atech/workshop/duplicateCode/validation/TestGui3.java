package br.atech.workshop.duplicateCode.validation;

import javax.swing.JButton;
import javax.swing.JTextField;

import br.atech.workshop.duplicateCode.binding.Binding;
import br.atech.workshop.duplicateCode.dry.SimpleGui;

/**
 * 
 * @author marcio
 * 
 */
@Binding(model = TestBean.class)
public class TestGui3 extends SimpleGui<TestBean> {

	JTextField nameText;
	JTextField ageText;
	
	JButton button;
	
	/**
	 * 
	 * @param nameVal
	 * @param ageVal
	 */
	public TestGui3(String nameVal, String ageVal) {
		super(new TestController2(TestBean.class));
		
		nameText  = addContent(new JTextField(nameVal));
		ageText  = addContent(new JTextField(ageVal));
		
		button = addAction(new JButton("Salvar"));
	}

}