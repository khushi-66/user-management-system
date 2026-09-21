package com.isrdc.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isrdc.dtos.NotificationDto;
import com.isrdc.dtos.UserDto;
import com.isrdc.entities.Notification;
import com.isrdc.entities.User;
import com.isrdc.repos.NotificationRepo;
import com.isrdc.repos.UserRepo;

@Service

public class NotificationService {

	@Autowired
	private NotificationRepo repo;
	@Autowired
	private UserRepo userRepo;
	
	public  List<NotificationDto> fetchAllNotifications(Integer userid) {
		List<Notification>notifications=repo.findAllByUserUserId(userid);
		List<NotificationDto>notificationDtos=new ArrayList<NotificationDto>();
		
		for(int i=0;i<notifications.size();i++) {
			Notification notification=notifications.get(i);
			NotificationDto dto=new NotificationDto();
			  dto.setNotificationId(notification.getNotificationId());
		        dto.setTitle(notification.getTitle());
		        dto.setMessage(notification.getMessage());
		        dto.setIsRead(notification.getIsRead());
		        dto.setTime(LocalDateTime.now());
			notificationDtos.add(dto);
		}
		return notificationDtos;
	}
	
	public void createNotification(Integer id,String title,String msg) {
		Optional<User> u=   userRepo.findById(id);
		User user=u.get();
		Notification notification=new Notification();
		notification.setIsRead("no");
		notification.setTitle(title);
		notification.setMessage(msg);
		notification.setUser(user);
		notification.setTime(LocalDateTime.now());
		repo.save(notification);
	}

}
