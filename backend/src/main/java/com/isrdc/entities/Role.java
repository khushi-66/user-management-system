package com.isrdc.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="role")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer roleId;
	@Column(unique=true ,nullable=false)
  private String name;
	@Column(nullable=false)
  private String permissions;
  
  @OneToMany(mappedBy="role",cascade=CascadeType.ALL)
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
