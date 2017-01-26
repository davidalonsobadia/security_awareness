package com.example.model.projections;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.example.model.Notification;
import com.example.model.User;

@Projection(name="expanded", types={Notification.class})
public interface NotificationsExpanded {
	long getId();
	String getTitle();
	Date getDate();
	String getDescription();
	User getSender();
}
