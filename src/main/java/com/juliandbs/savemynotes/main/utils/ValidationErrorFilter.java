package com.juliandbs.savemynotes.main.utils;

import com.juliandbs.savemynotes.registration.annotations.ValidUsername;
import com.juliandbs.savemynotes.registration.annotations.ValidUsernameCharacters;
import com.juliandbs.savemynotes.registration.annotations.ValidUsernameSize;
import com.juliandbs.savemynotes.registration.annotations.ValidEmail;
import com.juliandbs.savemynotes.registration.annotations.ValidEmailPattern;
import com.juliandbs.savemynotes.registration.annotations.PasswordMatches;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;

import java.lang.NullPointerException;

@Component("validationErrorFilter")
public class ValidationErrorFilter {
	private static List<String> usernameClassList = new LinkedList<>();
	private static List<String> emailClassList = new LinkedList<>();
	private static List<String> passwordClassList = new LinkedList<>();
	static {
		usernameClassList.add(ValidUsername.class.getSimpleName());
		usernameClassList.add(ValidUsernameCharacters.class.getSimpleName());
		usernameClassList.add(ValidUsernameSize.class.getSimpleName());
		emailClassList.add(ValidEmail.class.getSimpleName());
		emailClassList.add(ValidEmailPattern.class.getSimpleName());
		passwordClassList.add(PasswordMatches.class.getSimpleName());
	}

	public List<String> getUsernameErrors(Errors errors) throws NullPointerException {
		if (errors == null)
			throw new NullPointerException();
		return filterList(errors, usernameClassList);
	}

	public List<String> getEmailErrors(Errors errors) throws NullPointerException {
		if (errors == null)
			throw new NullPointerException();
		return filterList(errors, emailClassList);
	}

	public List<String> getPasswordErrors(Errors errors) throws NullPointerException {
		if (errors == null)
			throw new NullPointerException();
		return filterList(errors, passwordClassList);
	}

	private List<String> filterList(Errors errors, List<String> classList) {
		List<ObjectError> errorList = errors.getAllErrors();
		List<ObjectError> finalList = new LinkedList<>();
		for (String usernameClass : classList) {
			finalList.addAll(
				errorList.stream()
						.filter(e -> e.getCode().equals(usernameClass))
						.collect(Collectors.toCollection(LinkedList::new))
			);
		}
		List<String> messages = finalList.stream()
							.map( e -> e.getDefaultMessage())
							.collect(Collectors.toCollection(LinkedList::new));
		return messages;
	}
}
