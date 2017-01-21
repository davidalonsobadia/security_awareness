package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Authority;
import com.example.model.Role;
import com.example.model.User;
import com.example.service.UserServiceImpl;

@Controller
public class RegisterController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserServiceImpl userService;
	
	
    @RequestMapping(value= "/register",method = RequestMethod.POST)
    public ResponseEntity<Void> register(@RequestBody User user,
    		UriComponentsBuilder ucBuilder) {
    	
    	if (userService.exists(user)){
    		logger.warn("User " + user.getUsername() + " already exists.");
    		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    	}
    	
    	// Upgrade users from role ANONYMOUS to role USER
    	user.setAuthority(new Authority(Role.USER));
    	userService.save(user);
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
    	ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.CREATED);
    	return response;
    } 
}
