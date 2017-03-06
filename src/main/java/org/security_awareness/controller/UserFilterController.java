package org.security_awareness.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.security_awareness.model.User;
import org.security_awareness.model.projections.UsersExpanded;
import org.security_awareness.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.hateoas.Resources;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserFilterController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProjectionFactory projectionFactory;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(method = RequestMethod.POST, value="/users/search/findByFilters")
	public @ResponseBody Resources<UsersExpanded> getUsersFiltered(@RequestBody String jsonString){
		
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
		
		//UsersExpanded expanded = projectionFactory.createProjection(UsersExpanded.class, currentUser);
		//System.out.println(expanded.getAuthority().getRole().toString());
		
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
		
		
		List<UsersExpanded> usersExpanded = new ArrayList<>();
		for(User user : allUsers){
			usersExpanded.add(projectionFactory.createProjection(UsersExpanded.class, user));
		}
		
		Resources<UsersExpanded> resources = new Resources<UsersExpanded>(usersExpanded);
		resources.add(linkTo(methodOn(UserFilterController.class).getUsersFiltered("json")).withSelfRel());
		
		return resources;
		
	}
}
