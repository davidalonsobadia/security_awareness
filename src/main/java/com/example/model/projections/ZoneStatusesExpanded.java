package com.example.model.projections;

import org.springframework.data.rest.core.config.Projection;

import com.example.model.User;
import com.example.model.Zone;
import com.example.model.ZoneStatus;

@Projection(name="expanded", types={ZoneStatus.class})
public interface ZoneStatusesExpanded {

	long getId();
	int getStatus();
	User getUser();
	Zone getZone();
}
