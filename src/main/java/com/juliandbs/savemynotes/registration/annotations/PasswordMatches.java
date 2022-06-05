package com.juliandbs.savemynotes.registration.annotations;

import com.juliandbs.savemynotes.registration.validations.PasswordMatchesValidator;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({TYPE, ANNOTATION_TYPE})
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Retention(RUNTIME)
public @interface PasswordMatches {

	String message() default "Passwords don't match";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
