package com.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

@Component
public class OAuthHelper {
	
    @Autowired
    AuthorizationServerTokenServices tokenservice;
	
    // For use with MockMvc
    public RequestPostProcessor bearerToken(final OAuth2Request clientRequest, final User user) {
    	RequestPostProcessor requestPostProcessor = new RequestPostProcessor() {
			@Override
			public MockHttpServletRequest postProcessRequest(MockHttpServletRequest mockRequest) {
				OAuth2AccessToken token = createAccessToken(clientRequest, user);
	            mockRequest.addHeader("Authorization", "Bearer " + token.getValue());
	            return mockRequest;
			}
		};
		return requestPostProcessor;
    }


    OAuth2AccessToken createAccessToken(final OAuth2Request clientRequest, final User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        OAuth2Authentication auth = new OAuth2Authentication(clientRequest, authenticationToken);
        
        return tokenservice.createAccessToken(auth);
    }    
}