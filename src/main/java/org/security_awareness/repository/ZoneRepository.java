package org.security_awareness.repository;

import java.util.Set;

import org.security_awareness.annotations.PreAuthorizeAdmin;
import org.security_awareness.model.Zone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ZoneRepository extends CrudRepository<Zone, Long> {
	
	@PreAuthorizeAdmin
	@Override
	<S extends Zone> S save(S entity);

	@PreAuthorizeAdmin
	@Override
	void delete(Zone entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();

	Zone findByName(@Param("name") String name);
	
	@Query("SELECT z from Zone z, ActivityStatus actstat "
			+ "JOIN actstat.user u "
			+ "JOIN actstat.activity act "
			+ "JOIN act.zone actzone "
			+ "WHERE u.email LIKE :user "
			+ "AND actzone.id = z.id "
			+ "AND (actstat.interested = true OR actstat.assistant = true)")
	Set<Zone> findByActivitiesInterestedOrAssisted(@Param("user") String user);
}
