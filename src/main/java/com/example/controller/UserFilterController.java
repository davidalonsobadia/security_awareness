package com.example.controller;

import java.util.Set;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.User;
import com.example.service.UserService;

@Controller
public class UserFilterController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(method = RequestMethod.POST, value="/users/search/findByFilters")
	public @ResponseBody Resources<User> getUsersFiltered(@RequestBody String jsonString){
		
		JSONObject json = new JSONObject(jsonString);
		boolean myZones = json.getBoolean("myZones");
		boolean assistant = json.getBoolean("assistant");
		boolean interested = json.getBoolean("interested");
		
		User currentUser = userService.findByEmail(
				SecurityContextHolder.getContext()
					.getAuthentication().getName()
					);
		String userEmail = currentUser.getEmail();
		
		Set<User> allUsers = userService.findAll();
		allUsers.remove(currentUser);
		
		// 1. Check if MyZones is activated
		if(myZones == true) {
			Set<User> usersByZone = userService.findAllByUserZones(userEmail);
			allUsers.retainAll(usersByZone);
			
		}
		
		// 2. Check interested filter
		if( interested == true) {
			Set<User> usersInterested = userService.findAllByInterestedAndUser(userEmail);
			allUsers.retainAll(usersInterested);
		}
		
		// 3. Check assistant filter
		if( assistant == true) {
			Set<User> usersAssistant = userService.findAllByAssistedAndUser(userEmail);
			allUsers.retainAll(usersAssistant);
		}
		
		
		Resources<User> resources = new Resources<User>(allUsers);
		
		return resources;
		
	}
}
