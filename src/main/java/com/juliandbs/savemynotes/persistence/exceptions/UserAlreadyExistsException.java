package com.juliandbs.savemynotes.persistence.exceptions;

import java.lang.Exception;
import java.lang.Throwable;

public class UserAlreadyExistsException extends Exception {

	public UserAlreadyExistsException() {super("User Already Exists");}

	public UserAlreadyExistsException(String message) {super(message);}

	public UserAlreadyExistsException(String message, Throwable cause) {super(message, cause);}

	public UserAlreadyExistsException(Throwable cause) {super(cause);}
}
