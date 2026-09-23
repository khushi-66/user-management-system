package com.isrdc.exceptions;


import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<?>handleTokenExpiration(TokenExpiredException ex){
		
		return ResponseEntity.badRequest().body(
	            Map.of(
	                "status", "error",
	                "message", ex.getMessage()
	            )
	        );
	}
	
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<?>handleInvalidToken(InvalidTokenException ex){
		
		return ResponseEntity.badRequest().body(
	            Map.of(
	                "status", "error",
	                "message", ex.getMessage()
	            )
	        );
	}
	
	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<?>handleRole(RoleNotFoundException ex){
		
		return ResponseEntity.badRequest().body(
	            Map.of(
	                "status", "error",
	                "message", ex.getMessage()
	            )
	        );
	}
	
	
	@ExceptionHandler(EmailAlreadyVerifiedException.class)
	public ResponseEntity<?>handleverifiedEmail(EmailAlreadyVerifiedException ex){
		
		return ResponseEntity.badRequest().body(
	            Map.of(
	                "status", "error",
	                "message", ex.getMessage()
	            )
	        );
	}
	
	
	@ExceptionHandler(FormValidationFailedException.class)
	public ResponseEntity<?>handleValidation(FormValidationFailedException ex){
		
		return ResponseEntity.badRequest().body(
	            Map.of(
	                "status", "error",
	                "message", ex.getMessage()
	            )
	        );
	}
	
	@ExceptionHandler(OTPExpiredException.class)
	public ResponseEntity<?>handleOTPExpiration(OTPExpiredException ex){
		return ResponseEntity.badRequest().body(
				Map.of(
						"status","error"
							
							, "message",ex.getMessage()
						
				));
	}
	
	@ExceptionHandler(RateLimitException.class)
	public ResponseEntity<?>handleOTPMismatch(RateLimitException ex){
		
		return ResponseEntity.status(429).body(
	            Map.of(
	                "status", "error",
	                "message", ex.getMessage()
	            )
	        );
	}
	
	@ExceptionHandler(InvalidOTPException.class)
	public ResponseEntity<?>handleERateLimit(InvalidOTPException ex){
		
		return ResponseEntity.status(429).body(
	            Map.of(
	                "status", "error",
	                "message", ex.getMessage()
	            )
	        );
	}
	
	@ExceptionHandler(SigninFailedException.class)
	public ResponseEntity<?>handleSignin(SigninFailedException ex){
		
		return ResponseEntity.badRequest().body(
	            Map.of(
	                "status", "error",
	                "message", ex.getMessage()
	            )
	        );
	}
	
	@ExceptionHandler(SignupFailedException.class)
	public ResponseEntity<?>handleSignup(SignupFailedException ex){
		
		return ResponseEntity.badRequest().body(
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
	            "message", "Oops! Something went wrong on our end. Please try again later"
	        )
	    );
	}
}
