package com.example.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import javax.persistence.Entity;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import com.example.model.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserRepositoryTest extends AbstractRepositoryTest{
	
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
    	RequestPostProcessor bearerToken = oauthHelper.bearerToken(authenticationClient.client(), authenticationUser.user());
    	MockHttpServletResponse response =  mvc.perform(
    			get("/" + getResourceName())
    				.with(bearerToken)
    			)		
    			.andExpect(status().isOk())
    			.andReturn().getResponse();
    	
    	
    	List<? extends Entity> users = getEntitiesList(response.getContentAsString());    	
    	assertNotNull(users);
    }
	
    
	/*
	 * Test 2. Get details of other user from registered User with Role User
	 */
    @Test
    public void Should_getUserDetails_When_UserDetailsFromANotherUserWithRoleUser() throws Exception{
    	RequestPostProcessor bearerToken = oauthHelper.bearerToken(authenticationClient.client(), authenticationUser.user());
    	mvc.perform(
    			get("/" + getResourceName() + "/1")
    				.with(bearerToken)
    			)		
    			.andExpect(status().isOk());
    }
	
	/*
	 * Test 3. Post/Put User with registered User with Role User
	 */
	@Test
	public void Should_getErrorMessage_When_PostUserWithRoleUser() throws Exception{    	
    	String content = "{\n\t\"firstName\": \"Juan\", \n\t\"lastName\":"
    			+ " \"Palomo\",\n\t\"username\": \"palomo_50\",\n\t\"password\":"
    			+ " \"123456\",\n\t\"authority\": "
    			+ "\"http://localhost/authorities/2\"\n}";
    	
		RequestPostProcessor bearerToken = oauthHelper.bearerToken(authenticationClient.client(), authenticationUser.user());
    	mvc.perform(
    			post("/" + getResourceName())
    				.with(bearerToken)
    				.content(content)
    			)		
    			.andExpect(status().isForbidden());
	}
    
	/*
	 * Test 4. Delete User with registered User with Role User
	 */
	@Test
	public void Should_getErrorMessage_When_DeleteUserWithRoleUser() throws Exception{ 
		RequestPostProcessor bearerToken = oauthHelper.bearerToken(authenticationClient.client(), authenticationUser.user());
    	mvc.perform(
    			delete("/" + getResourceName() + "/2")
    				.with(bearerToken)
    			)		
    			.andExpect(status().isForbidden());
	}
	
	
	
	/**
	 * TESTS WITH ROLE: ADMIN
	 */
	
	/*
	 * Test 1. Get details from registered User with Role Admin
	 */	
	@Test
	public void Should_getAllUserDetails_When_UserWithRoleAdmin() throws Exception{
		RequestPostProcessor bearerToken = oauthHelper.bearerToken(authenticationClient.client(), authenticationUser.admin());
	    MockHttpServletResponse response = mvc.perform(
	    			get("/"+ getResourceName())
	    			.with(bearerToken))
	    		.andReturn().getResponse();			 
		 
    	List<? extends Entity> users = getEntitiesList(response.getContentAsString());
    	
    	assertEquals(response.getStatus(), 200);
    	assertNotNull(users);
    	assertTrue(users.size() > 1);
	}
	
	/*
	 * Test 2. Get details of other user from registered User with Role Admin
	 */
	@Test
	public void Should_getUserDetailsOfAnotherUser_When_UserWithRoleAdmin() throws Exception{
		RequestPostProcessor bearerToken = oauthHelper.bearerToken(authenticationClient.client(), authenticationUser.admin());
	    MockHttpServletResponse response = mvc.perform(
	    			get("/"+ getResourceName()+"/2")
	    			.with(bearerToken))
	    		.andReturn().getResponse();	
		
    	User user = mapper.readValue(response.getContentAsByteArray(), User.class);
    	
    	assertNotNull(user);
    	assertEquals(user.getUsername(), "sergi_50");
	}
	
	/*
	 * Test 3. Post/Put User with registered User with Role Admin
	 */
	@Test
	public void Should_addNewuser_When_UserWithRoleAdmin() throws Exception{    	
    	String content = "{\n\t\"firstName\": \"Juan\", \n\t\"lastName\":"
    			+ " \"Palomo\",\n\t\"username\": \"palomo_50\",\n\t\"password\":"
    			+ " \"123456\",\n\t\"authority\": "
    			+ "\"http://localhost/authorities/2\"\n}";
    	
		RequestPostProcessor bearerToken = oauthHelper.bearerToken(authenticationClient.client(), authenticationUser.admin());
	    mvc.perform(
    		post("/"+ getResourceName())
    			.with(bearerToken)
    			.content(content)
    		)
			.andExpect(status().isCreated())
			.andDo(print())
			.andReturn().getResponse();	
    		
	}
	
	/*
	 * Test 4. Delete User with registered User with Role Admin
	 */
	@Test
	public void Should_deleteUser_When_UserWithRoleAdmin() throws Exception{
		RequestPostProcessor bearerToken = oauthHelper.bearerToken(authenticationClient.client(), authenticationUser.admin());
	    MockHttpServletResponse response = mvc.perform(
	    		get("/"+ getResourceName() + "/search/findByUsername?username="+"palomo_50")
	    		.with(bearerToken)
	    		)
    			.andReturn().getResponse();	
    	
    	JSONObject json = new JSONObject(response.getContentAsString());    	    	
    	String locationUser = json.getJSONObject("_links").
    			getJSONObject("self")
    			.getString("href");
    	
    	mvc.perform(
    			delete(locationUser)
    				.with(bearerToken)
    			)		
    			.andExpect(status().isNoContent());
	}	
}
