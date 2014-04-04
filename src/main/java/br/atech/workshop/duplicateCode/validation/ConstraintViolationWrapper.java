package br.atech.workshop.duplicateCode.validation;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

import br.atech.workshop.duplicateCode.binding.Binding;

/**
 * 
 * @author marcio
 * 
 * @param <T>
 */
final class ConstraintViolationWrapper<T> implements ConstraintViolation<T> {
	/**
	 * 
	 */
	private final ConstraintViolation<T> violation;

	/**
	 * @param guiValidator
	 */
	public ConstraintViolationWrapper(ConstraintViolation<T> violation) {
		this.violation = violation;
	}

	@Override
	public ConstraintDescriptor<?> getConstraintDescriptor() {
		return this.violation.getConstraintDescriptor();
	}

	@Override
	public Object[] getExecutableParameters() {
		return this.violation.getExecutableParameters();
	}

	@Override
	public Object getExecutableReturnValue() {
		return this.violation.getExecutableReturnValue();
	}

	@Override
	public Object getInvalidValue() {
		return this.violation.getInvalidValue();
	}

	@Override
	public Object getLeafBean() {
		return this.violation.getLeafBean();
	}

	@Override
	public String getMessage() {
		Binding annotation = this.violation.getLeafBean().getClass()
				.getAnnotation(Binding.class);

		String modelTypeName = this.violation.getLeafBean().getClass()
				.getName();
		String modelPropertyName = this.violation.getPropertyPath().toString();

		if (annotation != null) {
			try {
				annotation.model().getDeclaredField(modelPropertyName);
				modelTypeName = annotation.model().getName();
			} catch (NoSuchFieldException e) {
			}
		}

		String replacement = "{" + modelTypeName + "." + modelPropertyName
				+ "}";

		return this.violation.getMessage().replaceAll("\\{field\\}",
				replacement);
	}

	@Override
	public String getMessageTemplate() {
		return this.violation.getMessageTemplate();
	}

	@Override
	public Path getPropertyPath() {
		return this.violation.getPropertyPath();
	}

	@Override
	public T getRootBean() {
		return this.violation.getRootBean();
	}

	@Override
	public Class<T> getRootBeanClass() {
		return this.violation.getRootBeanClass();
	}

	@Override
	public <U> U unwrap(Class<U> arg0) {
		return this.violation.unwrap(arg0);
	}

	@Override
	public String toString() {
		try {
			Map<String, Object> data = new HashMap<>();

			PropertyDescriptor[] pdescs = Introspector.getBeanInfo(getClass())
					.getPropertyDescriptors();

			for (PropertyDescriptor propertyDescriptor : pdescs) {
				String name = propertyDescriptor.getName();
				Object value = propertyDescriptor.getReadMethod().invoke(this);
				if (value instanceof String) {
					value = "'" + value + "'";
				}
				data.put(name, value);
			}

			return data.toString();

		} catch (IntrospectionException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}