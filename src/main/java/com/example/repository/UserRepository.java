package com.example.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.annotations.PreAuthorizeAdmin;
import com.example.annotations.PreAuthorizeAdminOrOwnUser;
import com.example.model.ActivityStatus;
import com.example.model.User;

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
	
	@Override
	Set<User> findAll();
	
	User findByEmail(@Param("email") String email);

	/**
	select u.email from users u 
	join user_zone uz on uz.user_id = u.id 
	join zone_status z on z.id = uz.zone_id 
	where z.name IN (
		select z.name from zone_status z 
		join user_zone uz on uz.zone_id = z.id 
		join users u on u.id = uz.user_id 
		where u.email like 'anna@weappyou.com'
		)
	group by u.email;
	**/
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
	
	
	/*
	select u.email from users u 
	join  activity_status astat on astat.user_id = u.id 
	where astat.activity_id IN (
		select subastat.activity_id from  subastat
		join users u on subastat.user_id = u.id
		where u.email like 'txema_50@mail.com'
		)
	group by u.email;
	*/
	@Query("SELECT user FROM User user "
			+ "JOIN user.activityStatus activity_status "
			+ "WHERE activity_status.activity IN ("
				+ "SELECT activity_status.activity from ActivityStatus activity_status "
				+ "JOIN activity_status.user u "
				+ "WHERE u.email LIKE :user "
				+ "AND activity_status.interested = true"
				+ ")")
	Set<User> findAllByInterestedAndUser(@Param("user") String user);
	
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
