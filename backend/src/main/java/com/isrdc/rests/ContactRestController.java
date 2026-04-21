package com.isrdc.rests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isrdc.dtos.ContactDto;
import com.isrdc.exceptions.ContactFormValidationFailedException;
import com.isrdc.services.ContactService;

import jakarta.validation.Valid;

@RestController
public class ContactRestController {
	
    @Autowired
	private ContactService serv;
	
	@PostMapping("/contact")
	public String saveContact(@Valid @RequestBody  ContactDto dto,BindingResult res) {
		
		if(res.hasErrors()) {
			throw new ContactFormValidationFailedException("Contact Form Data is not validated");
		}
		serv.handleContact(dto);
		return "Ok";
		
	}
	

}
