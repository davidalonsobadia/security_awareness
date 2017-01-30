package com.example.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.model.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long>{
	
	Set<Notification> findBySender_Email(@Param("sender") String sender);
		
}
