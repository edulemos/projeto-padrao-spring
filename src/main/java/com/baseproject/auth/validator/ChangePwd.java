package com.baseproject.auth.validator;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = ChangePwdValidator.class)
@Documented
public @interface ChangePwd {
	String message() default "{auth.password.mismatch}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
