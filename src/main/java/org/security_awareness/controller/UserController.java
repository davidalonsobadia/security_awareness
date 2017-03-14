package org.security_awareness.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.security_awareness.model.Authority;
import org.security_awareness.model.Role;
import org.security_awareness.model.User;
import org.security_awareness.service.AuthorityService;
import org.security_awareness.service.UserService;
import org.security_awareness.utils.UserPostContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@RequestMapping(value="/users", method =  RequestMethod.POST)
	@ResponseBody 
	public ResponseEntity<Resource<User>> postUser(
			@RequestBody UserPostContext userContext) throws Exception{
			
		User user = new User();
		
		user.setEmail(userContext.getEmail());
		user.setCity(userContext.getCity());
		user.setFirstName(userContext.getFirstName());
		user.setLastName(userContext.getLastName());
		user.setConfiguration(userContext.getConfiguration());
		
		user.setManager(userService.findById(objectParser(userContext.getManager(), "Manager")));
		
		Authority authority =authorityService.findByRole(
				Role.valueOf(userContext.getAuthority().toUpperCase()));
		
		boolean registered = authority.getRole().compareTo(Role.ANONYMOUS)==0 ? false : true; 
		user.setRegistered(registered);
		
		user.setAuthority(authority);
			
		userService.save(user);
		
				
		User userRepository = userService.findByEmail(userContext.getEmail());
	    BasicLinkBuilder builder = BasicLinkBuilder.linkToCurrentMapping()
	                                               .slash("users")
	                                               .slash(userRepository.getId());
	    Resource<User> resource = new Resource<User>(userRepository,
                builder.withSelfRel());	    
	    
	    return new ResponseEntity<>(resource, HttpStatus.CREATED);
	}
	
	
	// NON - API
	private int objectParser(Object object, String type) throws Exception{
		
		int objectId = 0;
		if(object instanceof Integer){
			objectId = (int) object;
		} else if(object instanceof String){
			final Pattern lastIntPattern = Pattern.compile("[^0-9]+([0-9]+)$");
			Matcher matcher = lastIntPattern.matcher(object.toString());
			if(matcher.find()){
				objectId = Integer.parseInt(matcher.group(1));
			} else {
				throw new Exception(type + " not Found");
			}
		} else {
			throw new Exception(type + " not Found");
		}
		return objectId;
		
	}
}
