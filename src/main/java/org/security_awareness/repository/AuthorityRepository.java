package org.security_awareness.repository;

import org.security_awareness.annotations.PreAuthorizeAdmin;
import org.security_awareness.model.Authority;
import org.security_awareness.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	
	@PreAuthorizeAdmin
	@Override
	<S extends Authority> S save(S entity);

	@PreAuthorizeAdmin
	@Override
	void delete(Authority entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
	
	Authority findByRole(Role role);

}
