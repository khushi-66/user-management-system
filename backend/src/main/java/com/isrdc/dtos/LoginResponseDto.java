package com.isrdc.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.isrdc.entities.UserProfile;

public class LoginResponseDto {

	private Integer userId;
	private String role;
	private String username;
	private String accountStatus;
	private String responseMsg;
	private String status;
	private UserProfileDto userProfile;
	private List<NotificationDto>notifications;
	private String email;
	private String phone;
	private LocalDateTime lastLogin;
	private String token;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getResponseMsg() {
		return responseMsg;
	}
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public UserProfileDto getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfileDto userProfile) {
		this.userProfile = userProfile;
	}
	public List<NotificationDto> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<NotificationDto> notifications) {
		this.notifications = notifications;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	

}
