package com.juliandbs.savemynotes.persistence.exceptions;

import java.lang.Exception;
import java.lang.Throwable;

public class NoteAlreadyExistsException extends Exception {

	public NoteAlreadyExistsException() {
		super("Note Already Exists");
	}

	public NoteAlreadyExistsException(String message) {
		super(message);
	}

	public NoteAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoteAlreadyExistsException(Throwable cause) {
		super(cause);
	}

}
