package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.annotations.PostFilterOwnUser;
import com.example.annotations.PreAuthorizeAdmin;
import com.example.annotations.PreAuthorizeAdminOrEntityWithUser;
import com.example.model.NotificationStatus;

public interface NotificationStatusRepository extends CrudRepository<NotificationStatus, Long>{
	
	@PreAuthorizeAdminOrEntityWithUser
	@Override
	<S extends NotificationStatus> S save(S entity);

	@PreAuthorizeAdmin
	@Override
	void delete(NotificationStatus entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
	
	@PostFilterOwnUser
	Iterable<NotificationStatus> findAll();
	
	List<NotificationStatus> findAllByUser_email(@Param("user") String user);
	
	List<NotificationStatus> findAllByUser_emailOrderByNotification_DateDesc(@Param("user") String user);

}
