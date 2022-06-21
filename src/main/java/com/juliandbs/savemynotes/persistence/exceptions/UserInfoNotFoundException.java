package com.juliandbs.savemynotes.persistence.exceptions;

import java.lang.Exception;
import java.lang.Throwable;

public class UserInfoNotFoundException extends Exception {

	public UserInfoNotFoundException() {
		super("UserInfo Not Found");
	}

	public UserInfoNotFoundException(String message) {
		super(message);
	}

	public UserInfoNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserInfoNotFoundException(Throwable cause) {
		super(cause);
	}
}
