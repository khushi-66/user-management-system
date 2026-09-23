package com.isrdc.utils;

import java.time.LocalDateTime;

public class OTPInfo {
	

	private String otp;
	private LocalDateTime expiryTime;
	
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(LocalDateTime expiryTime) {
		this.expiryTime = expiryTime;
	}
	
	

}
