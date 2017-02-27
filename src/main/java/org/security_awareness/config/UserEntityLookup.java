package org.security_awareness.config;
//package com.example.config;
//import java.io.Serializable;
//
//import org.springframework.data.rest.core.support.EntityLookupSupport;
//import org.springframework.stereotype.Component;
//
//import com.example.model.User;
//import com.example.repository.UserRepository;
//
//@Component
//public class UserEntityLookup extends EntityLookupSupport<User> {
//	
//	private final UserRepository userRepository;
//	
//	public UserEntityLookup(UserRepository userRepository){
//		this.userRepository = userRepository;
//	}
//
//	@Override
//	public Serializable getResourceIdentifier(User user) {
//		return user.getEmail();
//	}
//
//	@Override
//	public Object lookupEntity(Serializable id) {
//		return userRepository.findByEmail(id.toString());
//	}
//
//}
