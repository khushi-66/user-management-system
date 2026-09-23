package com.isrdc.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isrdc.entities.ActivityLog;



public interface ActivityLogRepo extends JpaRepository<ActivityLog, Integer> {
public List<ActivityLog>findAllByUserUserIdOrderByTimeDesc(Integer id);
}
