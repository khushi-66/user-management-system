package com.isrdc.configs;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.isrdc.entities.Role;
import com.isrdc.repos.RoleRepo;

@Configuration
public class DataInititalizer {

      @Bean
	 CommandLineRunner init(RoleRepo repo) {
    	  
    	  return args->{
    		  
    		  if(repo.count()==0) {
    			  Role user=new Role();
    			  user.setName("USER");
    			  user.setPermissions("Basic");
    			  
    			  Role admin=new Role();
    			  admin.setName("ADMIN");
    			  admin.setPermissions("ALL");
    			  
    			  Role manager=new Role();
    			  manager.setName("MANAGER");
    			  manager.setPermissions("Manage_User");
    			  
    			  repo.save(user);
    			  repo.save(admin);
    			  repo.save(manager);
    			  
    			  
    		  }
    	  };
		
	}

}
