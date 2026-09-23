package com.isrdc.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isrdc.entities.Token;
import com.isrdc.entities.User;

public interface TokenRepo  extends JpaRepository<Token, Integer>{

	
public Token findByToken(String token);
public Token findByUser(User user);
}
