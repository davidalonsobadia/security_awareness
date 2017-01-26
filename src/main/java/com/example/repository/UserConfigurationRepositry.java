package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.model.UserConfiguration;

@RepositoryRestResource(exported=false)
public interface UserConfigurationRepositry extends CrudRepository<UserConfiguration, Long> {

}
