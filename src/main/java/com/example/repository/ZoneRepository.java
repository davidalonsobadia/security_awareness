package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.model.Zone;

public interface ZoneRepository extends CrudRepository<Zone, Long> {

	Zone findByName(@Param("name") String name);
}
