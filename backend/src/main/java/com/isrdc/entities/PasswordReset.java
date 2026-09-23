package com.isrdc.entities;


import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Password_Reset")
public class PasswordReset {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer resetId;
	
	private String token;
	private LocalDateTime expiryTime;
	
	@ManyToOne
	@JoinColumn(name="user_id")
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
