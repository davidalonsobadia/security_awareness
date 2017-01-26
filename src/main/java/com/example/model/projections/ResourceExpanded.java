package com.example.model.projections;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.example.model.Resource;
import com.example.model.User;

@Projection(name="expanded", types={Resource.class})
public interface ResourceExpanded {
	long getId();
	String getName();
	String getUrl();
	int getType();
	Date getCcreationDate();
	User getCreator();
}
