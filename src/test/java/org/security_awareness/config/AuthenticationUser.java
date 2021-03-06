package org.security_awareness.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.security_awareness.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUser {
	
    public static final String ADMIN_NAME = "david.alonso@eurecat.org";
    public static final String ADMIN_PASSWORD = "Awareness2017";
    public static final String USER_NAME = "txema_50@mail.com";
    public static final String USER_PASSWORD = "123456";
    public static final String ANONYMOUS_NAME = "victor_50@mail.com";;
    public static final String ANONYMOUS_PASSWORD = "Awareness2017";
    
	public User admin(){
		
		User userDetails = new User(
				ADMIN_NAME, 
				ADMIN_PASSWORD,
				new ArrayList<GrantedAuthority>(
						Arrays.asList(new SimpleGrantedAuthority(Role.ADMIN.getRoleName()))));
		
		return userDetails;
	}
	
	public User user(){
		
		User userDetails = new User(
				USER_NAME,
				USER_PASSWORD,
				new ArrayList<GrantedAuthority>(
						Arrays.asList(new SimpleGrantedAuthority(Role.USER.getRoleName()))));
		return userDetails;
	}

	public User anonymous() {

		User userDetails = new User(
				ANONYMOUS_NAME,
				ANONYMOUS_PASSWORD,
				new ArrayList<GrantedAuthority>(
						Arrays.asList(new SimpleGrantedAuthority(Role.ANONYMOUS.getRoleName()))));
		return userDetails;
	}
}
