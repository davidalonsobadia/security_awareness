package org.security_awareness.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="users",
	uniqueConstraints={@UniqueConstraint(name="unique_email", columnNames={"email"})})
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String firstName;
	
	private String lastName;
	
	private String city;
		
	@ManyToOne
	private User manager;
			
	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	@JsonIgnore
	private Set<ZoneStatus> zoneStatus;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	@JsonIgnore
	private Set<NotificationStatus> notificationStatus;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	@JsonIgnore
	private Set<ActivityStatus> activityStatus;
		
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private UserConfiguration configuration;
	
	@OneToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	private Password password;
		
	@NotEmpty
	@NotNull
	@Column(unique = true)
	private String email;
	
	@ManyToOne
	private Authority authority;
	
	public User(){}
	
	public User(String firstName, String lastName, Password password, 
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

	@JsonIgnore
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

	public UserConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(UserConfiguration configuration) {
		this.configuration = configuration;
	}
	
	public Set<NotificationStatus> getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(Set<NotificationStatus> notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public Set<ActivityStatus> getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Set<ActivityStatus> activityStatus) {
		this.activityStatus = activityStatus;
	}

	public Set<ZoneStatus> getZoneStatus() {
		return zoneStatus;
	}

	public void setZoneStatus(Set<ZoneStatus> zoneStatus) {
		this.zoneStatus = zoneStatus;
	}
}
