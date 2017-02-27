package org.security_awareness.model;

public enum Role {
	
	ANONYMOUS, USER, ADMIN, SUPERADMIN;
	
	public String getRoleName(){
		return "ROLE_" + this.name();
	}
}
