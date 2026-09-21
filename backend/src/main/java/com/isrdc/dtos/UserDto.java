package com.isrdc.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.isrdc.entities.ActivityLog;
import com.isrdc.entities.LoginHistory;
import com.isrdc.entities.Notification;
import com.isrdc.entities.PasswordReset;
import com.isrdc.entities.Role;
import com.isrdc.entities.UserProfile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;




public class UserDto {
	private Integer userId;
	private String name;
	private String email;
	private String phone;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime lastloginAt;
	private String password;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public LocalDateTime getLastloginAt() {
		return lastloginAt;
	}
	public void setLastloginAt(LocalDateTime lastloginAt) {
		this.lastloginAt = lastloginAt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
