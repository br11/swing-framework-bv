/**
 * 
 */
package br.atech.workshop.duplicateCode.validation;

import br.atech.workshop.duplicateCode.dry.SimpleController;

/**
 * @author spac2
 * 
 */
public class TestController5 extends SimpleController<TestBean> {

	/**
	 * @param modelType
	 */
	public TestController5() {
		super(TestBean.class);
	}

	public void button() throws Exception {
		System.out.println(">>> button");

		getModel().setName("beltrano");
		getModel().setAge(40);
	}
}
