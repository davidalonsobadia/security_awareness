package com.example.repositories;

import java.util.List;

import static org.junit.Assert.assertEquals;

import javax.persistence.Entity;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import com.example.config.AbstractMvcTest;

public class NotificationStatusRepositoryTest extends AbstractMvcTest{

	private static final String RESOURCE_NAME = "notificationStatuses";
	
	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}

	
	@Test
	public void Should_getNotificationStatusByUser_When_UserEmail() throws Exception  {
		
		String route = "search/findAllByUser_email?user=%s";
		route = String.format(route, "gonzalo_50@mail.com");
		
		ResultActions results = read(user(), route);
		verify(results, isOk());
		
		List<? extends Entity> notificationStatuses = getEntitiesList(results.andReturn().getResponse().getContentAsString());
		
		assertEquals(notificationStatuses.size(), 5);
	}
}
