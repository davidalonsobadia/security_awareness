package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.example.model.Activity;
import com.example.model.Notification;
import com.example.model.Resource;
import com.example.model.User;
import com.example.model.Zone;

@Configuration
public class ExposeEntityIdRestConfiguration extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config){
		config.exposeIdsFor(User.class, Zone.class, Resource.class, 
				Activity.class, Notification.class);
	}
}
