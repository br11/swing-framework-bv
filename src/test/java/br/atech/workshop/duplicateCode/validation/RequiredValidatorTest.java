/**
 * 
 */
package br.atech.workshop.duplicateCode.validation;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author marcio
 * 
 */
@SuppressWarnings("unchecked")
public class RequiredValidatorTest {

	@Test
	public void testRequired() {
		Validator validator = new GuiValidator();

		Set<?> validate1 = validator.validate(new TestGui("Uhu", "123"));
		assertValid((Set<ConstraintViolation<?>>) validate1);

		Set<?> validate2 = validator.validate(new TestGui(null, null));
		assertValid(
				(Set<ConstraintViolation<?>>) validate2,
				"Campo {br.atech.workshop.duplicateCode.validation.TestBean.age} é obrigatório.",//
				"Campo {br.atech.workshop.duplicateCode.validation.TestBean.name} é obrigatório.");
	}

	@Test
	public void testPredef() {
		Validator validator = new GuiValidator();

		Set<?> validate1 = validator.validate(new TestGui("abc123", "aa46"));
		assertValid(
				(Set<ConstraintViolation<?>>) validate1,
				"O campo {br.atech.workshop.duplicateCode.validation.TestBean.name} deve conter apenas letras.",//
				"O campo {br.atech.workshop.duplicateCode.validation.TestBean.age} deve conter apenas números.");
	}

	@Test
	public void testMinMax() {
		Validator validator = new GuiValidator();

		Set<?> validate1 = validator.validate(new TestBean("fulano", 15));

		Set<?> validate2 = (Set<?>) validate1;
		Set<ConstraintViolation<?>> validate3 = (Set<ConstraintViolation<?>>) validate2;

		assertValid(
				(Set<ConstraintViolation<?>>) validate3,
				"O valor informado no campo {br.atech.workshop.duplicateCode.validation.TestBean.age} está fora dos limites permitidos. (18 e 65)");
	}

	void assertValid(Set<ConstraintViolation<?>> validations,
			String... expectedMessages) {

		Assert.assertEquals("número de violações inesperado.",
				expectedMessages.length, validations.size());

		Set<String> messages = getMessages(validations);
		for (String expectedMessage : expectedMessages) {
			Assert.assertTrue("Violação esperada não encontrada: "
					+ expectedMessage, messages.contains(expectedMessage));
		}
	}

	Set<String> getMessages(Set<ConstraintViolation<?>> validations) {
		Set<String> messages = new LinkedHashSet<>();
		for (ConstraintViolation<?> constraintViolation : validations) {
			System.out.println(constraintViolation);

			messages.add(constraintViolation.getMessage());
		}
		return messages;
	}

}
