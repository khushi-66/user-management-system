package com.isrdc.repos;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isrdc.entities.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer> {

	public int countByEmailAndCreateAtAfter(String email,LocalDateTime onehourseago);

	public boolean existsByEmail(String email);
}
