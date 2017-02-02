package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.annotations.PreAuthorizeAdmin;
import com.example.model.NotificationStatus;
import com.example.model.Password;

@RepositoryRestResource(exported=false)
public interface PasswordRepository extends CrudRepository<Password, Long> {
	
	//TODO: REVIEW THIS
//	@PreAuthorizeAdmin
//	@Override
//	<S extends Password> S save(S entity);

	@PreAuthorizeAdmin
	@Override
	void delete(Password entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();

}
