package com.isrdc.entities;

import java.sql.Time;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="login_histories")
public class LoginHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer loginId;
	private Integer userId;
	private LocalTime loginTime;
	private LocalTime logoutTime;
	private String devicename;
	private String IPaddress;
	private String browserName;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public LocalTime getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(LocalTime loginTime) {
		this.loginTime = loginTime;
	}
	public LocalTime getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(LocalTime logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public String getIPaddress() {
		return IPaddress;
	}
	public void setIPaddress(String iPaddress) {
		IPaddress = iPaddress;
	}
	public String getBrowserName() {
		return browserName;
	}
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}
	
	
	
	

}
