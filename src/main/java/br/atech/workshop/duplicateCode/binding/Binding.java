package br.atech.workshop.duplicateCode.binding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author marcio
 * 
 */
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Binding {

	String value() default "";

	Class<?> model() default Object.class;
	
	boolean assertValid() default true;
}
