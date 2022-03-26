package com.example.demo.exception;

public class PostTypeInvalidException extends RuntimeException{

	public PostTypeInvalidException() {
		super();
	}

	public PostTypeInvalidException(String arg0) {
		super(arg0);
	}

	public PostTypeInvalidException(Throwable arg0) {
		super(arg0);
	}

}
