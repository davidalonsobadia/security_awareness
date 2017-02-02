package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.annotations.PreAuthorizeAdmin;
import com.example.model.ActivityBlock;

@RepositoryRestResource(exported=false)
public interface ActivityBlockRepository extends CrudRepository<ActivityBlock, Long> {
	
	@PreAuthorizeAdmin
	@Override
	<S extends ActivityBlock> S save(S entity);

	@PreAuthorizeAdmin
	@Override
	void delete(ActivityBlock entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
}
