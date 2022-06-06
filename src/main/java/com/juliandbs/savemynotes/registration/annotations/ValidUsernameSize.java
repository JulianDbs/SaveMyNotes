package com.juliandbs.savemynotes.registration.annotations;

import com.juliandbs.savemynotes.registration.validations.UsernameSizeValidator;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Constraint(validatedBy = UsernameSizeValidator.class)
@Retention(RUNTIME)
public @interface ValidUsernameSize {

	String message() default "Username length must be greater than 3 and greater or less than 21";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
