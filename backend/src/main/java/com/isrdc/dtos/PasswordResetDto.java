package com.isrdc.dtos;


import java.time.LocalDateTime;

import com.isrdc.entities.User;



public class PasswordResetDto {

	private Integer resetId;
	
	private String token;
	private LocalDateTime expiryTime;
	
	
		public Integer getResetId() {
		return resetId;
	}
	public void setResetId(Integer resetId) {
		this.resetId = resetId;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(LocalDateTime expiryTime) {
		this.expiryTime = expiryTime;
	}
	
	
}
