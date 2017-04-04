package org.security_awareness.utils;

import org.security_awareness.model.Password;
import org.security_awareness.model.UserConfiguration;

public class UserPostContext {


	private String firstName;
	
	private String lastName;
	
	private String city;
		
	private Object manager;
			
	private UserConfiguration configuration;
	
	private Password password;
		
	private String email;
	
	private String authority;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Object getManager() {
		return manager;
	}

	public void setManager(Object manager) {
		this.manager = manager;
	}

	public UserConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(UserConfiguration configuration) {
		this.configuration = configuration;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
	
}
