package com.example.test.controller;

import org.junit.Test;

import com.example.repositories.AbstractRepositoryTest;

public class ActivityControllerTest extends AbstractRepositoryTest{


	private static final String RESOURCE_NAME = "/activities/search/findByMonthAndYear";
	
	@Test
	public void Should_getActivitiesResult_When_MonthAndYear() throws Exception  {
		verify(read(user()), isOk());
	}

	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}

}
