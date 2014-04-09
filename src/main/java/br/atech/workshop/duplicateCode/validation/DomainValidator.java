package br.atech.workshop.duplicateCode.validation;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.atech.workshop.duplicateCode.validation.Domain.PreDef;

public class DomainValidator implements ConstraintValidator<Domain, Object> {

	private RequiredValidator requiredValidator = new RequiredValidator();

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
		this.requiredValidator.initialize(null);
		this.annotation = annotation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(Object valueOrComponent,
			ConstraintValidatorContext context) {
		Object value = valueOrComponent;

		if (value instanceof JTextComponent) {
			value = ((JTextComponent) value).getText();
		} else if (value instanceof JComboBox<?>) {
			value = ((JComboBox<?>) value).getModel().getSelectedItem();
		} else if (value instanceof ButtonGroup) {
			value = ((ButtonGroup) value).getSelection().getSelectedObjects();
		}

		if (value == null || value.toString().isEmpty()) {
			return true;
		}

		for (PreDef predef : annotation.value()) {
			boolean valid;
			if (predef.equals(PreDef.NotNull)) {
				valid = requiredValidator.isValid(value, context);
			} else {
				valid = predef.isValid(value);
			}

			if (!valid) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						predef.getErrorMessage()).addConstraintViolation();
				return false;
			}
		}

		if (annotation.min() != Integer.MIN_VALUE
				|| annotation.max() != Integer.MAX_VALUE) {

			if (!Domain.PreDef.Float.isValid(value.toString())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						Domain.PreDef.Float.getErrorMessage())
						.addConstraintViolation();
				return false;
			}

			if (value instanceof String) {
				value = new Integer((String) value);
			}

			if (value instanceof Number) {
				Number numericValue = (Number) value;
				numericValue.doubleValue();

				if (numericValue.doubleValue() >= annotation.min()
						&& numericValue.doubleValue() <= annotation.max()) {
					return true;
				} else {
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(
							String.format(annotation.message() + " (%d e %d)",
									annotation.min(), annotation.max()))
							.addConstraintViolation();
					return false;
				}
			}
		}
		return true;
	}
}
