package com.example.model;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="zones")
public class Zone {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	
//	@OneToMany(mappedBy="zoneManager")
//	private List<User> userManagers;
		
	@OneToMany(mappedBy="zone")
	private List<ZoneStatus> zoneStatus;
	
	public Zone() {}
	
	public Zone(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<User> getManager() {
//		return userManagers;
//	}
//
//	public void setManager(List<User> manager) {
//		this.userManagers = manager;
//	}

	public long getId() {
		return id;
	}

	public List<ZoneStatus> getZoneStatus() {
		return zoneStatus;
	}

	public void setZoneStatus(List<ZoneStatus> zoneStatus) {
		this.zoneStatus = zoneStatus;
	}
}
