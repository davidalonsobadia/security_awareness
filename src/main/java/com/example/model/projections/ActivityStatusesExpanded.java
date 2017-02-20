package com.example.model.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.example.model.Activity;
import com.example.model.ActivityStatus;
import com.example.model.User;
import com.example.model.Zone;

@Projection(name="expanded", types={ActivityStatus.class})
public interface ActivityStatusesExpanded {

	long getId();
	boolean getInterested();
	boolean getAssistant();
	Activity getActivity();
	
	User getUser();
	
	@Value("#{target.getActivity().getZone()}")
	Zone getZone();
	
}
