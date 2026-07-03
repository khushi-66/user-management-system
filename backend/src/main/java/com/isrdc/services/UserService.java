package com.isrdc.services;

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

import com.isrdc.repos.ActivityLogRepo;
import com.isrdc.repos.NotificationRepo;
import com.isrdc.repos.PasswordResetRepo;
import com.isrdc.repos.RoleRepo;
import com.isrdc.repos.UserProfileRepo;
import com.isrdc.repos.UserRepo;

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
	private LoginHistory loginHistoryRepo;
	@Autowired
	private ActivityLogRepo activityRepo;
	
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
		BeanUtils.copyProperties(dto, user);
		 userRepo.save(user);
	}
	
	

}
