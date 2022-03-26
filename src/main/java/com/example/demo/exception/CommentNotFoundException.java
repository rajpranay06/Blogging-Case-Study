package com.example.demo.exception;

public class CommentNotFoundException extends RuntimeException{

	public CommentNotFoundException() {
		super();
	}

	public CommentNotFoundException(String message) {
		super(message);
	}

	public CommentNotFoundException(Throwable cause) {
		super(cause);
	}
}
