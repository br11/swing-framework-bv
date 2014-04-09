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
@Binding(model = TestBean.class, assertValid = true)
public class TestGui2 extends SimpleGui<TestBean> {

	JTextField name;
	JTextField age;
	JButton button;
	
	/**
	 * 
	 * @param nameVal
	 * @param ageVal
	 */
	public TestGui2(String nameVal, String ageVal) {
		super(new TestController2(TestBean.class));
		
		name  = addContent(new JTextField(nameVal));
		age  = addContent(new JTextField(ageVal));
		
		button = addAction(new JButton("Salvar"));
	}

}