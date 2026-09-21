package com.isrdc.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="notifications")
public class Notification {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Integer notificationId;
	private LocalDateTime time;
	 private String title;
	 private String message;
	 private String isRead;
	 @ManyToOne
		@JoinColumn(name="user_id")
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
	 public LocalDateTime getTime() {
		 return time;
	 }
	 public void setTime(LocalDateTime time) {
		 this.time = time;
	 }
	 
	 
	

}
