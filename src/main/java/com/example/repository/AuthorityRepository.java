package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.annotations.PreAuthorizeAdmin;
import com.example.model.Authority;
import com.example.model.Role;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	
	@PreAuthorizeAdmin
	@Override
	<S extends Authority> S save(S entity);

	@PreAuthorizeAdmin
	@Override
	void delete(Authority entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
	
	Authority findByRole(Role role);

}
