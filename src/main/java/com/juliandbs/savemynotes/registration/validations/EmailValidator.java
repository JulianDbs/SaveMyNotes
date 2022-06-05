package com.juliandbs.savemynotes.registration.validations;

import com.juliandbs.savemynotes.registration.annotations.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

	@Override
	public void initialize(ValidEmail validEmail) {}

	@Override
	public boolean isValid(String emailField, ConstraintValidatorContext context) {
		return emailField != null &&
			!emailField.equals("null") &&
			!emailField.equals("Null") &&
			!emailField.equals("") &&
			!emailField.startsWith(" ") &&
			!emailField.endsWith(" ");
	}
}
