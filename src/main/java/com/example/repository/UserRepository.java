package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	@PreAuthorize("hasRole('ADMIN')")
	User findByUsername(@Param("username") String username);
	
	@PostFilter("hasRole('ADMIN') or "
			+ "filterObject.getUsername() == principal.getUsername()")
	@Override
	Iterable<User> findAll();
	
}
