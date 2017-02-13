package com.example.model.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.example.model.Notification;
import com.example.model.NotificationStatus;
import com.example.model.User;

@Projection(name="expanded", types={NotificationStatus.class})
public interface NotificationStatusesExpanded {

	long getId();
	int getStatus();
	User getUser();
	Notification getNotification();
	
	@Value("#{target.getNotification().getSender()}")
	User getNotification_Sender();
}
