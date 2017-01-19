package com.example.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.example.model.Role;

@Component
public class AuthenticationUser {
	
    public static final String ADMIN_NAME = "alonso_50";
    public static final String ADMIN_PASSWORD = "123456";
    public static final String USER_NAME = "victor_50";
    public static final String USER_PASSWORD = "123456";
    
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
}
