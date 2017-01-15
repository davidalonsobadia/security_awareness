package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.example.utils.BCryptPasswordDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name="users",
	uniqueConstraints={@UniqueConstraint(name="unique_user_name", columnNames={"username"})})
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String firstName;
	
	private String lastName;
	
	@JsonDeserialize(using = BCryptPasswordDeserializer.class )
	@JsonIgnore
	private String password;
		
	@NotEmpty
	@NotNull
	@Column(unique = true)
	private String username;
	
	@ManyToOne
	private Authority authority;
	
	public User(){}
	
	public User(String firstName, String lastName, String password, String username, Authority authority) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.username = username;
		this.authority = authority;
	}	

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
	
	public long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}	
}
