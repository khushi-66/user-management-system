package com.isrdc.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isrdc.entities.PasswordReset;



public interface PasswordResetRepo extends JpaRepository<PasswordReset, Integer> {

}
