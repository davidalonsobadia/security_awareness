package org.security_awareness.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.security_awareness.config.AbstractMvcTest;

import static org.security_awareness.config.ResultMatchersImpl.*;
public class RecoverPasswordTest extends AbstractMvcTest{

	private final String RESOURCE_NAME = "recoverPassword";
	
	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}

	@SuppressWarnings("unchecked")
	@Test
	public void Should_OK_When_RightEmail() throws Exception{
		String email = "txema_50@mail.com";
		
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		
		verify(readWithParams(user(), map), isOk());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void Should_404_When_WrongEmail() throws Exception{
		String email = "wrongEmail@mail.com";
		
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		
		verify(readWithParams(user(), map), isNotFound());
	}



}
