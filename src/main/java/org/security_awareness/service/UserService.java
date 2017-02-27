package org.security_awareness.service;

import java.util.Set;

import org.security_awareness.model.User;

public interface UserService {

	public User save(User user);
		
	public boolean exists(User user);
	
	public User findById(long id);
	
	public void delete(User user);
	
	public User findByEmail(String email);

	Set<User> findAll();

	Set<User> findAllByUserZones(String user);

	Set<User> findAllByInterestedAndUser(String user);

	Set<User> findAllByAssistedAndUser(String user);
}
