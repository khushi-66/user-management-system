package com.isrdc.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isrdc.entities.Token;

public interface TokenRepo  extends JpaRepository<Token, Integer>{

	
public Token findByToken(String token);
}
