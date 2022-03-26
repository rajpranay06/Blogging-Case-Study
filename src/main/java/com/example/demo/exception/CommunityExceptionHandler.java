package com.example.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.bean.CommunityErrorResponse;



@ControllerAdvice
public class CommunityExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<CommunityErrorResponse> handleException(CommunityNotFoundException exception)
	{
		CommunityErrorResponse error = new CommunityErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<CommunityErrorResponse> handleException(CommunityFoundException exception)
	{
		CommunityErrorResponse error = new CommunityErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error,HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler
	public ResponseEntity<CommunityErrorResponse> handleException(ComDescriptionNotFoundException exception)
	{
		CommunityErrorResponse error = new CommunityErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
}
