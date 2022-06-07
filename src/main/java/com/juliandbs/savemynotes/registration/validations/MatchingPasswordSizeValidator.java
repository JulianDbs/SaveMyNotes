package com.juliandbs.savemynotes.registration.validations;

import com.juliandbs.savemynotes.registration.annotations.ValidMatchingPasswordSize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchingPasswordSizeValidator implements ConstraintValidator<ValidMatchingPasswordSize, String> {

	private static final Integer MIN_SIZE = 6;
	private static final Integer MAX_SIZE = 21;

	@Override
	public boolean isValid(final String matchingPasswordField, final ConstraintValidatorContext context) {
		return matchingPasswordField.length() >= MIN_SIZE && matchingPasswordField.length() <= MAX_SIZE;
	}

}
