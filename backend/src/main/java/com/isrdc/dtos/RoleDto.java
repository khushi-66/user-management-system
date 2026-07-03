package com.isrdc.dtos;

import java.util.List;

import com.isrdc.entities.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

public class RoleDto {

	private Integer roleId;
	  private String name;
	  private String permissions;
	  
	 
		private List<User> users;
	  
	  
		 public List<User> getUsers() {
				return users;
			}
			  public void setUsers(List<User> users) {
				this.users = users;
			  }
	  
	  public Integer getRoleId() {
		return roleId;
	  }
	  public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	  }
	  public String getName() {
		return name;
	  }
	  public void setName(String name) {
		this.name = name;
	  }
	  public String getPermissions() {
		return permissions;
	  }
	  public void setPermissions(String permissions) {
		this.permissions = permissions;
	  }
	  
	  
	  
	  

}
