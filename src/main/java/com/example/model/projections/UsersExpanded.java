package com.example.model.projections;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.example.model.Authority;
import com.example.model.User;
import com.example.model.Zone;
import com.example.model.ZoneStatus;

@Projection(name="expanded", types = User.class)
public interface UsersExpanded {
	long getId();
	String getFirstName();
	String getLastName();
	String getCity();
	boolean getRegistered();
	
	//Zone getZoneManager();

	Set<ZoneStatusProjection> getZoneStatus();
	
	Authority getAuthority();
}
