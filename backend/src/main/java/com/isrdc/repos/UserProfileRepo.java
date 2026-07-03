package com.isrdc.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isrdc.entities.UserProfile;

public interface UserProfileRepo extends JpaRepository<UserProfile, Integer>{

}
