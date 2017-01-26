package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Authority;
import com.example.model.Role;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	
	Authority findByRole(Role role);

}
