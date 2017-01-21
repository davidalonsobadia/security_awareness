package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepository;
	
	public User save(User user){
		return userRepository.save(user);
	}
	
	public boolean exists(User user){
		User userFromDB = userRepository.findByUsername(user.getUsername());
		if(userFromDB == null)
			return false;
		return true;
	}
	
	public User findById(long id){
		return userRepository.findOne(id);
	}
	
	public void delete(User user){
		userRepository.delete(user);
	}
}
