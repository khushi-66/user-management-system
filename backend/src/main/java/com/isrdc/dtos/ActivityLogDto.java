package com.isrdc.dtos;

import java.time.LocalDateTime;

import com.isrdc.entities.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


public class ActivityLogDto {

private Integer logId;
	
	private String action;
	private LocalDateTime time;
	
	private String description;
	
	
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
