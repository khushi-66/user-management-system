package com.isrdc.services;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isrdc.dtos.RoleDto;
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
	
	public void updateUser(String password,Integer id ) {
		Optional<com.isrdc.entities.User>u= userRepo.findById(id);
		 if (u.isPresent()) {
			try {
			 com.isrdc.entities.User user= u.get();
			 System.out.println(user.getPassword());
				user.setPassword(password);
				System.out.println(user.getPassword());
				userRepo.save(user);
				System.out.println("Saved");
				Token tok = tokRepo.findByUser(user);
				user.setToken(null);
				tok.setUser(null);
                userRepo.save(user);
				tokRepo.delete(tok);
			}catch(Exception e) {
					e.printStackTrace();
				}
		 }else {
			 throw new RuntimeException("User not found ");
		 }
		 
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
	
	public UserDto findUser(String email) {
		com.isrdc.entities.User  user=userRepo.findByEmail(email);
		UserDto userdto=new UserDto();
		BeanUtils.copyProperties(user, userdto);
		return userdto;
	}
	
	public UserDto findUserByToken(String token) {
		Token tokObj=tokRepo.findByToken(token);
		com.isrdc.entities.User user=tokObj.getUser();
		UserDto userdto=new UserDto();
		BeanUtils.copyProperties(user, userdto);
		return  userdto;
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
	
	public void SendPasswordResetMail(String email) {
		com.isrdc.entities.User user=userRepo.findByEmail(email);
		System.out.println(user);
		if(user==null){
		    throw new UsernameNotFoundException("User not found");
		}
		String token=tokGen.generateToken();
		System.out.println("Token = "+token);
		Token dbtokenObj=tokRepo.findByUser(user);
		
		if(dbtokenObj==null) {
			Token tokenObj=new Token();
			 tokenObj.setToken(token);
			tokenObj.setExpiryTime(LocalDateTime.now().plusMinutes(15));
			tokenObj.setUser(user);
			user.setToken(tokenObj);
			 userRepo.save(user);
			 System.out.println("user saved....");
			 System.out.println(tokenObj);
			 System.out.println(user);
			 sender.sendPasswordResetMail(user.getEmail(), user.getName(), token);
			 System.out.println("email sent");
			 }else {
				 
				 dbtokenObj.setToken(token);
					dbtokenObj.setExpiryTime(LocalDateTime.now().plusMinutes(15));
					dbtokenObj.setUser(user);
					user.setToken(dbtokenObj);
					 userRepo.save(user);
					 
					 System.out.println("user saved....");
					 System.out.println(dbtokenObj);
					 System.out.println(user);
					 sender.sendPasswordResetMail(user.getEmail(), user.getName(), token);
					 System.out.println("email sent");
				 
			 }
	}
	
	
public String verifyEmail(String token) {
	Token tokObj=tokRepo.findByToken(token);
	String emailToken;
	com.isrdc.entities.User user=tokObj.getUser();
	if(tokObj == null) {
		emailToken="invalid-email";
		
	}
		
	 else if(LocalDateTime.now().isAfter(tokObj.getExpiryTime())) {
		 emailToken="expired-email";
		
	 }
	 else if("Active".equals(tokObj.getUser().getStatus())) {
		 emailToken="alreadyverified-email";
		
	}else {
		emailToken="verified-email";
		user.setStatus("Active");
		}
	user.setToken(null);
	tokObj.setUser(null);
	userRepo.save(user);
	tokRepo.delete(tokObj);
	 return emailToken;
	 
}


public String verifyToken(String token) {

    Token tokObj = tokRepo.findByToken(token);
    System.out.println(tokObj);
    System.out.println(token);
    
    if (tokObj == null) {
        return "invalid-token";
    }
    com.isrdc.entities.User user=tokObj.getUser();
    if (LocalDateTime.now().isAfter(tokObj.getExpiryTime())) {

    	user.setToken(null);
    	tokObj.setUser(null);
    	userRepo.save(user);
    	tokRepo.delete(tokObj);   
        return "expired-token";
    }

    return "verified-token";
}

}
