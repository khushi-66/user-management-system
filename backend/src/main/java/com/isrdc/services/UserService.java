package com.isrdc.services;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isrdc.dtos.UserDto;
import com.isrdc.dtos.UserDto;
import com.isrdc.entities.LoginHistory;
import com.isrdc.entities.Role;
import com.isrdc.entities.Token;
import com.isrdc.exceptions.EmailAlreadyVerifiedException;
import com.isrdc.exceptions.InvalidTokenException;
import com.isrdc.exceptions.RoleNotFoundException;
import com.isrdc.exceptions.TokenExpiredException;
import com.isrdc.repos.ActivityLogRepo;
import com.isrdc.repos.LoginHistoryRepo;
import com.isrdc.repos.NotificationRepo;
import com.isrdc.repos.PasswordResetRepo;
import com.isrdc.repos.RoleRepo;
import com.isrdc.repos.TokenRepo;
import com.isrdc.repos.UserProfileRepo;
import com.isrdc.repos.UserRepo;
import com.isrdc.utils.EmailSender;
import com.isrdc.utils.TokenGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserProfileRepo userprofileRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private PasswordResetRepo passwordResetRepo;
	@Autowired
	private NotificationRepo notificationRepo;
	@Autowired
	private LoginHistoryRepo loginHistoryRepo;
	@Autowired
	private ActivityLogRepo activityRepo;
	@Autowired
	private EmailSender sender;
	@Autowired
	private TokenGenerator tokGen;
	@Autowired
	private TokenRepo tokRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException {
	com.isrdc.entities.User user= userRepo.findByEmail(email);
	
	       if(user == null) {
	    	      throw new UsernameNotFoundException("User not found");
	       }
	       
		return new User(user.getEmail(),user.getPassword(),Collections.emptyList());
		
	}
	
	public void saveUser(UserDto dto) {
		com.isrdc.entities.User user=new com.isrdc.entities.User();
		Role role=roleRepo.findByName("USER");
		String token=tokGen.generateToken();
		System.out.println("Token = "+token);
		if(role==null) {
			throw new RoleNotFoundException("Role not found");
		}
	    Token tokenObj=new Token();
		tokenObj.setToken(token);
		
		tokenObj.setExpiryTime(LocalDateTime.now().plusHours(24));
		BeanUtils.copyProperties(dto, user);
		user.setRole(role);
		user.setStatus("InActive");
		tokenObj.setUser(user);
		user.setToken(tokenObj);
		 userRepo.save(user);
		 tokRepo.save(tokenObj);
		  sender.sendVerificationMail(dto.getEmail(), dto.getName(), token);
	}
	
	public void reSendVerificationMail(String email) {
		com.isrdc.entities.User user=userRepo.findByEmail(email);
		System.out.println(user);
		if(user==null){
		    throw new UsernameNotFoundException("User not found");
		}
		
		String token=tokGen.generateToken();
		System.out.println("Token = "+token);
		 Token tokenObj=tokRepo.findByUser(user);
			tokenObj.setToken(token);
			tokenObj.setExpiryTime(LocalDateTime.now().plusHours(24));
			user.setStatus("InActive");
			tokenObj.setUser(user);
			user.setToken(tokenObj);
			 userRepo.save(user);
			 sender.sendVerificationMail(user.getEmail(), user.getName(), token);
	}
	
	
	
public String verifyEmail(String token) {
	Token tokObj=tokRepo.findByToken(token);
	String emailToken;
	com.isrdc.entities.User user=tokObj.getUser();
	if(tokObj == null) {
		emailToken="invalid";
		
	}
		
	 else if(LocalDateTime.now().isAfter(tokObj.getExpiryTime())) {
		 emailToken="expired";
		
	 }
	 else if("Active".equals(tokObj.getUser().getStatus())) {
		 emailToken="alreadyverified";
		
	}else {
		emailToken="verified";
		user.setStatus("Active");
		}
	user.setToken(null);
	tokObj.setUser(null);
	userRepo.save(user);
	tokRepo.delete(tokObj);
	 return emailToken;
	 
}
}
