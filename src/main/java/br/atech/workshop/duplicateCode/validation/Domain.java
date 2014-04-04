package br.atech.workshop.duplicateCode.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * @author marcio
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DomainValidator.class)
public @interface Domain {

	/**
	 * 
	 */
	public enum PreDef {
		ANY(".*"), //
		TEXT("[\\., a-zA-Z0-9]*"), //
		ALFANUM("[a-zA-Z0-9]*"), //
		ALFA("[a-zA-Z]*"), //
		NUM("[0-9]*"), //
		NAME("[ a-zA-Z]*");

		private String regex;

		private PreDef(String regex) {
			this.regex = regex;
		}

		public boolean isValid(Object value) {
			return value == null || value.toString().matches(regex);
		}
	}

	String message() default "O valor informado no campo {field} é inválido.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	double min() default Double.MIN_VALUE;

	double max() default Double.MAX_VALUE;

	PreDef value() default PreDef.ANY;
}
