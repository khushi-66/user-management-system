package com.isrdc.exceptions;


import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ContactFormValidationFailedException.class)
	public ResponseEntity<?>handleValidation(ContactFormValidationFailedException ex){
		
		return ResponseEntity.badRequest().body(
	            Map.of(
	                "status", "error",
	                "message", ex.getMessage()
	            )
	        );
	}
	
	
	
	@ExceptionHandler(RateLimitException.class)
	public ResponseEntity<?>handleERateLimit(RateLimitException ex){
		
		return ResponseEntity.status(429).body(
	            Map.of(
	                "status", "error",
	                "message", ex.getMessage()
	            )
	        );
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGeneric(Exception ex) {

	    return ResponseEntity.status(500).body(
	        Map.of(
	            "status", "error",
	            "message", "Internal Server Error"
	        )
	    );
	}
}
