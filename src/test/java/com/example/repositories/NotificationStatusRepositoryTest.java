package com.example.repositories;

import static com.example.config.ResultMatchersImpl.isOk;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import com.example.config.AbstractMvcTest;
import com.example.model.NotificationStatus;
import com.example.repository.NotificationStatusRepository;

public class NotificationStatusRepositoryTest extends AbstractMvcTest{
	
	@Autowired
	NotificationStatusRepository notificationStatusRepository;

	private static final String RESOURCE_NAME = "notificationStatuses";
	
	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}

	
	@Test
	public void Should_getNotificationStatusByUser_When_UserEmail() throws Exception  {
		
		String route = "search/findAllByUser_email?user=%s";
		route = String.format(route, user().getUsername());
		
		ResultActions results = readWithVariables(user(), route);
		verify(results, isOk());	
	}
	
	@Test
	public void Should_getNotificationStatusByUserOrdered_When_UserEmail() throws Exception  {
		
		StringBuilder route = new StringBuilder("search/findAllByUser_emailOrderByNotification_DateDesc");
		route.append("?user=");
		route.append(user().getUsername());
		route.append("&projection=expanded");
				
		ResultActions results = readWithVariables(user(), route.toString());
		verify(results, isOk());
		
		List<NotificationStatus> notificationStatuses = getEntitiesList(
				results.andReturn().getResponse().getContentAsString(),
				NotificationStatus.class);
		
		for(int i = 0 ; i < notificationStatuses.size() - 1 ; i++)	{
			Date currentDate = notificationStatuses.get(i).getNotification().getDate();
			Date nextDate = notificationStatuses.get(i+1).getNotification().getDate();
			
			assertTrue(currentDate.compareTo(nextDate) >= 0);	
		}
	}
}
