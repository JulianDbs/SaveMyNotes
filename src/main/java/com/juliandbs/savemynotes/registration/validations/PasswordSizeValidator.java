package com.juliandbs.savemynotes.registration.validations;

import com.juliandbs.savemynotes.registration.annotations.ValidPasswordSize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordSizeValidator implements ConstraintValidator<ValidPasswordSize, String> {

	private static final Integer MIN_SIZE = 6;
	private static final Integer MAX_SIZE = 21;

	@Override
	public boolean isValid(final String passwordField, final ConstraintValidatorContext context) {
		return passwordField.length() >= MIN_SIZE && passwordField.length() <= MAX_SIZE;
	}

}
