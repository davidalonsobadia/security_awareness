package org.security_awareness.repository;

import java.util.List;

import org.security_awareness.annotations.PreAuthorizeAdmin;
import org.security_awareness.model.Resource;
import org.springframework.data.repository.CrudRepository;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
	
	@PreAuthorizeAdmin
	@Override
	<S extends Resource> S save(S entity);

	@PreAuthorizeAdmin
	@Override
	void delete(Resource entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
	
	List<Resource> findAllByOrderByCreationDateDesc();

}
