package com.example.test.security;

import org.junit.Test;

import com.example.repositories.AbstractRepositoryTest;


public class RegisterControllerTest extends AbstractRepositoryTest{
	
	private final static String REGISTER_USER = "{\n\t\"firstName\": \"Victor\",\n\t\""
			+ "lastName\": \"Pomareda\",\n\t\"email\": \"victor_50@mail.com\"\n}";

	private final String RESOURCE_NAME = "register";
	
	public String getResourceName(){
		return RESOURCE_NAME;
	}
	
	@Test
	public void Should_AddUserAndSetRoleUser_When_UserWithRoleAnonymous() throws Exception {		
		verify(create(anonymous(), REGISTER_USER), isNoContent());
		
	}
	
	@Test
	public void Should_get4xxStatus_When_UserAlreadyRegistered() throws Exception {
		verify(create(anonymous(), REGISTER_USER), isConflict());
	}
	
}
