package com.example.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.bean.UserErrorResponse;

@ControllerAdvice
public class UserExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException exception) {
		UserErrorResponse error = new UserErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMsg(exception.getMessage());
		//error.setTimestamp(System.currentTimeMillis());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);  //404 Not found
	}
}

