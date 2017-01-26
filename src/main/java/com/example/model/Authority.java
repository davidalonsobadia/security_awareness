package com.example.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="authorities", uniqueConstraints={
		@UniqueConstraint(name="unique_roles", columnNames="role")
})
public class Authority {
	
	// Roles = USER, ADMIN, SUPERADMIN, GOD 
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.STRING)
	@NotNull
	private Role role;
	
	@OneToMany(mappedBy="authority")
	private Set<User> users;
	
	public Authority(){}
	
	public Authority(Role role) {
		super();
		this.role = role;
	}	
	
	public Long getId() {
		return id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
	
}
