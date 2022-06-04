package com.juliandbs.savemynotes.registration.validators;

import com.juliandbs.savemynotes.registration.annotations.ValidUsername;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

	@Override
	public void initialize(ValidUsername validUsername) {}

	@Override
	public boolean isValid(String usernameField, ConstraintValidatorContext context) {
		return usernameField != null &&
			!usernameField.equals("null") &&
			!usernameField.equals("Null") &&
			!usernameField.equals("") &&
			!usernameField.startsWith(" ") &&
			!usernameField.endsWith(" ");
	}
}
