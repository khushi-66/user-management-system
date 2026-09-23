package com.isrdc.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isrdc.dtos.RoleDto;
import com.isrdc.entities.Role;
import com.isrdc.entities.User;
import com.isrdc.repos.RoleRepo;
import com.isrdc.repos.UserRepo;

@Service
public class RoleService {

	@Autowired
	private UserRepo repo;
	public  RoleDto getRoleByUserId(Integer id) {
		User user = repo.findById(id).orElse(null);

		Role role = user.getRole();
		RoleDto dto=new RoleDto();
		BeanUtils.copyProperties(role, dto);
		return dto;
	}
	
	
}
