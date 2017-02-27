package org.security_awareness.model.projections;

import org.security_awareness.model.User;
import org.security_awareness.model.Zone;
import org.security_awareness.model.ZoneStatus;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="expanded", types={ZoneStatus.class})
public interface ZoneStatusesExpanded {

	long getId();
	int getStatus();
	User getUser();
	Zone getZone();
}
