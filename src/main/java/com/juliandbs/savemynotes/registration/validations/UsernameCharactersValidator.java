package com.juliandbs.savemynotes.registration.validators;

import com.juliandbs.savemynotes.registration.annotations.ValidUsernameCharacters;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UsernameCharactersValidator implements ConstraintValidator<ValidUsernameCharacters, String> {
	private static final String USERNAME_PATTERN = "^[A-Za-z0-9]*$";
	private static final Pattern PATTERN = Pattern.compile(USERNAME_PATTERN);

	@Override
	public boolean isValid(final String usernameField, final ConstraintValidatorContext context) {
		Matcher matcher = PATTERN.matcher(usernameField);
		return matcher.matches();
	}

}
