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
import com.isrdc.entities.ActivityLog;
import com.isrdc.entities.LoginHistory;
import com.isrdc.entities.Notification;
import com.isrdc.entities.Role;
import com.isrdc.entities.Token;
import com.isrdc.entities.UserProfile;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;
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
	
	
	
	public void signupUser(UserDto dto) {
		com.isrdc.entities.User user=new com.isrdc.entities.User();
		Role role=roleRepo.findByName("USER");
		String token=tokGen.generateToken();
		System.out.println("Token = "+token);
		if(role==null) {
			throw new RoleNotFoundException("Role not found");
		}
		//token obj create
	    Token tokenObj=new Token();
		tokenObj.setToken(token);
		tokenObj.setExpiryTime(LocalDateTime.now().plusHours(24));
		BeanUtils.copyProperties(dto, user);
		user.setRole(role);
		user.setStatus("InActive");
		
		// .........create userprofile ............
		UserProfile profile=new UserProfile();
		  user.setUserProfile(profile);
		  profile.setUser(user);
		  profile.setProfileUrl(null);
		  
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
	
	Notification notification=new Notification();
	notification.setIsRead("no");
	notification.setTitle("welcome");
	notification.setMessage("Your email is verified now u can login in your account");
	notification.setTime(LocalDateTime.now());
	notification.setUser(user);
	notificationRepo.save(notification);
	
	tokRepo.delete(tokObj);
	 return emailToken;
	 
}


 public void createLoginHistoryAndActivityLogs(String email,HttpServletRequest req) {
	 com.isrdc.entities.User  user=userRepo.findByEmail(email);
	 if(user == null) {
		throw  new UsernameNotFoundException("user does not exist");
	 }
	String userAgent= req.getHeader("User-Agent");
	
	 LoginHistory history=new LoginHistory();
	 history.setBrowserName(getBrowserName(userAgent));
	 history.setDevicename(getDeviceName(userAgent));
	 history.setIPaddress(req.getRemoteAddr());
	 history.setLoginTime(LocalDateTime.now());
	 history.setUser(user);
	 loginHistoryRepo.save(history);
	  
	 ActivityLog activity =new ActivityLog();
	 activity.setUser(user);
	 activity.setTime(LocalDateTime.now());
	 activity.setAction("login");
	 
	 activity.setDescription("You are logged in successfully in  your account");
	 activityRepo.save(activity) ;
}
 
 
 private String getDeviceName(String userAgent) {

	    if (userAgent == null) {
	        return "Unknown";
	    }

	    if (userAgent.contains("Android")) {
	        return "Android";
	    }

	    if (userAgent.contains("iPhone")) {
	        return "iPhone";
	    }

	    if (userAgent.contains("iPad")) {
	        return "iPad";
	    }

	    if (userAgent.contains("Windows")) {
	        return "Windows PC";
	    }

	    if (userAgent.contains("Macintosh")) {
	        return "Mac";
	    }

	    return "Unknown";
	}
 
 private String getBrowserName(String userAgent) {
	 System.out.println(userAgent);
	 if(userAgent == null) {
		 return "Unknown";
	 }
	
	 
	 if (userAgent.contains("Edg/")) {
	        return "Microsoft Edge";
	    }
	 if (userAgent.contains("OPR/")) {
	        return "Opera";
	    }
if (userAgent.contains("Chrome/")) {
	        return "Google Chrome";
	    }

	    if (userAgent.contains("Firefox/")) {
	        return "Mozilla Firefox";
	    }

	    if (userAgent.contains("Safari/")) {
	        return "Safari";
	    }
	 
	 return "Unknown";
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
