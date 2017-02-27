package org.security_awareness.model.projections;

import java.util.Date;

import org.security_awareness.model.Notification;
import org.security_awareness.model.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="expanded", types={Notification.class})
public interface NotificationsExpanded {
	long getId();
	String getTitle();
	Date getDate();
	String getDescription();
	User getSender();
}
