package com.isrdc.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userId;
	@Column(nullable=false)
	private String name;
	@Column(unique=true ,nullable=false)
	private String email;
	@Column(unique=true ,nullable=false)
	private String phone;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	private LocalDateTime lastloginAt;
	@Column(nullable=false ,length=100)
	private String password;
	@Column(nullable=false ,columnDefinition="varchar(20) default 'inactive'")
	private String status;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<LoginHistory>loginHistories;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<ActivityLog>activityLogs;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List< Notification>notifications;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<PasswordReset>passwordResets;
	
	@OneToOne(mappedBy="user",cascade=CascadeType.ALL)
	private UserProfile userProfile;
	
	
	@ManyToOne
	@JoinColumn(name="role_id",nullable=false)
	private Role role;
	
	
	
	public List<LoginHistory> getLoginHistories() {
		return loginHistories;
	}
	public void setLoginHistories(List<LoginHistory> loginHistories) {
		this.loginHistories = loginHistories;
	}
	public List<ActivityLog> getActivityLogs() {
		return activityLogs;
	}
	public void setActivityLogs(List<ActivityLog> activityLogs) {
		this.activityLogs = activityLogs;
	}
	public List<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	public List<PasswordReset> getPasswordResets() {
		return passwordResets;
	}
	public void setPasswordResets(List<PasswordReset> passwordResets) {
		this.passwordResets = passwordResets;
	}
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
	
	
	

}
