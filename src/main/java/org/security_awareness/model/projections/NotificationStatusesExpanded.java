package org.security_awareness.model.projections;

import org.security_awareness.model.Notification;
import org.security_awareness.model.NotificationStatus;
import org.security_awareness.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="expanded", types={NotificationStatus.class})
public interface NotificationStatusesExpanded {

	long getId();
	int getStatus();
	User getUser();
	Notification getNotification();
	
	@Value("#{target.getNotification().getSender()}")
	User getNotification_Sender();
}
