package com.isrdc.exceptions;

public class EmailAlreadyVerifiedException extends RuntimeException{

	public EmailAlreadyVerifiedException(String msg) {
		super(msg);
	}

}
