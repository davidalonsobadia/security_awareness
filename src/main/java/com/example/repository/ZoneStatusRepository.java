package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.annotations.PostFilterOwnUser;
import com.example.annotations.PreAuthorizeAdmin;
import com.example.annotations.PreAuthorizeAdminOrEntityWithUser;
import com.example.model.ZoneStatus;

public interface ZoneStatusRepository extends CrudRepository<ZoneStatus, Long> {
	
	@PreAuthorizeAdminOrEntityWithUser
	@Override
	<S extends ZoneStatus> S save(S entity);
	
	@PreAuthorizeAdmin
	@Override
	void delete(ZoneStatus entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
	
	@PostFilterOwnUser
	@Override
	Iterable<ZoneStatus> findAll();

}
