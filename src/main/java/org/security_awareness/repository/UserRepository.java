package org.security_awareness.repository;

import java.util.Set;

import org.security_awareness.annotations.PostFilterOwnUser;
import org.security_awareness.annotations.PreAuthorizeAdmin;
import org.security_awareness.annotations.PreAuthorizeAdminOrOwnUser;
import org.security_awareness.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface UserRepository extends CrudRepository<User, Long> {
			
	@PreAuthorizeAdminOrOwnUser
	@Override
	<S extends User> S save(S entity);

	@PreAuthorizeAdminOrOwnUser
	@Override
	void delete(User entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
	
	@PostFilterOwnUser
	@Override
	Set<User> findAll();
	
	User findByEmail(@Param("email") String email);
	
	// Visibility: null(0), by common assisted activities(1), by common interested activities(2), by common zones(3), all(4)
	@Query("SELECT user from User user "
			+ "JOIN user.configuration c "
			// VISIBILITY = 4
			+ "WHERE c.visibility = 4 "
			+ "OR user.id IN ("
				// VISIBILITY = 3
				+ "SELECT user_visibility3.id from User user_visibility3 "
				+ "JOIN user_visibility3.zoneStatus z3 "
				+ "JOIN user_visibility3.configuration c3 "
				+ "WHERE z3.zone IN ("
					+ "SELECT subuz.zone FROM User subu "
					+ "JOIN subu.zoneStatus subuz "
					+ "WHERE "
					+ 	"subu.email LIKE :user "
					+ ") "
				+ "AND z3.status > 0 "
				+ "AND c3.visibility = 3"
			+ ")"
			+ "OR user.id IN ("
				// VISIBILITY = 2
				+ "SELECT user_visibility2.id FROM User user_visibility2 "
				+ "JOIN user_visibility2.configuration c2 "
				+ "JOIN user_visibility2.activityStatus activity_status "
				+ "WHERE activity_status.activity IN ("
					+ "SELECT activity_status.activity from ActivityStatus activity_status "
					+ "JOIN activity_status.user u "
					+ "WHERE u.email LIKE :user "
					+ "AND activity_status.interested = true"
					+ ")"
				+ "AND c2.visibility = 2"
			+")"
			+"OR user.id IN ("
				// VISIBILITY = 1
				+ "SELECT user_visibility1.id FROM User user_visibility1 "
				+ "JOIN user_visibility1.configuration c1 "
				+ "JOIN user_visibility1.activityStatus activity_status "
				+ "WHERE activity_status.activity IN ("
					+ "SELECT activity_status.activity from ActivityStatus activity_status "
					+ "JOIN activity_status.user u "
					+ "WHERE u.email LIKE :user "
					+ "AND activity_status.assistant = true"
					+ ")"
				+ "AND c1.visibility = 1"
			+") "
			+ "AND user.registered = true"
			)
	Set<User> findAllByVisibility(@Param("user") String user);


	@RestResource(exported=false)
	@Query("SELECT u from User u "
			+ "JOIN u.zoneStatus z "
			+ "WHERE z.zone IN ("
				+ "SELECT subuz.zone FROM User subu "
				+ "JOIN subu.zoneStatus subuz "
				+ "WHERE "
				+ 	"subu.email LIKE :user "
				+ ")"
				+ "AND z.status > 0")
	Set<User> findAllByUserZones(@Param("user") String user);
	
	@RestResource(exported=false)
	@Query("SELECT user FROM User user "
			+ "JOIN user.activityStatus activity_status "
			+ "WHERE activity_status.activity IN ("
				+ "SELECT activity_status.activity from ActivityStatus activity_status "
				+ "JOIN activity_status.user u "
				+ "WHERE u.email LIKE :user "
				+ "AND activity_status.interested = true"
				+ ")")
	Set<User> findAllByInterestedAndUser(@Param("user") String user);
	
	@RestResource(exported=false)
	@Query("SELECT user FROM User user "
			+ "JOIN user.activityStatus activity_status "
			+ "WHERE activity_status.activity IN ("
				+ "SELECT activity_status.activity from ActivityStatus activity_status "
				+ "JOIN activity_status.user u "
				+ "WHERE u.email LIKE :user "
				+ "AND activity_status.assistant = true"
				+ ")")
	Set<User> findAllByAssistedAndUser(@Param("user") String user);
}
