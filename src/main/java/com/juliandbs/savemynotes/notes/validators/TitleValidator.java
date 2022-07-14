package com.juliandbs.savemynotes.notes.validators;

import com.juliandbs.savemynotes.notes.annotations.ValidTitle;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TitleValidator implements ConstraintValidator<ValidTitle, String> {

	@Override
	public void initialize(ValidTitle validTitle) {}

	@Override
	public boolean isValid(String titleField, ConstraintValidatorContext context) {
		return titleField != null &&
			!titleField.equals("null") &&
			!titleField.equals("Null") &&
			!titleField.equals("") &&
			!titleField.startsWith(" ") &&
			!titleField.endsWith(" ");
	}
}
