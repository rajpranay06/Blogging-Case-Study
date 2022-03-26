package com.example.demo.exception;

public class CommunityNotFoundException extends RuntimeException {

	public CommunityNotFoundException() {
		super();
	}

	public CommunityNotFoundException(String message) {
		super(message);
	}

	public CommunityNotFoundException(Throwable cause) {
		super(cause);
	}
	
}