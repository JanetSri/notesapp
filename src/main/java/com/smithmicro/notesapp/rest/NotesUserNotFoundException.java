package com.smithmicro.notesapp.rest;

public class NotesUserNotFoundException extends RuntimeException {

	public NotesUserNotFoundException() {
		
	}

	public NotesUserNotFoundException(String message) {
		super(message);
		
	}

	public NotesUserNotFoundException(Throwable cause) {
		super(cause);
		
	}

	public NotesUserNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public NotesUserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
