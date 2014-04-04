/**
 * 
 */
package br.atech.workshop.duplicateCode.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;

/**
 * @author marcio
 * 
 */
public class RequiredValidatorTest {

	@Test
	public void test1() {
		Validator validator = new GuiValidator();

		Set<ConstraintViolation<TestGui>> validate1 = validator
				.validate(new TestGui("Uhu", "123"));
		print(validate1);

		Set<ConstraintViolation<TestGui>> validate2 = validator
				.validate(new TestGui(null, null));
		print(validate2);

		Set<ConstraintViolation<TestGui>> validate3 = validator
				.validate(new TestGui("abc123", "aa46"));
		print(validate3);
	}

	private void print(Set<ConstraintViolation<TestGui>> validations) {

		System.out.println();
		System.out.println("Violações: [" + validations.size() + "]");

		for (ConstraintViolation<TestGui> constraintViolation : validations) {
			System.out.println(constraintViolation);
//			System.out.println("message:" + constraintViolation.getMessage());
		}

	}
}
