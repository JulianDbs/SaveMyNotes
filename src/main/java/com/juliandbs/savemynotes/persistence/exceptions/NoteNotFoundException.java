package com.juliandbs.savemynotes.persistence.exceptions;

import java.lang.Exception;
import java.lang.Throwable;

public class NoteNotFoundException extends Exception {

	public NoteNotFoundException() {
		super("Note Not Found");
	}

	public NoteNotFoundException(String message) {
		super(message);
	}

	public NoteNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoteNotFoundException(Throwable cause) {
		super(cause);
	}
}
