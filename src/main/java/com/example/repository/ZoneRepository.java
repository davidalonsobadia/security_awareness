package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.annotations.PreAuthorizeAdmin;
import com.example.model.UserConfiguration;
import com.example.model.Zone;

public interface ZoneRepository extends CrudRepository<Zone, Long> {
	
	@PreAuthorizeAdmin
	@Override
	<S extends Zone> S save(S entity);

	@PreAuthorizeAdmin
	@Override
	void delete(Zone entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();

	Zone findByName(@Param("name") String name);
}
