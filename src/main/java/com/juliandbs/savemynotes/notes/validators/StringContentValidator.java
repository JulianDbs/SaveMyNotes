package com.juliandbs.savemynotes.notes.validators;

import com.juliandbs.savemynotes.notes.annotations.ValidStringContent;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringContentValidator implements ConstraintValidator<ValidStringContent, String> {

	@Override
	public void initialize(ValidStringContent validStringContent) {}

	@Override
	public boolean isValid(String stringContent, ConstraintValidatorContext context) {
		return stringContent != null;
	}
}
