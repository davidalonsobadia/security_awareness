package com.example.test.controller;

import java.util.HashMap;
import java.util.Map;

import static com.example.config.ResultMatchersImpl.isOk;
import static com.example.config.ResultMatchersImpl.isNotFound;

import org.junit.Test;

import com.example.config.AbstractMvcTest;

public class RecoverPasswordTest extends AbstractMvcTest{

	private final String RESOURCE_NAME = "recoverPassword";
	
	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}

	@SuppressWarnings("unchecked")
	@Test
	public void Should_OK_When_RightEmail() throws Exception{
		String email = "david.alonso@eurecat.org";
		
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
