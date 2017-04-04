package org.security_awareness.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="activities")
public class Activity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	
	@JsonIgnore
	private Date dateTimeStart;
	
	@JsonIgnore
	private Date dateTimeEnd;
	
	private String place;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name="activity_id")
	List<ActivityBlock> activitiesBlock;
	
	@ManyToOne
	private Zone zone;
	
	// Type: Meeting(0), Training(1), Conference(2), Event(3)  
	private int type;
	
	private int numRepeats;
	
	@Type(type="text")
	private String description;
	
	@ManyToOne
	private User manager;
		
	@OneToMany(mappedBy="activity")
	private List<ActivityStatus> activityStatus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(Date dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	public Date getDateTimeEnd() {
		return dateTimeEnd;
	}

	public void setDateTimeEnd(Date dateTimeEnd) {
		this.dateTimeEnd = dateTimeEnd;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public int getNumRepeats() {
		return numRepeats;
	}

	public long getId() {
		return id;
	}

	public void setNumRepeats(int numRepeats) {
		this.numRepeats = numRepeats;
	}
	
	public List<ActivityBlock> getActivitiesBlock() {
		return activitiesBlock;
	}

	public void setActivitiesBlock(List<ActivityBlock> activitiesBlock) {
		this.activitiesBlock = activitiesBlock;
	}

	public List<ActivityStatus> getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(List<ActivityStatus> activityStatus) {
		this.activityStatus = activityStatus;
	}
}
