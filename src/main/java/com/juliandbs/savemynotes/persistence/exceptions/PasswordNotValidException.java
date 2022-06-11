package com.juliandbs.savemynotes.persistence.exceptions;

import java.lang.Exception;
import java.lang.Throwable;

public class PasswordNotValidException extends Exception {

	public PasswordNotValidException() {super("Password Not Valid");}

	public PasswordNotValidException(String message) {super(message);}

	public PasswordNotValidException(String message, Throwable cause) {super(message, cause);}

	public PasswordNotValidException(Throwable cause) {super(cause);}


}
