package com.example.model;

public enum Role {
	
	USER, ADMIN, SUPERADMIN, GOD;
	
	public String getRoleName(){
		return "ROLE_" + this.name();
	}
}
