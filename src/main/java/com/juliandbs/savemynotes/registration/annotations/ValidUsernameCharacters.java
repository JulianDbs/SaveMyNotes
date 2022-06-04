package com.juliandbs.savemynotes.registration.annotations;

import com.juliandbs.savemynotes.registration.validators.UsernameCharactersValidator;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = UsernameCharactersValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidUsernameCharacters {

	String message() default "Character/s not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default{};
}

