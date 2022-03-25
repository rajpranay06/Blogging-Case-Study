package com.example.demo.exception;

public class PostIdNotFoundException extends RuntimeException{

	public PostIdNotFoundException() {
		super();
	}

	public PostIdNotFoundException(String message) {
		super(message);
	}

	public PostIdNotFoundException(Throwable msg) {
		super(msg);
	}

}
