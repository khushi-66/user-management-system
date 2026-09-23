package com.isrdc.utils;

import org.springframework.stereotype.Component;

@Component
public class OTPGenerator {

	public  String GenerateOTP() {
		 String otp = String.valueOf(
	                (int)(Math.random() * 9000) + 1000
	        );
		 System.out.println( " OTP : "+otp);
		return otp;
	}
}
