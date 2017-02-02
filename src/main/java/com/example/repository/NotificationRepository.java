package com.example.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.annotations.PreAuthorizeAdmin;
import com.example.model.Authority;
import com.example.model.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long>{
	
	@PreAuthorizeAdmin
	@Override
	<S extends Notification> S save(S entity);

	@PreAuthorizeAdmin
	@Override
	void delete(Notification entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
	
	Set<Notification> findBySender_Email(@Param("sender") String sender);
		
}
