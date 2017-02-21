package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.annotations.PreAuthorizeAdmin;
import com.example.annotations.PreAuthorizeAdminOrEntityWithUser;
import com.example.model.ActivityStatus;

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

	ActivityStatus findOneByActivity_IdAndUser_Email(@Param("activity_id") long id,
			@Param("user") String user);

}