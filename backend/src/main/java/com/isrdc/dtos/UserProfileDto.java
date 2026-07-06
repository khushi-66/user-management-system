package com.isrdc.dtos;



import java.time.LocalDateTime;

import com.isrdc.entities.User;



public class UserProfileDto {

	 private Integer userId;
	 private LocalDateTime dob;
	 private String address;
	 private String profileUrl;
	 private String bio;
	 private String profession;
	 private Integer age;
	 
		private User user;
		
	 public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
	 public Integer getUserId() {
		 return userId;
	 }
	 public void setUserId(Integer userId) {
		 this.userId = userId;
	 }
	 public LocalDateTime getDob() {
		 return dob;
	 }
	 public void setDob(LocalDateTime dob) {
		 this.dob = dob;
	 }
	 public String getAddress() {
		 return address;
	 }
	 public void setAddress(String address) {
		 this.address = address;
	 }
	 public String getProfileUrl() {
		 return profileUrl;
	 }
	 public void setProfileUrl(String profileUrl) {
		 this.profileUrl = profileUrl;
	 }
	 public String getBio() {
		 return bio;
	 }
	 public void setBio(String bio) {
		 this.bio = bio;
	 }
	 public String getProfession() {
		 return profession;
	 }
	 public void setProfession(String profession) {
		 this.profession = profession;
	 }
	 public Integer getAge() {
		 return age;
	 }
	 public void setAge(Integer age) {
		 this.age = age;
	 }
	 

}
