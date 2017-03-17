package org.security_awareness.controller;

import static org.junit.Assert.assertTrue;
import static org.security_awareness.config.ResultMatchersImpl.isNotFound;
import static org.security_awareness.config.ResultMatchersImpl.isOk;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;
import org.security_awareness.config.AbstractMvcTest;
import org.springframework.test.web.servlet.ResultActions;

public class IsRegisteredControllerTest extends AbstractMvcTest{
	
	private final String RESOURCE_NAME = "isRegistered";

	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}

	@SuppressWarnings("unchecked")
	@Test
	public void Should_OKAndTrue_When_UserIsRegistered() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", "david.alonso@eurecat.org");
		
		ResultActions result = readWithParams(user(), map);
		verify(result, isOk());
		
		JSONObject jsonObject = new JSONObject(result.andReturn().getResponse().getContentAsString());
		assertTrue((boolean)jsonObject.get("registered"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void Should_OKAndFalse_When_UserNotRegistered() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", "juan.caubet@eurecat.org");
		
		ResultActions result = readWithParams(user(), map);
		verify(result, isOk());
		
		JSONObject jsonObject = new JSONObject(result.andReturn().getResponse().getContentAsString());
		assertTrue(!(boolean)jsonObject.get("registered"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void Should_NotFound_WhenUserNotFound() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", "userNotFound@mail.com");
		
		verify(readWithParams(user(), map), isNotFound());
	}
}
