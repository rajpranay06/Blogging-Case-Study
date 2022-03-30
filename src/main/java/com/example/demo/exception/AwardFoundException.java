package com.example.demo.exception;

public class AwardFoundException extends RuntimeException{

	public AwardFoundException() {
		super();
		
	}


	public AwardFoundException(String message) {
		super(message);
		
	}

	public AwardFoundException(Throwable cause) {
		super(cause);
		
	}
	

}
