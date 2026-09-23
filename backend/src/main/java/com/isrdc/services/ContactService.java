package com.isrdc.services;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isrdc.dtos.ContactDto;
import com.isrdc.entities.Contact;
import com.isrdc.exceptions.RateLimitException;
import com.isrdc.repos.ContactRepo;
import com.isrdc.utils.EmailSender;



@Service
public class ContactService {

	@Autowired
	private ContactRepo repo;
	
	@Autowired
	private EmailSender sender;
	
	public void handleContact(ContactDto con) {
		
		Contact contact=new Contact();
		BeanUtils.copyProperties(con, contact);
		
		LocalDateTime onehourseago=LocalDateTime.now().minusHours(1);
		int count=repo.countByEmailAndCreateAtAfter(con.getEmail(), onehourseago);
		System.out.println(count);
		if(count>5) {
		throw new RateLimitException("You’ve reached the maximum number of messages allowed for now. Please try again after 1 hours.");
		}
		repo.save(contact);
		sender.sendEmail(contact);
		
	}

	
}
