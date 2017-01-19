package com.example.repositories;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.config.AuthenticationClient;
import com.example.config.AuthenticationUser;
import com.example.config.OAuthHelper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class MockMvcTest {
	    	
    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
    
	@Autowired
	protected AuthenticationClient authenticationClient;
	
	@Autowired
	protected AuthenticationUser authenticationUser;
    
	@Autowired
	protected OAuthHelper oauthHelper;
    
	protected static final ObjectMapper mapper = new ObjectMapper();
	
    protected MockMvc mvc;
    
    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain).build();
    	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }
}
