package com.example.repositories;

import static com.example.config.ResultMatchersImpl.isOk;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import com.example.config.AbstractMvcTest;
import com.example.model.Activity;

public class ActivityRepositoryTest extends AbstractMvcTest{


	private static String RESOURCE_NAME = "activities";
	
	@Test
	public void Should_getActivitiesResult_When_MonthAndYear() throws Exception  {
		
		String params = "search/findAllByMonthAndYear?month=%d&year=%d";
		params = String.format(params, 1, 2017);
		
		ResultActions action = readWithVariables(user(), params);
		verify(action, isOk());
		
		List<Activity> activities = getEntitiesList(
				action.andReturn().getResponse().getContentAsString(),
				Activity.class);
		assertEquals(activities.size(), 4);
	}
	
	@Test
	public void Should_getActivitiesResult_When_MonthAndYearAndZone() throws Exception  {
		
		String params = "search/findAllByMonthAndYearAndZone?month=%d&year=%d&zone=%s";
		params = String.format(params, 1, 2017, "Barcelona");
		
		ResultActions action = readWithVariables(user(), params);
		verify(action, isOk());
		
		List<Activity> activities = getEntitiesList(
				action.andReturn().getResponse().getContentAsString(),
				Activity.class);
		assertEquals(activities.size(), 3);
	}
	
	@Test
	public void Should_get4ActivitiesResult_When_MonthAndYearAndUserZones() throws Exception  {
		
		String params = "search/findAllByMonthAndYearAndUserZones?month=%d&year=%d&user=%s";
		params = String.format(params, 1, 2017, "gonzalo_50@mail.com");
		
		ResultActions action = readWithVariables(user(), params);
		verify(action, isOk());
		
		List<Activity> activities = getEntitiesList(
				action.andReturn().getResponse().getContentAsString(),
				Activity.class);
		assertEquals(activities.size(), 4);
	}
	
	@Test
	public void Should_get3ActivitiesResult_When_MonthAndYearAndUserZones() throws Exception  {
		
		String params = "search/findAllByMonthAndYearAndUserZones?month=%d&year=%d&user=%s";
		params = String.format(params, 1, 2017, "juan_50@mail.com");
		
		ResultActions action = readWithVariables(user(), params);
		verify(action, isOk());
		
		List<Activity> activities = getEntitiesList(
				action.andReturn().getResponse().getContentAsString(),
				Activity.class);
		assertEquals(activities.size(), 3);
	}
	
	@Test
	public void Should_getInterestedActivities_When_UserInterested() throws Exception {
		StringBuilder route = new StringBuilder("search/findAllByInterestedAndUser");
		route.append("?");
		route.append("user=");
		route.append("sergi_50@mail.com");
		
		ResultActions action = readWithVariables(user(), route.toString());
		verify(action, isOk());
		
		List<Activity> activities = getEntitiesList(
				action.andReturn().getResponse().getContentAsString(),
				Activity.class);
		assertEquals(activities.size(), 4);

	}
	
	@Test
	public void Should_getAssistantActivities_When_UserAssistant() throws Exception {
		StringBuilder route = new StringBuilder("search/findAllByAssistantAndUser");
		route.append("?");
		route.append("user=");
		route.append("sergi_50@mail.com");
		
		ResultActions action = readWithVariables(user(), route.toString());
		verify(action, isOk());
		
		List<Activity> activities = getEntitiesList(
				action.andReturn().getResponse().getContentAsString(),
				Activity.class);
		assertEquals(activities.size(), 1);

	}

	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}

}
