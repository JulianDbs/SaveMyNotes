package com.juliandbs.savemynotes.main.utils;

import com.juliandbs.savemynotes.registration.annotations.ValidUsername;
import com.juliandbs.savemynotes.registration.annotations.ValidUsernameCharacters;
import com.juliandbs.savemynotes.registration.annotations.ValidUsernameSize;
import com.juliandbs.savemynotes.registration.annotations.ValidEmail;
import com.juliandbs.savemynotes.registration.annotations.ValidEmailPattern;
import com.juliandbs.savemynotes.registration.annotations.PasswordMatches;
import com.juliandbs.savemynotes.registration.annotations.ValidPassword;
import com.juliandbs.savemynotes.registration.annotations.ValidPasswordSize;
import com.juliandbs.savemynotes.registration.annotations.ValidMatchingPassword;
import com.juliandbs.savemynotes.registration.annotations.ValidMatchingPasswordSize;
import com.juliandbs.savemynotes.notes.annotations.ValidTitle;
import com.juliandbs.savemynotes.notes.annotations.ValidStringArrayContent;
import com.juliandbs.savemynotes.notes.annotations.ValidStringContent;

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
	private static List<String> matchingPasswordClassList = new LinkedList<>();
	private static List<String> titleNoteClassList = new LinkedList<>();
	private static List<String> contentNoteClassList = new LinkedList<>();
	static {
		usernameClassList.add(ValidUsername.class.getSimpleName());
		usernameClassList.add(ValidUsernameCharacters.class.getSimpleName());
		usernameClassList.add(ValidUsernameSize.class.getSimpleName());
		emailClassList.add(ValidEmail.class.getSimpleName());
		emailClassList.add(ValidEmailPattern.class.getSimpleName());
		passwordClassList.add(PasswordMatches.class.getSimpleName());
		passwordClassList.add(ValidPassword.class.getSimpleName());
		passwordClassList.add(ValidPasswordSize.class.getSimpleName());
		matchingPasswordClassList.add(PasswordMatches.class.getSimpleName());
		matchingPasswordClassList.add(ValidMatchingPassword.class.getSimpleName());
		matchingPasswordClassList.add(ValidMatchingPasswordSize.class.getSimpleName());
		titleNoteClassList.add(ValidTitle.class.getSimpleName());
		contentNoteClassList.add(ValidStringArrayContent.class.getSimpleName());
		contentNoteClassList.add(ValidStringContent.class.getSimpleName());
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

	public List<String> getMatchingPasswordErrors(Errors errors) throws NullPointerException {
		if (errors == null)
			throw new NullPointerException();
		return filterList(errors, matchingPasswordClassList);
	}

	public List<String> getTitleNoteErrors(Errors errors) throws NullPointerException {
		if (errors == null)
			throw new NullPointerException();
		return filterList(errors, titleNoteClassList);
	}

	public List<String> getContentNoteErrors(Errors errors) throws NullPointerException {
		if (errors == null)
			throw new NullPointerException();
		return filterList(errors, contentNoteClassList);
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
