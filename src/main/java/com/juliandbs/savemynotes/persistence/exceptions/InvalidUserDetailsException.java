package com.juliandbs.savemynotes.persistence.exceptions;

import java.lang.Exception;
import java.lang.Throwable;

public class InvalidUserDetailsException extends Exception {

	public InvalidUserDetailsException() {super("The UserDetails contains empty or null properties");}

	public InvalidUserDetailsException(String message) {super(message);}

	public InvalidUserDetailsException(String message, Throwable cause) {super(message, cause);}

	public InvalidUserDetailsException(Throwable cause) {super(cause);}
}
