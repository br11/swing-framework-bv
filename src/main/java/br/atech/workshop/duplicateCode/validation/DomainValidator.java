package br.atech.workshop.duplicateCode.validation;

import javax.swing.JTextField;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DomainValidator implements ConstraintValidator<Domain, Object> {

	private Domain annotation;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(Domain annotation) {
		this.annotation = annotation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(Object component, ConstraintValidatorContext context) {
		Object value = ((JTextField) component).getText();

		return annotation.value().isValid(value);
	}
}
