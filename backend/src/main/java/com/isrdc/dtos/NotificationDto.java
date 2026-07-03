package com.isrdc.dtos;

import com.isrdc.entities.User;


public class NotificationDto {

	private Integer notificationId;
	 private Integer userId;
	 private String title;
	 private String message;
	 private Boolean isRead;
	 
		private User user;
	 
	 public User getUser() {
		return user;
	}
	 public void setUser(User user) {
		 this.user = user;
	 }
	 public Integer getNotificationId() {
		 return notificationId;
	 }
	 public void setNotificationId(Integer notificationId) {
		 this.notificationId = notificationId;
	 }
	 public Integer getUserId() {
		 return userId;
	 }
	 public void setUserId(Integer userId) {
		 this.userId = userId;
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
	 public Boolean getIsRead() {
		 return isRead;
	 }
	 public void setIsRead(Boolean isRead) {
		 this.isRead = isRead;
	 }
	 
	 
	
}
