/**
 * 
 */
package com.imooc.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author apple
 *
 */
//@Target:表示注解可以标注在什么地方
@Target({ElementType.FIELD , ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {
	String message();

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
