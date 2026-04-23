package com.isrdc.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="contact_messages")
public class Contact {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer contactId;

@NotBlank(message="Name is required")
@Size(min=3,max=20,message="Name can be 3 to 20 characters")
private String name;

@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number")
private String phone;

@NotBlank(message = "Email required")
@Email(message = "Invalid email")

private String email;

@NotBlank(message = "Message is  required")
@Size(min=10,max=500,message = "message must be 10 to 500 charaters")
private String message;

@NotBlank(message = "Subject is  required")
private String subject;

private LocalDateTime createAt=LocalDateTime.now();

public Integer getContactId() {
	return contactId;
}
public void setContactId(Integer contactId) {
	this.contactId = contactId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public LocalDateTime getCreateAt() {
	return createAt;
}
public void setCreateAt(LocalDateTime createAt) {
	this.createAt = createAt;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

	

}
