package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Resource;

public interface ResourceRepository extends CrudRepository<Resource, Long> {

}
