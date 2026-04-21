package com.isrdc.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isrdc.dtos.ContactDto;
import com.isrdc.entities.Contact;
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
		
		repo.save(contact);
		sender.sendEmail(contact);
		
	}

}
