package com.isrdc.dtos;

import java.time.LocalDateTime;

import com.isrdc.entities.User;


public class NotificationDto {

	private Integer notificationId;
	
	 private String title;
	 private String message;
	 private String isRead;
	 private LocalDateTime time;
	 
	 
		
	 
	 
	 public LocalDateTime getTime() {
		return time;
	}
	 public void setTime(LocalDateTime time) {
		 this.time = time;
	 }
	 public Integer getNotificationId() {
		 return notificationId;
	 }
	 public void setNotificationId(Integer notificationId) {
		 this.notificationId = notificationId;
	 }
	 
	 public String getTitle() {
		 return title;
	 }
	 public void setTitle(String title) {
		 this.title = title;
	 }
	 public String getMessage() {
		 return message;
	 }
	 public void setMessage(String message) {
		 this.message = message;
	 }
	 public String getIsRead() {
		 return isRead;
	 }
	 public void setIsRead(String isRead) {
		 this.isRead = isRead;
	 }
	 
	 
	
}
