package com.example.model.projections;

import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

import com.example.model.User;
import com.example.model.UserConfiguration;
import com.example.model.Zone;

@Projection(name="expanded", types = User.class)
public interface UsersExpanded {
	long getId();
	String getFirstName();
	String getLastName();
	String getCity();
	boolean getRegistered();
	
	Set<Zone> getZones();
		
}
