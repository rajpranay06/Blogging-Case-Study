package com.example.demo.exception;

public class ComDescriptionNotFoundException extends RuntimeException {

	public ComDescriptionNotFoundException() {
		super();
	}

	public ComDescriptionNotFoundException(String message) {
		super(message);
	}

	public ComDescriptionNotFoundException(Throwable cause) {
		super(cause);
	}

}
