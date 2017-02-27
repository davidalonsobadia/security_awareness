package org.security_awareness.model.projections;

import java.util.Date;

import org.security_awareness.model.Activity;
import org.security_awareness.model.User;
import org.security_awareness.model.Zone;
import org.springframework.data.rest.core.config.Projection;

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
		
}
