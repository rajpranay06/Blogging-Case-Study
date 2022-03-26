package com.example.demo.exception;

public class CommunityFoundException extends RuntimeException {

	public CommunityFoundException() {
		super();
	}

	public CommunityFoundException(String message) {
		super(message);
		
	}

	public CommunityFoundException(Throwable cause) {
		super(cause);
		
	}
	
}