package org.security_awareness.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.security_awareness.config.ResultMatchersImpl.isCreated;
import static org.security_awareness.config.ResultMatchersImpl.isForbidden;
import static org.security_awareness.config.ResultMatchersImpl.isNoContent;
import static org.security_awareness.config.ResultMatchersImpl.isOk;

import java.util.List;

import org.json.JSONObject;
import org.junit.Test;
import org.security_awareness.config.AbstractMvcTest;
import org.security_awareness.model.User;
import org.springframework.test.web.servlet.ResultActions;

public class UserRepositoryTest extends AbstractMvcTest{
	
	private static final String NEW_USER_CONTENT = "{\r\n\t\"firstName\": \"Pau\",\r\n\t\"lastName\":"
			+ " \"Li\",\r\n\t\"city\": \"Sant Cugat\",\r\n\t\"email\": \"pau_50@mail.com\","
			+ "\r\n\t\"configuration\": {\r\n\t\t\"visibility\": 0,\r\n\t\t\"notificationsReceived\": 0\r\n\t},"
			+ "\r\n\t\"manager\": 2,"
			+ "\r\n\t\"authority\": \"user\"\r\n}";
	
	private static final String NEW_USER_EMAIL = "pau_50@mail.com";
	
	/**
	 * TEST WITH ROLE: USER
	 */
    public static final String RESOURCE_NAME = "users";
    

	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}
    
	/*
	 * Test 1. Get details from registered User with Role User
	 */
    @Test
    public void Should_getUserDetails_When_UserWithRoleUser() throws Exception{
    	verify(readWithVariables(user()), isOk());
    	
    }
    
	/*
	 * Test 2. Get details of other user from registered User with Role User
	 */
    @Test
    public void Should_getUserDetails_When_UserDetailsFromANotherUserWithRoleUser() throws Exception{
    	verify(readWithVariables(user(), "/1"), isOk());
    }
	
	/*
	 * Test 3. Post/Put User with registered User with Role User
	 */
	@Test 
	public void Should_getErrorMessage_When_PostUserWithRoleUser() throws Exception{    	    	
    	verify(create(user(), NEW_USER_CONTENT), isForbidden());
	}
	
    
	/*
	 * Test 4. Delete User with registered User with Role User
	 */
	@Test
	public void Should_getErrorMessage_When_DeleteUserWithRoleUser() throws Exception{ 
		verify(remove(user(), "/2"), isForbidden());
	}
	
	
	
	/**
	 * TESTS WITH ROLE: ADMIN
	 */
	
	/*
	 * Test 1. Get details from registered User with Role Admin
	 */	
	@Test
	public void Should_getAllUserDetails_When_UserWithRoleAdmin() throws Exception{		
		ResultActions result = readWithVariables(admin());
		
		verify(result, isOk());
		
		List<User> users = getEntitiesList(
				result.andReturn().getResponse().getContentAsString(),
				User.class);
		assertNotNull(users);
		assertTrue(users.size() > 1);
		
	}
	
	/*
	 * Test 2. Get details of other user from registered User with Role Admin
	 */
	@Test
	public void Should_getUserDetailsOfAnotherUser_When_UserWithRoleAdmin() throws Exception{
		ResultActions result = readWithVariables(admin(), "/4");
		
		verify(result, isOk());
		
    	User user = mapper.readValue(result.andReturn().getResponse().getContentAsByteArray(), User.class);
    	
    	assertNotNull(user);
    	assertEquals(user.getEmail(), "sergi_50@mail.com");
	}
	
	/*
	 * Test 3. Post/Put User with registered User with Role Admin
	 */
	@Test
	public void Should_addNewuser_When_UserWithRoleAdmin() throws Exception{    	
		verify(create(admin(), NEW_USER_CONTENT), isCreated());
	}
	

	
	
	/*
	 * Test 4. Delete User with registered User with Role Admin
	 */
	@Test
	public void Should_deleteUser_When_UserWithRoleAdmin() throws Exception{
		ResultActions result = readWithVariables(admin(), "search/findByEmail?email="+NEW_USER_EMAIL );
		
    	JSONObject json = new JSONObject(result.andReturn().getResponse().getContentAsString());    	    	
    	String varId = String.valueOf(json.getInt("id"));
    	
    	verify(remove(admin(), varId), isNoContent());
	}	
}
