package org.security_awareness.repository;

import java.util.List;

import org.security_awareness.annotations.PostFilterNot0Status;
import org.security_awareness.annotations.PreAuthorizeAdmin;
import org.security_awareness.annotations.PreAuthorizeAdminOrEntityWithUser;
import org.security_awareness.model.NotificationStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface NotificationStatusRepository extends CrudRepository<NotificationStatus, Long>{
	
	@PreAuthorizeAdminOrEntityWithUser
	@Override
	<S extends NotificationStatus> S save(S entity);

	@PreAuthorizeAdminOrEntityWithUser
	@Override
	void delete(NotificationStatus entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
	
	@PostFilterNot0Status
	List<NotificationStatus> findAll();
	
	@PostFilterNot0Status
	List<NotificationStatus> findAllByUser_email(@Param("user") String user);
	
	@PostFilterNot0Status
	List<NotificationStatus> findAllByUser_emailOrderByNotification_DateDesc(@Param("user") String user);

}
