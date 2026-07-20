package com.isrdc.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isrdc.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
  public User findByEmail(String email);
  public Optional<User> findById(Integer id);
}
