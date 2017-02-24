package com.example.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.google.common.collect.Lists;

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
	
	@Override
	public Set<User> findAll(){
		return userRepository.findAll();
	}
	
	@Override
	public Set<User> findAllByUserZones(String user){
		return userRepository.findAllByUserZones(user);
	}
	
	@Override
	public Set<User> findAllByInterestedAndUser(String user){
		return userRepository.findAllByInterestedAndUser(user);
	}
	
	@Override
	public Set<User> findAllByAssistedAndUser(String user){
		return userRepository.findAllByAssistedAndUser(user);
	}
}
