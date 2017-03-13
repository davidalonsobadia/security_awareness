package org.security_awareness.utils;

public class ActivityContext {
	
	private String name;
	
	private String description;
	
	private int numRepeats;
	
	private String place;
	
	private int type;
	
	private Object zone;
	
	private Object manager;
	
	private String dateStart;
	
	private String dateEnd;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumRepeats() {
		return numRepeats;
	}

	public void setNumRepeats(int numRepeats) {
		this.numRepeats = numRepeats;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Object getZone() {
		return zone;
	}

	public void setZone(Object zone) throws Exception {
		this.zone = zone;
	}

	public Object getManager() {
		return manager;
	}

	public void setManager(Object manager) {
		this.manager = manager;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
		
}
