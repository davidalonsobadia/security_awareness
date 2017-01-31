package com.example.test.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.config.AbstractMvcTest;
import com.example.service.UserService;

public class ChangePasswordTest extends AbstractMvcTest{

	private static final String RESOURCE_NAME = "changePassword";
	
	@Autowired
	private UserService userService;
	
	private String body = "{\n\t\"oldPassword\": \"%s\","
			+ "\n\t\"newPassword\": \"111111\","
			+ "\n\t\"userId\": %d\n}";
	
	private String rightPassword = "123456";
	private String wrongPassword = "wrongPassword";
	
	@Test
	public void Should_ChangePasswordOk_When_ValidPasswordAndUserName() throws Exception{
		body = String.format(body, rightPassword, getUserId());
		verify(create(user(), body), isOk());
	}
	
	@Test
	public void Should_ReturnConflict_When_InvalidUserId() throws Exception {
		body = String.format(body, rightPassword, getUserId()+1);
		verify(create(user(), body), isConflict());
	}
	
	@Test
	public void Should_ReturnUnauthorized_When_InvalidCredentials() throws Exception {
		body = String.format(body, wrongPassword, getUserId());
		verify(create(user(), body), isUnauthorized());
	}

	private int getUserId(){
		return (int) userService.findByEmail(user().getUsername()).getId();
	}

	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}

}
