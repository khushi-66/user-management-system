package com.isrdc.dtos;

import java.sql.Time;
import java.time.LocalTime;

import com.isrdc.entities.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PasswordResetDto {

	private Integer resetId;
	private Integer userId;
	private String token;
	private LocalTime expiryTime;
	
	
	private User user;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getResetId() {
		return resetId;
	}
	public void setResetId(Integer resetId) {
		this.resetId = resetId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalTime getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(LocalTime expiryTime) {
		this.expiryTime = expiryTime;
	}
	
	
}
