package org.security_awareness.repository;

import org.security_awareness.annotations.PostFilterOwnUser;
import org.security_awareness.annotations.PreAuthorizeAdmin;
import org.security_awareness.annotations.PreAuthorizeAdminOrEntityWithUser;
import org.security_awareness.model.ActivityStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ActivityStatusRepository extends CrudRepository<ActivityStatus, Long> {
	
	@PreAuthorizeAdminOrEntityWithUser
	@Override
	<S extends ActivityStatus> S save(S entity);
	
	@PreAuthorizeAdmin
	@Override
	void delete(ActivityStatus entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
	
	@PostFilterOwnUser
	@Override
	Iterable<ActivityStatus> findAll();

	ActivityStatus findOneByActivity_IdAndUser_Email(@Param("activity_id") long id,
			@Param("user") String user);

}