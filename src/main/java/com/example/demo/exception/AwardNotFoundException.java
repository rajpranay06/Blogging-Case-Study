package com.example.demo.exception;

public class AwardNotFoundException extends RuntimeException {

	public AwardNotFoundException() {
		super();
	}

	public AwardNotFoundException(String message) {
		super(message);
	}

	public AwardNotFoundException(Throwable cause) {
		super(cause);
	}

}
