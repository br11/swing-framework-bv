package br.atech.workshop.duplicateCode.validation;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;

import org.hibernate.validator.HibernateValidator;

import br.atech.workshop.duplicateCode.binding.Binding;
import br.atech.workshop.duplicateCode.gui.Gui;

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

	/*
	 * (non-Javadoc)
	 * @see javax.validation.Validator#validate(java.lang.Object, java.lang.Class[])
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T> Set<ConstraintViolation<T>> validate(T gui, Class<?>... groups) {
		Set allViolations = new LinkedHashSet<ConstraintViolation>();

		Set<?> guiViolations = transform(validator.validate(gui, groups));
		allViolations.addAll(guiViolations);

		if (gui instanceof Gui<?>
				&& gui.getClass().getAnnotation(Binding.class).assertValid()) {
			Set modelViolations = transform(validator.validate(((Gui<?>) gui)
					.getController().getModel(), groups));
			allViolations.addAll(modelViolations);
		}

		return (Set<ConstraintViolation<T>>) allViolations;
	}

	/**
	 * 
	 * @param gui
	 * @param groups
	 */
	public <T> void assertValid(T gui, Class<?>... groups) {
		Set<ConstraintViolation<T>> constraintViolations = validate(gui);

		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(
					"Dados incompletos ou inconsistentes.",
					constraintViolations);
		}
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
			transformed.add(new ConstraintViolationWrapper<>(
					constraintViolation));
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
