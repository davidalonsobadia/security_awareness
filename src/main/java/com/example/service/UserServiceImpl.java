package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.User;
import com.example.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(User user){
		return userRepository.save(user);
	}
	
	@Override
	public boolean exists(User user){
		User userFromDB = userRepository.findByEmail(user.getEmail());
		if(userFromDB == null)
			return false;
		return true;
	}
	
	@Override
	public User findById(long id){
		return userRepository.findOne(id);
	}
	
	@Override
	public void delete(User user){
		userRepository.delete(user);
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
