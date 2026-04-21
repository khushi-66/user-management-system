package com.isrdc.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isrdc.entities.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer> {

	
}
