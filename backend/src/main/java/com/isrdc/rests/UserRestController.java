package com.isrdc.rests;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isrdc.exceptions.OTPExpiredException;
import com.isrdc.utils.OTPService;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class UserRestController {

	@Autowired
	private OTPService otpserv;
	
	@GetMapping("/send-otp")
	public String sendOTP(@RequestParam("phone")String phone) {
		otpserv.sendOTP(phone);
		 return "OTP sent Successfully.........";
	}
	
	@GetMapping("/verify-otp")
	public ResponseEntity<?> verifyOTP(@RequestParam String phone, @RequestParam String otp) {
		try {
	String isVerifiedOTP=	otpserv.verify(phone,otp);
		}catch(Exception e) {
			throw new OTPExpiredException("Hey User OTP is Expired !! Please Resend Again");
		}
		return  ResponseEntity.ok(
				((Map.of(
						 "status","success","msg","OTP sent Successfully"))
				
				)
				

		);
	}

}
