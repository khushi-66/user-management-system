package com.isrdc.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isrdc.entities.Notification;



public interface NotificationRepo extends JpaRepository<Notification, Integer> {

}
