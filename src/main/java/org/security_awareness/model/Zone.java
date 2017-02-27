package org.security_awareness.model;

import java.util.List;

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
