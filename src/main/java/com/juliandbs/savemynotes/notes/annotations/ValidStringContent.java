package com.juliandbs.savemynotes.notes.annotations;

import com.juliandbs.savemynotes.notes.validators.StringContentValidator;

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
@Constraint(validatedBy = StringContentValidator.class)
@Retention(RUNTIME)
public @interface ValidStringContent {

	String message() default "Null Note Content";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
