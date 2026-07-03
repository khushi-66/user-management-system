package com.isrdc.rests;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isrdc.exceptions.OTPExpiredException;
import com.isrdc.services.OTPService;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class OTPRestController {

	@Autowired
	private OTPService otpserv;
	
	@GetMapping("/send-otp")
	public String sendOTP(@RequestParam("phone")String phone) {
		otpserv.sendOTP(phone);
		 return "OTP sent Successfully.........";
	}
	
	@GetMapping("/verify-otp")
	public ResponseEntity<?> verifyOTP(@RequestParam String phone, @RequestParam String otp) {
		
	boolean isVerifiedOTP=	otpserv.verify(phone,otp);
	System.out.println(isVerifiedOTP);
		
		return  ResponseEntity.ok(
				((Map.of(
						 "status","success","msg","OTP verified Successfully"))
				
				)
				

		);
	}

}
