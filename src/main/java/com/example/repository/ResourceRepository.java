package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Resource;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
	
	List<Resource> findAllByOrderByCreationDateDesc();

}
