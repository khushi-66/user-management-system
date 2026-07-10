package com.isrdc.rests;


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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isrdc.dtos.UserDto;
import com.isrdc.entities.Token;
import com.isrdc.entities.User;
import com.isrdc.exceptions.SignupFailedException;
import com.isrdc.jwts.JwtService;
import com.isrdc.repos.TokenRepo;
import com.isrdc.services.UserService;

import jakarta.validation.Valid;

@Controller
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
		 String tok= jwtServ.generateToken(dto.getEmail());
	  
	  return ResponseEntity.ok(Map.of(
				 "Status","success",
				 "Message",tok
				 ));
	  
	  }
	  
	  return ResponseEntity.ok(Map.of(
				 "Status","unsuccessfull",
				 "Message","Signin Failed !!! Unable to sign in at the moment. Please try again later."
				 ));
  }
  
  @GetMapping("/verify-email")
  public String verifyToken(@RequestParam String token) {
	String isEmail= serv.verifyEmail(token);
	
	return isEmail;
  }
	

}
