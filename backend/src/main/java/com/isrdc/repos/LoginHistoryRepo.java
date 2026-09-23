package com.isrdc.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isrdc.entities.LoginHistory;



public interface LoginHistoryRepo extends JpaRepository<LoginHistory, Integer>{

}
