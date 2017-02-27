package org.security_awareness.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="zone_status", uniqueConstraints={@UniqueConstraint(columnNames={"user_id", "zone_id"}, 
name="UK_user_zone")})
public class ZoneStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
		
	// Requested(0), Accepted/added(1), Admin(2)
	private int status;
	
	@ManyToOne//(fetch=FetchType.EAGER, cascade?=)
	private Zone zone;
	
	@ManyToOne
	private User user;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}
}
