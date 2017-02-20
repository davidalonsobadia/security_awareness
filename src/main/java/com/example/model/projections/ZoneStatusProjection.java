package com.example.model.projections;

import org.springframework.data.rest.core.config.Projection;

import com.example.model.User;
import com.example.model.Zone;
import com.example.model.ZoneStatus;

@Projection(name="projection", types = ZoneStatus.class)
public interface ZoneStatusProjection {
	
	String getStatus();
	Zone getZone();
	//User getUser();
}
