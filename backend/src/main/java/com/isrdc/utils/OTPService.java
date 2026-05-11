package com.isrdc.utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.isrdc.exceptions.OTPExpiredException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class OTPService {

	@Autowired
	OTPGenerator otpgenerator;
	@Value("${twilio.account.sid}")
	private String sid;
	
	@Value("${twilio.auth.token}")
	private String authtoken;
	
	@Value("${twilio.phone.number}")
	private String twilioPhone;
	
	Map<String, OTPInfo>otpstorage=new HashMap<String,OTPInfo>();
	
	
	public void sendOTP (String phone) {
		String otp=otpgenerator.GenerateOTP();
		OTPInfo otpinfo=new OTPInfo();
		otpinfo.setOtp(otp);
		otpinfo.setExpiryTime(LocalDateTime.now().plusMinutes(5));
		otpstorage.put(phone, otpinfo);
		System.out.println(otpinfo);
		
		Twilio.init( sid,authtoken);
		String msg="Hello User from Twilio 📞 Your is OTP"+otp+" to Verify Your Phone";
		Message.creator(new PhoneNumber("+91"+phone),new PhoneNumber(twilioPhone), msg).create();
		System.out.println("otp successfully sent"+otpstorage.get(phone).getOtp());
	}
	
	
	public String verify( String phone, String otp) {
		String flag ="false";
		String storageotp=otpstorage.get(phone).getOtp();
		LocalDateTime expirytime=otpstorage.get(phone).getExpiryTime();
		
		
		if(LocalDateTime.now().isAfter(expirytime)) {
			flag="Expired";
			throw new OTPExpiredException("Hey User OTP is Expired !! Please Resend Again");
		}
		else {
		
			if(otp.equals(storageotp)) {
				flag="true";
				
				}else {
					
					flag="false";
				}
			
		}
		otpstorage.remove(phone);
		
		return flag;
	}

}
