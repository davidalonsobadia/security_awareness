package org.security_awareness.model.projections;

import org.security_awareness.model.Zone;
import org.security_awareness.model.ZoneStatus;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="projection", types = ZoneStatus.class)
public interface ZoneStatusProjection {
	
	String getStatus();
	Zone getZone();
}
