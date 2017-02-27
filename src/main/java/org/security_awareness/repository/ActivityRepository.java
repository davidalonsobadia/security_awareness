package org.security_awareness.repository;

import java.util.Set;

import org.security_awareness.annotations.PreAuthorizeAdmin;
import org.security_awareness.model.Activity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ActivityRepository extends CrudRepository<Activity, Long>{
	
	@PreAuthorizeAdmin
	@Override
	<S extends Activity> S save(S entity);

	@PreAuthorizeAdmin
	@Override
	void delete(Activity entity);
	
	@PreAuthorizeAdmin
	@Override
	void deleteAll();
	
	@Override
	Set<Activity> findAll();
	
	
	@Query("SELECT a FROM Activity a "
			+ "JOIN a.activitiesBlock ab "
			+ "WHERE MONTH(ab.startDate) = :month AND YEAR(ab.startDate) = :year "
			+ "ORDER BY ab.startDate ASC")
	Set<Activity> findAllByMonthAndYear(@Param("month") Integer month, @Param("year") Integer year);
	
	@Query("SELECT a FROM Activity a "
			+ "JOIN a.activitiesBlock ab "
			+ "JOIN a.zone z "
			+ "WHERE MONTH(ab.startDate) = :month AND YEAR(ab.startDate) = :year "
			+ "AND z.id = :zone "
			+ "ORDER BY ab.startDate ASC")
	Set<Activity> findAllByMonthAndYearAndZone(@Param("month") Integer month, 
			@Param("year") Integer year, @Param("zone") long zone);
	
	
	@Query("SELECT a FROM Activity a, User u "
			+ "JOIN a.activitiesBlock ab "
			+ "JOIN a.zone az "
			+ "JOIN u.zoneStatus uz "
			+ "JOIN uz.zone z "
			+ "WHERE MONTH(ab.startDate) = :month AND YEAR(ab.startDate) = :year "
			+ "AND z.name LIKE az.name "
			+ "AND u.email LIKE :user "
			+ "ORDER BY ab.startDate ASC")
	Set<Activity> findAllByMonthAndYearAndUserZones(@Param("month") Integer month, 
			@Param("year") Integer year, @Param("user") String user);
	
	
	@Query("SELECT activity FROM Activity activity, ActivityStatus activityStatus "
			+ "JOIN activityStatus.user u "
			+ "WHERE u.email LIKE :user "
			+ "AND activityStatus.interested = true "
			+ "AND activity.id = activityStatus.activity.id")
	Set<Activity> findAllByInterestedAndUser(@Param("user") String user);
	
	@Query("SELECT activity FROM Activity activity, ActivityStatus activityStatus "
			+ "JOIN activityStatus.user u "
			+ "WHERE u.email LIKE :user "
			+ "AND activityStatus.assistant = true "
			+ "AND activity.id = activityStatus.activity.id")
	Set<Activity> findAllByAssistantAndUser(@Param("user") String user);
}
