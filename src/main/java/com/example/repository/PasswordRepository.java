package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.model.Password;

@RepositoryRestResource(exported=false)
public interface PasswordRepository extends CrudRepository<Password, Long> {

}
