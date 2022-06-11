package com.juliandbs.savemynotes.persistence.exceptions;

import java.lang.Exception;
import java.lang.Throwable;

public class UserNotFoundException extends Exception {

	public UserNotFoundException() {super("User Not Found");}

	public UserNotFoundException(String message) {super(message);}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(Throwable cause) {super(cause);}
}
