package com.example.model.projections;

import org.springframework.data.rest.core.config.Projection;

import com.example.model.User;
import com.example.model.Zone;

@Projection(name="expanded", types={Zone.class})
public interface ZonesExpanded {
	long getId();
	String getName();
	User getManager();
}
