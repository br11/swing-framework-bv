/**
 * 
 */
package br.atech.workshop.duplicateCode.validation;

import java.awt.event.ActionEvent;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author marcio
 * 
 */
public class BindingTest {

	@Test
	public void test2() {

		TestGui2 gui = new TestGui2("fulano", "64");

		TestBean model = new TestBean();

		Assert.assertNull(gui.getController().getModel().getName());
		Assert.assertNull(gui.getController().getModel().getAge());

		gui.getController().setModel(model);

		Assert.assertNull(gui.getController().getModel().getName());
		Assert.assertNull(gui.getController().getModel().getAge());

		gui.getActionListener().actionPerformed(
				new ActionEvent(gui.button, 123, "Salvar"));

		System.out.println("name:" + model.getName());
		System.out.println("age:" + model.getAge());

		Assert.assertNotNull(gui.getController().getModel().getName());
		Assert.assertNotNull(gui.getController().getModel().getAge());
	}

	@Test
	public void test3() {

		TestGui3 gui = new TestGui3("fulano", "64");

		TestBean model = new TestBean();

		Assert.assertNull(gui.getController().getModel().getName());
		Assert.assertNull(gui.getController().getModel().getAge());

		gui.getController().setModel(model);

		Assert.assertNull(gui.getController().getModel().getName());
		Assert.assertNull(gui.getController().getModel().getAge());

		gui.getActionListener().actionPerformed(
				new ActionEvent(gui.button, 123, "Salvar"));

		System.out.println("name:" + model.getName());
		System.out.println("age:" + model.getAge());

		Assert.assertNull(gui.getController().getModel().getName());
		Assert.assertNull(gui.getController().getModel().getAge());
	}

	@Test
	public void test4() {

		TestGui4 gui = new TestGui4("fulano", "64");

		TestBean model = new TestBean();

		Assert.assertNull(gui.getController().getModel().getName());
		Assert.assertNull(gui.getController().getModel().getAge());

		gui.getController().setModel(model);

		Assert.assertNull(gui.getController().getModel().getName());
		Assert.assertNull(gui.getController().getModel().getAge());

		gui.getActionListener().actionPerformed(
				new ActionEvent(gui.button, 123, "Salvar"));

		System.out.println("name:" + model.getName());
		System.out.println("age:" + model.getAge());

		Assert.assertNotNull(gui.getController().getModel().getName());
		Assert.assertNotNull(gui.getController().getModel().getAge());
	}

	@Test
	public void test5() {

		TestGui4 gui = new TestGui4(new TestController5());

		TestBean model = new TestBean("fulano", 64);

		System.out.println("name:" + model.getName());
		System.out.println("age:" + model.getAge());

		Assert.assertNull(gui.getController().getModel().getName());
		Assert.assertNull(gui.getController().getModel().getAge());

		gui.getController().setModel(model);
		gui.show();

		Assert.assertNotNull(gui.getController().getModel().getName());
		Assert.assertNotNull(gui.getController().getModel().getAge());

		gui.getActionListener().actionPerformed(
				new ActionEvent(gui.button, 123, "Salvar"));

		System.out.println("name:" + model.getName());
		System.out.println("age:" + model.getAge());

		Assert.assertEquals("beltrano", gui.nameText.getText());
		Assert.assertEquals("40", gui.ageText.getText());
	}

}
