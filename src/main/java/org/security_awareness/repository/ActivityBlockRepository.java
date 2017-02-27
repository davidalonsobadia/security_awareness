package org.security_awareness.repository;

import org.security_awareness.annotations.PreAuthorizeAdmin;
import org.security_awareness.model.ActivityBlock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=false)
public interface ActivityBlockRepository extends CrudRepository<ActivityBlock, Long> {
	
	@PreAuthorizeAdmin
	@Override
	<S extends ActivityBlock> S save(S entity);

	@PreAuthorizeAdmin
	@Override
	void delete(ActivityBlock entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
}
