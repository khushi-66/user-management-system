package com.isrdc.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isrdc.dtos.UserProfileDto;
import com.isrdc.entities.UserProfile;
import com.isrdc.repos.UserProfileRepo;

@Service
public class UserProfileService {

	@Autowired
	private UserProfileRepo repo;
	
	public UserProfileDto findUserProfile(Integer id) {
		UserProfile userProfile= repo.findByUserUserId(id);
		UserProfileDto dto=new UserProfileDto();
		BeanUtils.copyProperties(userProfile, dto);
		return dto;
	}

}
