package org.security_awareness.model.projections;

import org.security_awareness.model.User;
import org.security_awareness.model.Zone;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="expanded", types={Zone.class})
public interface ZonesExpanded {
	long getId();
	String getName();
	User getManager();
}
