package com.juliandbs.savemynotes.persistence.exceptions;

import java.lang.Exception;
import java.lang.Throwable;

public class UnauthorizedUserException extends Exception {

	public UnauthorizedUserException() {super("The user is not authorized to access this note.");}

	public UnauthorizedUserException(String message) {super(message);}

	public UnauthorizedUserException(String message, Throwable cause) {super(message, cause);}

	public UnauthorizedUserException(Throwable cause) {super(cause);}
}
