package com.isrdc.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isrdc.entities.ActivityLog;



public interface ActivityLogRepo extends JpaRepository<ActivityLog, Integer> {

}
