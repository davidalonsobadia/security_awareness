package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.model.ActivityStatus;

public interface ActivityStatusRepository extends CrudRepository<ActivityStatus, Long> {

	ActivityStatus findOneByActivity_IdAndUser_Email(@Param("activity_id") long id,
			@Param("user") String user);

}