package br.atech.workshop.duplicateCode.validation;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;

import org.hibernate.validator.HibernateValidator;

/**
 * 
 * @author marcio
 *
 */
public class GuiValidator implements Validator {

	private Validator validator;

	/**
	 * 
	 */
	public GuiValidator() {
		this(Validation.byProvider(HibernateValidator.class).configure()
				.messageInterpolator(new CustomMessageInterpolator())
				.buildValidatorFactory().getValidator());
	}

	/**
	 * 
	 * @param validator
	 */
	public GuiValidator(Validator validator) {
		this.validator = validator;
	}

	@Override
	public <T> Set<ConstraintViolation<T>> validate(T gui, Class<?>... groups) {
		return transform(validator.validate(gui, groups));
	}

	/**
	 * 
	 * @param validations
	 * @return
	 */
	private <T> Set<ConstraintViolation<T>> transform(
			Set<ConstraintViolation<T>> validations) {
		// System.out.println();
		// System.out.println("Violações: [" + validations.size() + "]");

		Set<ConstraintViolation<T>> transformed = new LinkedHashSet<>();
		for (ConstraintViolation<T> constraintViolation : validations) {
			transformed.add(new ConstraintViolationWrapper<>(constraintViolation));
		}
		
		return transformed;
	}

	@Override
	public BeanDescriptor getConstraintsForClass(Class<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> Set<ConstraintViolation<T>> validateProperty(T arg0,
			String arg1, Class<?>... arg2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> Set<ConstraintViolation<T>> validateValue(Class<T> arg0,
			String arg1, Object arg2, Class<?>... arg3) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ExecutableValidator forExecutables() {
		throw new UnsupportedOperationException();
	}
}
