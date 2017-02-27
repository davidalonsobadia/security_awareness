package org.security_awareness.repository;

import org.security_awareness.annotations.PreAuthorizeAdmin;
import org.security_awareness.model.UserConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=false)
public interface UserConfigurationRepositry extends CrudRepository<UserConfiguration, Long> {

	@PreAuthorizeAdmin
	@Override
	<S extends UserConfiguration> S save(S entity);

	@PreAuthorizeAdmin
	@Override
	void delete(UserConfiguration entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
	
}
