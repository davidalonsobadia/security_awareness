package com.example.test.controller;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.config.AbstractMvcTest;
import com.example.service.UserService;

import ch.qos.logback.classic.Logger;

public class ChangePasswordTest extends AbstractMvcTest{

	private static final String RESOURCE_NAME = "changePassword";
	
	@Autowired
	private UserService userService;
	
	private final String body = "{\n\t\"oldPassword\": \"%s\","
			+ "\n\t\"newPassword\": \"%s\","
			+ "\n\t\"userId\": %d\n}";
	
	private String rightPassword = "123456";
	private String wrongPassword = "wrongPassword";
	private String newPassword = "111111";
	
	@Test
	public void Should_ChangePasswordOk_When_ValidPasswordAndUserName() throws Exception{
		String bodyTest1 = String.format(body, rightPassword, newPassword, getUserId());
		verify(create(user(), bodyTest1), isOk());
		
		// RESET THE PASSWORD FOR USER. OTHERWISE NEXT TESTS COULDN'T WORK
		String bodyTest2 = String.format(body,  newPassword, rightPassword, getUserId());
		create(user(), bodyTest2);
	}
	
	@Test
	public void Should_ReturnConflict_When_InvalidUserId() throws Exception {
		String bodyTest = String.format(body, rightPassword, newPassword, getUserId()+1);
		verify(create(user(), bodyTest), isConflict());
	}
	
	@Test
	public void Should_ReturnUnauthorized_When_InvalidCredentials() throws Exception {
		String bodyTest = String.format(body, wrongPassword, newPassword, getUserId());
		verify(create(user(), bodyTest), isUnauthorized());
	}
	
	private int getUserId(){
		return (int) userService.findByEmail(user().getUsername()).getId();
	}

	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}

}
