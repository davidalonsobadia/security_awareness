package org.security_awareness.controller;

import org.junit.Test;
import org.security_awareness.config.AbstractMvcTest;

import static org.security_awareness.config.ResultMatchersImpl.*;

public class RegisterControllerTest extends AbstractMvcTest{
	
//	private final static String REGISTER_USER = "{\n\t\"firstName\": \"Victor\",\n\t\""
//			+ "lastName\": \"Pomareda\",\n\t\"email\": \"victor_50@mail.com\"\n}";
	
	
	private final static String REGISTER_USER = "{\n\t\"user\": {\n\t\t\"firstName\": \"Victor\","
			+ "\n\t\t\"lastName\": \"Pomareda\",\n\t\t\"email\": \"victor_50@mail.com\","
			+ "\n\t\t\"configuration\": {\n\t\t\t\"visibility\": 1,"
			+ "\n\t\t\t\"notificationsReceived\": 1\n\t\t},\n\t\t\"city\": \"Barcelona\"\n\t},"
			+ "\n\t\"password\": {\n\t\t\"password\": \"123456\"\n\t}\n}";
	
	private final static String ALREADY_REGISTERED_USER = "{\n\t\"user\": {\n\t\t\"firstName\": \"Txema\","
			+ "\n\t\t\"lastName\": \"Rodriguez\",\n\t\t\"email\": \"txema_50@mail.com\","
			+ "\n\t\t\"configuration\": {\n\t\t\t\"visibility\": 0,"
			+ "\n\t\t\t\"notificationsReceived\": 0\n\t\t},\n\t\t\"city\": \"Guipuzcoa\"\n\t},"
			+ "\n\t\"password\": {\n\t\t\"password\": \"123456\"\n\t}\n}";
		
	private final static String NOT_REGISTER_USER = "{\n\t\"user\": {\n\t\t\"firstName\": \"Sergi\","
			+ "\n\t\t\"lastName\": \"Alonso\",\n\t\t\"email\": \"sergi.not.found@mail.com\","
			+ "\n\t\t\"configuration\": {\n\t\t\t\"visibility\": 1,"
			+ "\n\t\t\t\"notificationsReceived\": 0\n\t\t},\n\t\t\"city\": \"Guipuzcoa\"\n\t},"
			+ "\n\t\"password\": {\n\t\t\"password\": \"123456\"\n\t}\n}";
	

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
