package com.juliandbs.savemynotes.registration.validations;

import com.juliandbs.savemynotes.registration.annotations.ValidUsernameSize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameSizeValidator implements ConstraintValidator<ValidUsernameSize, String> {

	private static final Integer MIN_SIZE = 4;
	private static final Integer MAX_SIZE = 20;

	@Override
	public boolean isValid(final String usernameField, final ConstraintValidatorContext context) {
		return usernameField.length() >= MIN_SIZE && usernameField.length() <= MAX_SIZE;
	}

}
