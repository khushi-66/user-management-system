package com.isrdc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.isrdc.entities.Contact;

import jakarta.mail.internet.MimeMessage;
@Service
public class EmailSender {
	@Autowired
	private JavaMailSender mailsender;
  @Autowired
  private EmailPage ePage;
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
	
	public void sendVerificationMail(String to,String username,String token) {
		String link="http://localhost:9090/verify-email?token="+token; 
		String subject="Verify your Email address";
		try {
			MimeMessage mimemsg=mailsender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimemsg,true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(ePage.wlcPage(username,link),true);
		mailsender.send(mimemsg);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		
		
	}

}
