package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.model.ActivityBlock;

@RepositoryRestResource(exported=false)
public interface ActivityBlockRepository extends CrudRepository<ActivityBlock, Long> {
	
}
