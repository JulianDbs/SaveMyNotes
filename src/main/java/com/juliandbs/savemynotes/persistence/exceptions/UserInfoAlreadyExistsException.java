package com.juliandbs.savemynotes.persistence.exceptions;

import java.lang.Exception;
import java.lang.Throwable;

public class UserInfoAlreadyExistsException extends Exception {

	public UserInfoAlreadyExistsException() {
		super("UserInfo Already Exists");
	}

	public UserInfoAlreadyExistsException(String message) {
		super(message);
	}

	public UserInfoAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserInfoAlreadyExistsException(Throwable cause) {
		super(cause);
	}
}
