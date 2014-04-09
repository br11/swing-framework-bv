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
		Any(".*", "O valor informado no campo {field} é inválido."), //
		Alfa("[a-zA-Z]*", "O campo {field} deve conter apenas letras."), //
		Name("[\\. a-zA-Z]*", "O campo {field} deve conter um nome válido."), //
		Text("[\\., a-zA-Z0-9]*", "O campo {field} deve conter apenas texto."), //
		Alfanum("[a-zA-Z0-9]*",
				"O campo {field} deve conter apenas letras e números."), //
		Int("[0-9]*", "O campo {field} deve conter apenas números."), //
		Float("[0-9]+[\\.,]?[0-9]*",
				"O valor do campo {field} deve ser numérico."), //
		NotNull(".*", "O valor do campo {field} deve ser numérico.");

		private final String regex;
		private final String errorMessage;

		/**
		 * 
		 * @param regex
		 * @param errorMessage
		 */
		private PreDef(String regex, String errorMessage) {
			this.regex = regex;
			this.errorMessage = errorMessage;
		}

		/**
		 * 
		 * @param value
		 * @return
		 */
		public boolean isValid(Object value) {
			return value.toString().matches(regex);
		}

		/**
		 * @return the errorMessage
		 */
		public String getErrorMessage() {
			return errorMessage;
		}
	}

	String message() default "O valor informado no campo {field} está fora dos limites permitidos.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	int min() default Integer.MIN_VALUE;

	int max() default Integer.MAX_VALUE;

	PreDef[] value() default PreDef.Any;
}
