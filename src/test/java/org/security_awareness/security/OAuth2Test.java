package org.security_awareness.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.security_awareness.utils.OAuthTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class OAuth2Test {
	
    public static final String CLIENT_ID = "security_awareness_app";
    public static final String CLIENT_SECRET = "secret";
    public static final String ADMIN_NAME = "david.alonso@eurecat.org";
    public static final String ADMIN_PASSWORD = "Awareness2017";

    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
     
    private MockMvc mvc;
    
    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain).build();
    }
	
    /*
	 * Test 1. Try to get an access token with valid credentials
	 */
    @Test
	public void Should_getValidTokens_When_ValidCredentials() throws Exception{
    	OAuthTokenResponse tokenResponse = getAccessToken(ADMIN_NAME, ADMIN_PASSWORD, CLIENT_ID, CLIENT_SECRET);
    	
    	assertNotNull(tokenResponse.accessToken);
    	assertNotNull(tokenResponse.refreshToken);
    	assertEquals(tokenResponse.accessToken.length(), 36);
    	assertEquals(tokenResponse.refreshToken.length(), 36);
    }
    

	/*
	 * Test 2. Try to get an access token with invalid credentials 
	 * (username and/or password wrong)
	 */
	@Test
	public void Should_getInvalidCredentialsMessage_When_InvalidCredentials() throws Exception{
		OAuthTokenResponse tokenResponse = getAccessToken(ADMIN_NAME, "wrongPassword", CLIENT_ID, CLIENT_SECRET);
    	
    	assertNull(tokenResponse.accessToken);
    	assertNull(tokenResponse.refreshToken);
    	assertNotNull(tokenResponse.error);
    	assertNotNull(tokenResponse.errorDescription);
    	assertEquals(tokenResponse.error, "invalid_grant");
    	assertEquals(tokenResponse.errorDescription, "Bad credentials");
	
	}
	
	/*
	 * Test 3. Try to get an access token with invalid credentials
	 * (wrong client_id)
	 */
	@Test
	public void Should_getInvalidCredentialsMessage_When_InvalidClientId() throws Exception{
		OAuthTokenResponse tokenResponse = getAccessToken(ADMIN_NAME, ADMIN_PASSWORD, "wrongClientId", CLIENT_SECRET);
    	
    	assertNull(tokenResponse.accessToken);
    	assertNull(tokenResponse.refreshToken);
    	assertNotNull(tokenResponse.error);
    	assertNotNull(tokenResponse.errorDescription);
    	assertEquals(tokenResponse.error, "invalid_client");
    	assertEquals(tokenResponse.errorDescription, "Bad client credentials");
	}
	
	/*
	 * Test 4. Try to get an access token with not all the credentials
	 * ( client id missing)
	 */
	@Test(expected=JsonMappingException.class)
	public void Should_getErrorMessage_When_MissingClientId() throws Exception{
		getAccessToken(ADMIN_NAME, ADMIN_PASSWORD, null, null);
    	
	}
	
	
	@Test
	public void Should_RefreshToken_When_ValidCredentialsAndToken() throws Exception {
		OAuthTokenResponse tokenResponse = getAccessToken(ADMIN_NAME, ADMIN_PASSWORD, CLIENT_ID, CLIENT_SECRET);
		
    	assertNotNull(tokenResponse.accessToken);
    	assertNotNull(tokenResponse.refreshToken);
    	assertEquals(tokenResponse.accessToken.length(), 36);
    	assertEquals(tokenResponse.refreshToken.length(), 36);
		
		OAuthTokenResponse refreshTokenResponse = getAccessTokenFromRefreshToken(tokenResponse.refreshToken, CLIENT_ID, CLIENT_SECRET);
		
    	assertNotNull(refreshTokenResponse.accessToken);
    	assertNotNull(refreshTokenResponse.refreshToken);
    	assertEquals(refreshTokenResponse.accessToken.length(), 36);
    	assertEquals(refreshTokenResponse.refreshToken.length(), 36);
    	assertNotEquals(tokenResponse.accessToken, refreshTokenResponse.accessToken);	
	}
    
	
	private OAuthTokenResponse getAccessTokenFromRefreshToken(
			String refreshToken,
			String clientId,
			String clientSecret) throws Exception {    
        MockHttpServletResponse response = mvc
                .perform(post("/oauth/token")
                        .param("grant_type", "refresh_token")
                		.param("client_id", clientId)
                		.param("client_secret", clientSecret)
                		.param("refresh_token", refreshToken)
                		)
                .andReturn().getResponse();

        ObjectMapper object = new ObjectMapper(); 
        object.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OAuthTokenResponse oauthTokenResponse =  object.readValue(
        		response.getContentAsByteArray(), 
        		OAuthTokenResponse.class);
        return oauthTokenResponse;
	}
    
    private OAuthTokenResponse getAccessToken(String username, String password, String clientId, String clientSecret) throws Exception {    
        MockHttpServletResponse response = mvc
                .perform(post("/oauth/token")
                        .param("username", username)
                        .param("password", password)
                        .param("grant_type", "password")
                		.param("client_id", clientId)
                		.param("client_secret", clientSecret))
                .andReturn().getResponse();

        ObjectMapper object = new ObjectMapper(); 
        object.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OAuthTokenResponse oauthTokenResponse =  object.readValue(
        		response.getContentAsByteArray(), 
        		OAuthTokenResponse.class);
        return oauthTokenResponse;
    }
    
}
    