package org.security_awareness.controller;

import java.util.List;

import org.junit.Test;
import org.security_awareness.config.AbstractMvcTest;
import org.security_awareness.model.Activity;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import static org.security_awareness.config.ResultMatchersImpl.*;



public class ActivityFilterControllerTest extends AbstractMvcTest{

	private final static String RESOURCE_NAME = "activities";
	
	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}

	
	@Test
	public void Should_OkAnd1Activity_When_UserOkAndZoneLleida() throws Exception{
		String body = "{\n\t\"myZones\": false,\n\t\"concreteZone\": 4,\n\t\"month\": 1,\n\t\"year\": 2017\n}";
		
		ResultActions results = create(user(), body, "/search/findByFilters");
		
		List<Activity> activities = getEntitiesList(
				results.andReturn().getResponse().getContentAsString(),
				Activity.class);
		
		assertNotNull(activities);
		assertEquals(activities.size(), 1);
		
		verify(results, isOk());
		
	}



}
