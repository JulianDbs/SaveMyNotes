package com.juliandbs.savemynotes.registration.validations;

import com.juliandbs.savemynotes.registration.annotations.ValidEmailPattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailPatternValidator implements ConstraintValidator<ValidEmailPattern, String> {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +  "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

	@Override
	public boolean isValid(final String emailField, final ConstraintValidatorContext context) {
		return (validateEmail(emailField));
	}

	private boolean validateEmail(final String email) {
		Matcher matcher = PATTERN.matcher(email);
		return matcher.matches();
	}
}
