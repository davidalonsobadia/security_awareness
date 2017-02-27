package org.security_awareness.repository;

import org.security_awareness.annotations.PostFilterOwnUser;
import org.security_awareness.annotations.PreAuthorizeAdmin;
import org.security_awareness.annotations.PreAuthorizeAdminOrEntityWithUser;
import org.security_awareness.model.ZoneStatus;
import org.springframework.data.repository.CrudRepository;

public interface ZoneStatusRepository extends CrudRepository<ZoneStatus, Long> {
	
	@PreAuthorizeAdminOrEntityWithUser
	@Override
	<S extends ZoneStatus> S save(S entity);
	
	@PreAuthorizeAdmin
	@Override
	void delete(ZoneStatus entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
	
	@PostFilterOwnUser
	@Override
	Iterable<ZoneStatus> findAll();

}
