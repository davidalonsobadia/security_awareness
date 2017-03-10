package org.security_awareness.model.projections;

import java.util.Date;
import java.util.List;

import org.security_awareness.model.Activity;
import org.security_awareness.model.ActivityBlock;
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
	
	List<ActivityBlock> getActivitiesBlock();
	
	Zone getZone();
	User getManager();
		
}
