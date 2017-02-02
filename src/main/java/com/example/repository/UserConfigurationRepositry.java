package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.annotations.PreAuthorizeAdmin;
import com.example.model.Password;
import com.example.model.UserConfiguration;

@RepositoryRestResource(exported=false)
public interface UserConfigurationRepositry extends CrudRepository<UserConfiguration, Long> {

	@PreAuthorizeAdmin
	@Override
	<S extends UserConfiguration> S save(S entity);

	@PreAuthorizeAdmin
	@Override
	void delete(UserConfiguration entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
	
}
