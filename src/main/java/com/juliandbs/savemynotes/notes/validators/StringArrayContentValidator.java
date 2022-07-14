package com.juliandbs.savemynotes.notes.validations;

import com.juliandbs.savemynotes.notes.annotations.ValidStringArrayContent;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringArrayContentValidator implements ConstraintValidator<ValidStringArrayContent, String[]> {

	@Override
	public void initialize(ValidStringArrayContent validStringArrayContent) {}

	@Override
	public boolean isValid(String[] content, ConstraintValidatorContext context) {
		return content != null;
	}

}
