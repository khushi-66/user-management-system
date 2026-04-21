package com.isrdc.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(exception=ContactFormValidationFailedException.class)
	public ResponseEntity<ExceptionInfo>handleValidation(ContactFormValidationFailedException ex){
		ExceptionInfo e=new ExceptionInfo();
		e.setCode("EX-12398");
		e.setDateTime(LocalDateTime.now());
		e.setDesc("The Problm was "+ex.getMessage());
		
		return  new ResponseEntity<> (e,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
