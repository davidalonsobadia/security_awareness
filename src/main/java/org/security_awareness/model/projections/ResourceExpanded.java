package org.security_awareness.model.projections;

import java.util.Date;

import org.security_awareness.model.Resource;
import org.security_awareness.model.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="expanded", types={Resource.class})
public interface ResourceExpanded {
	long getId();
	String getName();
	String getUrl();
	int getType();
	Date getCreationDate();
	User getCreator();
}
