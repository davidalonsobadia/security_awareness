package com.example.service;

import com.example.model.User;

public interface UserService {

	public User save(User user);
		
	public boolean exists(User user);
	
	public User findById(long id);
	
	public void delete(User user);
}
