package com.example.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.example.utils.BCryptPasswordDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name="users",
	uniqueConstraints={@UniqueConstraint(name="unique_email", columnNames={"email"})})
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String firstName;
	
	private String lastName;
	
	private String city;
	
	private boolean registered;
	
	@OneToOne
	private User manager;
	
	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	@OneToMany
	private Set<Zone> zones;	
	
	@OneToMany
	private Set<Notification> notifications;
	
	@OneToMany
	private Set<Activity> activities;
	
	@OneToMany
	private Set<Resource> resources;
	
	@OneToOne
	private UserConfiguration configuration;
	
	@JsonDeserialize(using = BCryptPasswordDeserializer.class )
	@JsonIgnore
	private String password;
		
	@NotEmpty
	@NotNull
	@Column(unique = true)
	private String email;
	
	@ManyToOne
	private Authority authority;
	
	public User(){}
	
	public User(String firstName, String lastName, String password, 
			String username, Authority authority, UserConfiguration configuration) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = username;
		this.authority = authority;
		this.configuration = configuration;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public Set<Zone> getZones() {
		return zones;
	}

	public void setZones(Set<Zone> zones) {
		this.zones = zones;
	}

	public Set<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public UserConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(UserConfiguration configuration) {
		this.configuration = configuration;
	}	
}
