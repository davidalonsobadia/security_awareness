package org.security_awareness.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.security_awareness.config.ResultMatchersImpl.isOk;

import java.util.List;

import org.junit.Test;
import org.security_awareness.config.AbstractMvcTest;
import org.security_awareness.model.User;
import org.springframework.test.web.servlet.ResultActions;

public class UserFilterControllerTest extends AbstractMvcTest {

	private final static String RESOURCE_NAME = "users";
	
	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}
	
	@Test
	public void Should_GetAllPossibleUsers_When_NoFilters() throws Exception{
		String body = "{\n\t\"myZones\": false,\n\t\"assistant\": false,\n\t\"interested\": false\n}";
		ResultActions results = create(user(), body, "/search/findByFilters");
		List<User> users = getEntitiesList(
				results.andReturn().getResponse().getContentAsString(),
				User.class);
		
		assertNotNull(users);
		assertEquals(users.size(), 6);
		verify(results, isOk());
	}

	@Test
	public void Should_GetUsers_When_MyZonesFilter() throws Exception{
		String body = "{\n\t\"myZones\": true,\n\t\"assistant\": false,\n\t\"interested\": false\n}";
		ResultActions results = create(user(), body, "/search/findByFilters");
		List<User> users = getEntitiesList(
				results.andReturn().getResponse().getContentAsString(),
				User.class);
		
		assertNotNull(users);
		assertEquals(users.size(), 6);
		verify(results, isOk());
	}
	
	@Test
	public void Should_GetUsers_When_MyZonesAndInterestedFilters() throws Exception{
		String body = "{\n\t\"myZones\": true,\n\t\"assistant\": false,\n\t\"interested\": true\n}";
		ResultActions results = create(user(), body, "/search/findByFilters");
		List<User> users = getEntitiesList(
				results.andReturn().getResponse().getContentAsString(),
				User.class);
		
		assertNotNull(users);
		assertEquals(users.size(), 3);
		verify(results, isOk());
	}
	
	@Test
	public void Should_GetUsers_When_AllFilters() throws Exception{
		String body = "{\n\t\"myZones\": true,\n\t\"assistant\": true,\n\t\"interested\": true\n}";
		ResultActions results = create(user(), body, "/search/findByFilters");
		List<User> users = getEntitiesList(
				results.andReturn().getResponse().getContentAsString(),
				User.class);
		
		assertNotNull(users);
		assertEquals(users.size(), 1);
		verify(results, isOk());
	}
}
