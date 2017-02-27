package org.security_awareness.repository;

import java.util.Set;

import org.security_awareness.annotations.PreAuthorizeAdmin;
import org.security_awareness.model.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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
