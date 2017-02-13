package com.example.utils;

import com.example.model.Password;
import com.example.model.User;

public class UserContext {
	
	private User user;
	
	private Password password;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}
}
