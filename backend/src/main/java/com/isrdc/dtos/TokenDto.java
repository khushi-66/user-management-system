package com.isrdc.dtos;

import java.time.LocalDateTime;

import com.isrdc.entities.User;

import jakarta.persistence.JoinColumn;

public class TokenDto {

	private Integer tokenId;
	private String token;
	private LocalDateTime expiryTime;
	private User user;
	
	
	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(LocalDateTime expiryTime) {
		this.expiryTime = expiryTime;
	}
	public Integer getTokenId() {
		return tokenId;
	}
	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	


}
