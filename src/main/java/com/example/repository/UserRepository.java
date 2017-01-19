package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.annotations.PostFilterAdminOrOwnUser;
import com.example.annotations.PreAuthorizeAdmin;
import com.example.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	@PreAuthorizeAdmin
	User findByUsername(@Param("username") String username);
	
	@PostFilterAdminOrOwnUser
	@Override
	Iterable<User> findAll();
	
	@PreAuthorizeAdmin
	@Override
	<S extends User> S save(S entity);
	
}
