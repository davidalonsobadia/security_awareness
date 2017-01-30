package com.example.repositories;

import java.util.List;

import javax.persistence.Entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

public class ActivityRepositoryTest extends AbstractRepositoryTest{


	private static String RESOURCE_NAME = "activities";
	
	@Test
	public void Should_getActivitiesResult_When_MonthAndYear() throws Exception  {
		
		String params = "search/findAllByMonthAndYear?month=%d&year=%d";
		params = String.format(params, 1, 2017);
		
		ResultActions action = read(user(), params);
		verify(action, isOk());
		
		List<? extends Entity> activities = getEntitiesList(action.andReturn().getResponse().getContentAsString());
		assertEquals(activities.size(), 4);
	}
	
	@Test
	public void Should_getActivitiesResult_When_MonthAndYearAndZone() throws Exception  {
		
		String params = "search/findAllByMonthAndYearAndZone?month=%d&year=%d&zone=%s";
		params = String.format(params, 1, 2017, "Barcelona");
		
		ResultActions action = read(user(), params);
		verify(action, isOk());
		
		List<? extends Entity> activities = getEntitiesList(action.andReturn().getResponse().getContentAsString());
		assertEquals(activities.size(), 3);
	}
	
	@Test
	public void Should_get4ActivitiesResult_When_MonthAndYearAndUserZones() throws Exception  {
		
		String params = "search/findAllByMonthAndYearAndUserZones?month=%d&year=%d&user=%s";
		params = String.format(params, 1, 2017, "gonzalo_50@mail.com");
		
		ResultActions action = read(user(), params);
		verify(action, isOk());
		
		List<? extends Entity> activities = getEntitiesList(action.andReturn().getResponse().getContentAsString());
		assertEquals(activities.size(), 4);
	}
	
	@Test
	public void Should_get3ActivitiesResult_When_MonthAndYearAndUserZones() throws Exception  {
		
		String params = "search/findAllByMonthAndYearAndUserZones?month=%d&year=%d&user=%s";
		params = String.format(params, 1, 2017, "juan_50@mail.com");
		
		ResultActions action = read(user(), params);
		verify(action, isOk());
		
		List<? extends Entity> activities = getEntitiesList(action.andReturn().getResponse().getContentAsString());
		assertEquals(activities.size(), 3);
	}

	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}

}
