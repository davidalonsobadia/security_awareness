package org.security_awareness.model.projections;

import org.security_awareness.model.Activity;
import org.security_awareness.model.ActivityStatus;
import org.security_awareness.model.User;
import org.security_awareness.model.Zone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

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
