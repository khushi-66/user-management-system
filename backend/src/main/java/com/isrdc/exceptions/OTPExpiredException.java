package com.isrdc.exceptions;

public class OTPExpiredException  extends RuntimeException{

	public OTPExpiredException(String msg) {
		super(msg);
	}

}
