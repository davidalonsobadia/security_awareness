package com.example.model.projections;

import java.util.Date;
import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

import com.example.model.Activity;
import com.example.model.User;
import com.example.model.Zone;

@Projection(name="expanded", types={Activity.class})
public interface ActivitiesExpanded {
	long getId();
	String getName();
	Date getDateTimeStart();
	Date getDateTimeEnd();
	String getPlace();
	int getNumRepeats();
	int getType();
	String getDescription();
	
	Zone getZone();
	User getManager();
	Set<User> getUsers();
	
}
