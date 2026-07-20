package com.isrdc.rests;


import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isrdc.dtos.PasswordResetDto;
import com.isrdc.dtos.ResetPasswordDto;
import com.isrdc.dtos.RoleDto;
import com.isrdc.dtos.UserDto;
import com.isrdc.entities.Token;
import com.isrdc.entities.User;
import com.isrdc.exceptions.SignupFailedException;
import com.isrdc.jwts.JwtService;
import com.isrdc.repos.TokenRepo;
import com.isrdc.services.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
public class UserRestController {
	@Autowired
	private UserService serv;
	@Autowired
	private PasswordEncoder passenc;
	@Autowired
	private AuthenticationManager authmanager;
	@Autowired
	private JwtService jwtServ;
	@Autowired
	private TokenRepo tokRepo;
	
  @PostMapping("/signup")
  public ResponseEntity<?> signUp( @Valid @RequestBody UserDto user,BindingResult res) {
	  if(res.hasErrors()) {
		  throw new SignupFailedException(" Signup Failed Please Try Again");
	  }
	user.setPassword( passenc.encode(user.getPassword()));
	 serv.saveUser(user);
	 return ResponseEntity.ok(Map.of(
			 "Status","success",
			 "Message","Congratualations Your is account created you are signup successfully"
			 ));
  }
  
  
  @PostMapping("/signin")
  public ResponseEntity<?> signIn( @RequestBody UserDto dto) {
	 UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword());
	  Authentication auth=authmanager.authenticate(token);
	  if(auth.isAuthenticated()) {
		 String jwtToken= jwtServ.generateToken(dto.getEmail());
	  UserDto userdto=serv.findUser(dto.getEmail());
	 
	   String roleName=userdto.getRole().getName();
	  return ResponseEntity.ok(Map.of(
				 "Status","success",
				 "Message","login successfull",
				 "token",jwtToken,
				 "userid",userdto.getUserId(),
				 "role",roleName,
				 "email",userdto.getEmail(),
				 "phone",userdto.getPhone(),
				 "username",userdto.getName()
				 ));
	  }
	  
	  return ResponseEntity.ok(Map.of(
				 "Status","unsuccessfull",
				 "Message","Signin Failed !!! Unable to sign in at the moment. Please try again later."
				 ));
  }
  
  @GetMapping("/verify-email")
  public void verifyToken(@RequestParam String token,HttpServletResponse res)throws IOException {
	String isEmail= serv.verifyEmail(token);
	String location="http://localhost:5173/"+isEmail;
	res.sendRedirect(location);
  }
  
  @GetMapping("/verify-passwordtoken")
  public void verifyPasswordToken(@RequestParam String token,HttpServletResponse res)throws IOException {
	  String status = serv.verifyToken(token);
System.out.println(status);
	    if ("verified-token".equals(status)) {
	        res.sendRedirect("http://localhost:5173/change-password?token=" + token);
	    } else if ("expired-token".equals(status)) {
	        res.sendRedirect("http://localhost:5173/expired-token");
	    } else {
	        res.sendRedirect("http://localhost:5173/invalid-token");
	    }
  }
	
  @PostMapping("/resend-verificationmail")
  public ResponseEntity<?> resendVerificationMail(@RequestBody UserDto dto) {
	String email=dto.getEmail();
	System.out.println(email);
	serv.reSendVerificationMail(email);
	
	return ResponseEntity.ok(Map.of(
			 "Status","success",
			 "Message","Email sent successfully"
			 ));
  }
  
  @PostMapping("/reset-password")
  public ResponseEntity<?> resetPassword(@RequestBody UserDto dto) {
	String email=dto.getEmail();
	System.out.println(email);
	serv.SendPasswordResetMail(email);
	
	return ResponseEntity.ok(Map.of(
			 "Status","success",
			 "Message","password resetmail sent successfully"
			 ));
  }
  
  
  @PostMapping("/update-password")
  public ResponseEntity<?> UpdatePassword(@RequestBody ResetPasswordDto dto,HttpServletResponse res) throws IOException {
	String token=dto.getToken();
	String querypassword= dto.getPassword();
	System.out.println(querypassword);
	System.out.println(token);
	String status=serv.verifyToken(token);
	if ("verified-token".equals(status)) {
		UserDto userdto=serv.findUserByToken(token);
		System.out.println(userdto);
		 serv.updateUser(passenc.encode(querypassword),userdto.getUserId());
	
		return ResponseEntity.ok(Map.of(
				 "Status","success",
				 "Message","password updated successfully"
				 ));
		
	}
    else if ("expired-token".equals(status)) {
    	return ResponseEntity.ok(Map.of(
   			 "Status","expired",
   			 "Message","password expired "
   			 ));
    } else {
    	return ResponseEntity.ok(Map.of(
   			 "Status","invalid",
   			 "Message","password is invalid"
   			 ));
    }
	
  }
  }
	
  
