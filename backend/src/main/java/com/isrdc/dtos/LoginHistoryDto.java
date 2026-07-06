package com.isrdc.dtos;


import java.time.LocalDateTime;
import java.time.LocalTime;

import com.isrdc.entities.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



public class LoginHistoryDto {

private Integer loginId;
	
	private LocalDateTime loginTime;
	private LocalDateTime logoutTime;
	private String devicename;
	private String IPaddress;
	private String browserName;
	
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
	
	public LocalDateTime getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}
	public LocalDateTime getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(LocalDateTime logoutTime) {
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
