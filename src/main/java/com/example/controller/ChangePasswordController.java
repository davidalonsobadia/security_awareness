package com.example.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Password;
import com.example.model.User;
import com.example.service.PasswordService;
import com.example.service.UserServiceImpl;

@Controller
public class ChangePasswordController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private PasswordService passwordService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@RequestMapping(value= "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<Void> changePassword(
			@RequestBody String jsonString) throws JSONException {
		
		
		JSONObject json = new JSONObject(jsonString);
		
		String oldPassword = json.getString("oldPassword");
		String newPassword = json.getString("newPassword");
		int userId = json.getInt("userId");
	
		User userFromDatabase = userService.findById(userId);
			
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		if( ! username.equals(userFromDatabase.getEmail())){
			//PROBLEM
			logger.error("User ID is not the same as the user ID found in SecurityContext");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		try {
		    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
		    
		    Password password = userFromDatabase.getPassword();
		    password.setPassword(newPassword);
		    passwordService.save(password);	    
		    
		    return new ResponseEntity<Void>(HttpStatus.OK);
		    
		} catch (AuthenticationException e) {
		    // Old password was wrong
			logger.error("Wrong authentication. Is your old Password typed down correctly?");
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		
	}
}
