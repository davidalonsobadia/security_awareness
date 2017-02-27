package org.security_awareness.utils;

import org.security_awareness.model.Password;
import org.security_awareness.model.User;

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
