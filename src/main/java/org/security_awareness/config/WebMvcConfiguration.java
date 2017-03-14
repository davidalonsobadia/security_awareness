package org.security_awareness.config;

import org.security_awareness.model.Activity;
import org.security_awareness.model.Notification;
import org.security_awareness.model.NotificationStatus;
import org.security_awareness.model.Resource;
import org.security_awareness.model.User;
import org.security_awareness.model.Zone;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
public class WebMvcConfiguration extends RepositoryRestMvcConfiguration{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config){
		config.exposeIdsFor(User.class, Zone.class, Resource.class, 
				Activity.class, Notification.class, NotificationStatus.class);
		config.setReturnBodyOnCreate(true);
	}
}
