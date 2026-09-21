package com.isrdc.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isrdc.dtos.UserDto;
import com.isrdc.entities.Token;
import com.isrdc.repos.TokenRepo;
import com.isrdc.repos.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TokenRepo tokRepo;
	
	public void updateLastloginUser(String email) {
		com.isrdc.entities.User user=userRepo.findByEmail(email);
		if(user == null)
		{
			throw new UsernameNotFoundException("User not found");
		}
		user.setLastloginAt(LocalDateTime.now());
		
		
		userRepo.save(user);
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

}
