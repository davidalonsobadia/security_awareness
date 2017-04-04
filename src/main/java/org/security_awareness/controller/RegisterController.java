package org.security_awareness.controller;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.security_awareness.model.Password;
import org.security_awareness.model.Role;
import org.security_awareness.model.User;
import org.security_awareness.service.AuthorityService;
import org.security_awareness.service.impl.UserServiceImpl;
import org.security_awareness.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class RegisterController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private TokenStore tokenStore;
	
    @RequestMapping(value= "/register",method = RequestMethod.POST)
    public ResponseEntity<Void> register(@RequestBody UserContext userContext,
    		UriComponentsBuilder ucBuilder,
    		HttpServletRequest request) {
    	    	
    	User user = userContext.getUser();
    	Password password = userContext.getPassword();
    	
    	if (!userService.exists(user)){
    		logger.warn("User " + user.getEmail() + " NOT exists.");
    		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    	}
    	
    	// Upgrade users from role ANONYMOUS to role USER
    	User userFromDatabase = userService.findByEmail(user.getEmail());
    	
    	//check current role    	
    	Role roleFromDatabase = userFromDatabase.getAuthority().getRole();
    	if(!roleFromDatabase.equals(Role.ANONYMOUS)){
    		logger.warn("User " + user.getEmail() + " already registered.");
    		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    	}
    	
    	userFromDatabase.setAuthority(authorityService.findByRole(Role.USER));
    	userFromDatabase.setCity(user.getCity());
    	userFromDatabase.setFirstName(user.getFirstName());
    	userFromDatabase.setLastName(user.getLastName());
    	userFromDatabase.setCity(user.getCity());
    	userFromDatabase.setPassword(password);
    	userFromDatabase.setConfiguration(user.getConfiguration());
    	
    	userService.save(userFromDatabase);
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(userFromDatabase.getId()).toUri());
    	ResponseEntity<Void> response = new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    	
    	/*
    	 * remove existing token
    	 * 
    	 */
    	
    	
    	Collection<OAuth2AccessToken> accessTokens = tokenStore.findTokensByClientIdAndUserName(
    			"security_awareness_app",
    			user.getEmail());
    	
    	Iterator<OAuth2AccessToken> iterator = accessTokens.iterator();
    	
    	while(iterator.hasNext()){
    		
    		OAuth2AccessToken accessToken = iterator.next();
    		tokenStore.removeAccessToken(accessToken);
    	}
    	    	
    	
    	return response;
    }
    
    @RequestMapping(value= "/isRegistered", method = RequestMethod.GET)
    public ResponseEntity<String> isRegistered(
    		@RequestParam("email") String email) {
    	
		JSONObject jsonObject = new JSONObject();
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
    	
    	if(userService.findByEmail(email) == null){
    		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    	} else if(userService.findByEmail(email).getAuthority().getRole().compareTo(Role.ANONYMOUS)!=0){    		
    		jsonObject.put("registered", true);
    		return new ResponseEntity<String>(jsonObject.toString(), headers, HttpStatus.OK);
    	} else {
    		jsonObject.put("registered", false);
    		return new ResponseEntity<String>(jsonObject.toString(), headers, HttpStatus.OK);
    	}
    }
}
