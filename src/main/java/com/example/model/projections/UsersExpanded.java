package com.example.model.projections;

import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

import com.example.model.Authority;
import com.example.model.User;
import com.example.model.UserConfiguration;

@Projection(name="expanded", types = User.class)
public interface UsersExpanded {
	long getId();
	String getFirstName();
	String getLastName();
	String getEmail();
	String getCity();
	boolean getRegistered();
	
	UserConfiguration getConfiguration();

	Set<ZoneStatusProjection> getZoneStatus();
	
	Authority getAuthority();
}
