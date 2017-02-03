package com.example.test.controller;

import static com.example.config.ResultMatchersImpl.isConflict;
import static com.example.config.ResultMatchersImpl.isNoContent;
import static com.example.config.ResultMatchersImpl.isNotFound;

import org.junit.Test;

import com.example.config.AbstractMvcTest;

public class RegisterControllerTest extends AbstractMvcTest{
	
	private final static String REGISTER_USER = "{\n\t\"firstName\": \"Victor\",\n\t\""
			+ "lastName\": \"Pomareda\",\n\t\"email\": \"victor_50@mail.com\"\n}";

	private final static String ALREADY_REGISTERED_USER = "{\n\t\"firstName\": \"Victor\",\n\t\""
			+ "lastName\": \"Pomareda\",\n\t\"email\": \"txema_50@mail.com\"\n}";
	
	private final static String NOT_REGISTER_USER = "{\n\t\"firstName\": \"Victor\",\n\t\""
			+ "lastName\": \"Pomareda\",\n\t\"email\": \"sergi.not.found@mail.com\"\n}";

	private final String RESOURCE_NAME = "register"; 
	

	@Override
	public String getResourceName(){
		return RESOURCE_NAME;
	}
	

	@Test
	public void Should_AddUserAndSetRoleUser_When_UserWithRoleAnonymous() throws Exception {					
		verify(create(anonymous(), REGISTER_USER), isNoContent());	
	}
	
	@Test
	public void Should_ConflictStatus_When_UserAlreadyRegistered() throws Exception {
		verify(create(anonymous(), ALREADY_REGISTERED_USER), isConflict());
	}
	
	@Test
	public void Should_NotFound_When_UserNotExists() throws Exception {
		verify(create(anonymous(), NOT_REGISTER_USER), isNotFound());
	}
	
}
