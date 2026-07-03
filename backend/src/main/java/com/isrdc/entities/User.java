package com.isrdc.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
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
	private String name;
	private String email;
	private String phone;
	private Integer roleId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime lastloginAt;
	private String password;
	private String Status;
	
	@OneToMany(mappedBy="users",cascade=CascadeType.ALL)
	private List<LoginHistory>loginHistories;
	
	@OneToMany(mappedBy="users",cascade=CascadeType.ALL)
	private List<ActivityLog>activityLogs;
	
	@OneToMany(mappedBy="users",cascade=CascadeType.ALL)
	private List< Notification>notifications;
	
	@OneToMany(mappedBy="users",cascade=CascadeType.ALL)
	private List<PasswordReset>passwordResets;
	
	@OneToOne(mappedBy="users",cascade=CascadeType.ALL)
	private UserProfile userProfile;
	
	@ManyToOne
	@JoinColumn(name="role_id")
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
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	
	
	
	
	
	
	
	

}
