package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.annotations.PreAuthorizeAdmin;
import com.example.model.Notification;
import com.example.model.NotificationStatus;

public interface NotificationStatusRepository extends CrudRepository<NotificationStatus, Long>{
	
	@PreAuthorizeAdmin
	@Override
	<S extends NotificationStatus> S save(S entity);

	@PreAuthorizeAdmin
	@Override
	void delete(NotificationStatus entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
	
	List<NotificationStatus> findAllByUser_email(@Param("user") String user);

}
