package org.security_awareness.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.security_awareness.model.Authority;
import org.security_awareness.model.Password;
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
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@Autowired
	private TokenStore tokenStore;
	
	
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
		user.setAuthority(authority);
			
		user.setPassword(userContext.getPassword());
					
		User userSaved = userService.save(user);
	
	    BasicLinkBuilder builder = BasicLinkBuilder.linkToCurrentMapping()
	                                               .slash("users")
	                                               .slash(userSaved.getId());
	    Resource<User> resource = new Resource<User>(userSaved, builder.withSelfRel());	    
	    
	    return new ResponseEntity<>(resource, HttpStatus.CREATED);
	}
	

	@RequestMapping(value="/users/{id}", method =  RequestMethod.DELETE)
	@ResponseBody 
	public ResponseEntity<Void> deleteUser(
			@PathVariable("id") int userId) throws Exception{
		
		User user = userService.findById(userId);
		if(user == null) {
			throw new Exception("User not found");
		}
		
    	Collection<OAuth2AccessToken> accessTokens = tokenStore.findTokensByClientIdAndUserName(
    			"security_awareness_app",
    			user.getEmail());
    	Iterator<OAuth2AccessToken> iterator = accessTokens.iterator();
    	while(iterator.hasNext()){
    		OAuth2AccessToken accessToken = iterator.next();
    		tokenStore.removeAccessToken(accessToken);
    	}
    	
		userService.delete(user);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
		
	
	// NON - API --------------------------------------------------------------
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
