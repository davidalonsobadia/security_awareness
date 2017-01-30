package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.model.NotificationStatus;

public interface NotificationStatusRepository extends CrudRepository<NotificationStatus, Long>{
	
	List<NotificationStatus> findAllByUser_email(@Param("user") String user);

}
