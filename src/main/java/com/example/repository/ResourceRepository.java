package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.annotations.PreAuthorizeAdmin;
import com.example.model.Resource;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
	
	@PreAuthorizeAdmin
	@Override
	<S extends Resource> S save(S entity);

	@PreAuthorizeAdmin
	@Override
	void delete(Resource entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
	
	List<Resource> findAllByOrderByCreationDateDesc();

}
