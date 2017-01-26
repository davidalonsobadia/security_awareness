package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.annotations.PreAuthorizeAdmin;
import com.example.annotations.PreAuthorizeAdminOrOwnUser;
import com.example.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
			
	@PreAuthorizeAdminOrOwnUser
	@Override
	<S extends User> S save(S user);

	@PreAuthorizeAdminOrOwnUser
	@Override
	void delete(User user);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
	
	User findByEmail(@Param("email") String email);
}
