package com.juliandbs.savemynotes.registration.validations;

import com.juliandbs.savemynotes.registration.annotations.ValidMatchingPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchingPasswordValidator implements ConstraintValidator<ValidMatchingPassword, String> {

	@Override
	public boolean isValid (final String matchingPasswordField, final ConstraintValidatorContext context) {
		return matchingPasswordField != null &&
			!matchingPasswordField.equals("null") &&
			!matchingPasswordField.equals("Null") &&
			!matchingPasswordField.equals("NULL") &&
			!matchingPasswordField.equals("") &&
			!matchingPasswordField.startsWith(" ") &&
			!matchingPasswordField.endsWith(" ");
	}
}
