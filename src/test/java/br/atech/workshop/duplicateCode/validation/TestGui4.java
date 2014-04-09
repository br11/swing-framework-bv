package br.atech.workshop.duplicateCode.validation;

import javax.swing.JButton;
import javax.swing.JTextField;

import br.atech.workshop.duplicateCode.binding.Binding;
import br.atech.workshop.duplicateCode.dry.SimpleGui;
import br.atech.workshop.duplicateCode.gui.Controller;

/**
 * 
 * @author marcio
 * 
 */
@Binding(model = TestBean.class)
public class TestGui4 extends SimpleGui<TestBean> {

	@Binding("name")
	JTextField nameText;

	@Binding("age")
	JTextField ageText;

	JButton button;

	/**
	 * 
	 * @param controller
	 * @param nameVal
	 * @param ageVal
	 */
	public TestGui4(Controller<TestBean> controller) {
		this(controller, null, null);
	}

	/**
	 * 
	 * @param controller
	 * @param nameVal
	 * @param ageVal
	 */
	public TestGui4(Controller<TestBean> controller, String nameVal,
			String ageVal) {
		super(controller);

		nameText = addContent(new JTextField(nameVal));
		ageText = addContent(new JTextField(ageVal));

		button = addAction(new JButton("Salvar"));
	}

	/**
	 * 
	 * @param nameVal
	 * @param ageVal
	 */
	public TestGui4(String nameVal, String ageVal) {
		this(new TestController2(TestBean.class), nameVal, ageVal);
	}

}