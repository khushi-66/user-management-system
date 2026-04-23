package com.isrdc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.isrdc.entities.Contact;
@Service
public class EmailSender {
	@Autowired
	private JavaMailSender mailsender;

	public void sendEmail(Contact contact) {
		 SimpleMailMessage msg=new SimpleMailMessage();
		 msg.setFrom("khushi.cs231113@global.org.in");
		 msg.setTo("khushi.cs231113@global.org.in");
		 msg.setReplyTo(contact.getEmail());
		 msg.setText(
				 "\nName : " + contact.getName() +
			        "\nEmail: " + contact.getEmail() +
			        "\nPhone: " + contact.getPhone() +
			        "\n\nMessage:\n" + contact.getMessage()
				 
				 );
		 msg.setSubject("Query from " + contact.getName() );
		mailsender.send(msg);
	}

}
