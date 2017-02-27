package org.security_awareness.repository;

import org.security_awareness.annotations.PreAuthorizeAdmin;
import org.security_awareness.model.Password;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=false)
public interface PasswordRepository extends CrudRepository<Password, Long> {
	
	//TODO: REVIEW THIS
//	@PreAuthorizeAdmin
//	@Override
//	<S extends Password> S save(S entity);

	@PreAuthorizeAdmin
	@Override
	void delete(Password entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();

}
