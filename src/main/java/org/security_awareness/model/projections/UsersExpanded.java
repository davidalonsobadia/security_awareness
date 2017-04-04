package org.security_awareness.model.projections;

import java.util.Set;

import org.security_awareness.model.Authority;
import org.security_awareness.model.User;
import org.security_awareness.model.UserConfiguration;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="expanded", types = User.class)
public interface UsersExpanded {
	long getId();
	String getFirstName();
	String getLastName();
	String getEmail();
	String getCity();
	
	UserConfiguration getConfiguration();

	Set<ZoneStatusProjection> getZoneStatus();
	
	Authority getAuthority();
}
