package com.juliandbs.savemynotes.registration.validations;

import com.juliandbs.savemynotes.registration.annotations.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

	@Override
	public boolean isValid (final String passwordField, final ConstraintValidatorContext context) {
		return passwordField != null &&
			!passwordField.equals("null") &&
			!passwordField.equals("Null") &&
			!passwordField.equals("NULL") &&
			!passwordField.equals("") &&
			!passwordField.startsWith(" ") &&
			!passwordField.endsWith(" ");
	}
}
