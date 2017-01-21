package com.example.model;

public enum Role {
	
	ANONYMOUS, USER, ADMIN, SUPERADMIN;
	
	public String getRoleName(){
		return "ROLE_" + this.name();
	}
}
