package com.isrdc.entities;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="user_profiles")
public class UserProfile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Integer profileId;
	 private LocalDate dob;
	 private String address;
	 private String profileUrl;
	 private String bio;
	 private String profession;
	 private Integer age;
	 @OneToOne
		@JoinColumn(name="user_id")
		private User user;
	 
	 
	 public User getUser() {
		return user;
	}
	 public void setUser(User user) {
		 this.user = user;
	 }
	 
	 public Integer getProfileId() {
		return profileId;
	}
	 public void setProfileId(Integer profileId) {
		 this.profileId = profileId;
	 }
	 public LocalDate getDob() {
		 return dob;
	 }
	 public void setDob(LocalDate dob) {
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
