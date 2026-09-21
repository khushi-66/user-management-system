package com.isrdc.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isrdc.entities.Notification;



public interface NotificationRepo extends JpaRepository<Notification, Integer> {
 public List<Notification> findAllByUserUserId(Integer userid);
}
