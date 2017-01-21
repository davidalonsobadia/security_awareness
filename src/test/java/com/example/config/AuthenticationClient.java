package com.example.config;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationClient {
	
    public static final String CLIENT_ID = "security_awareness_app";
	
    @Autowired
    private ClientDetailsService clientDetailsService;
    
    public OAuth2Request client() {
        // Look up authorities, resourceIds and scopes based on clientId    	
        ClientDetails client = clientDetailsService.loadClientByClientId(CLIENT_ID);
        Collection<GrantedAuthority> authorities = client.getAuthorities();
        Set<String> resourceIds = client.getResourceIds();
        Set<String> scopes = client.getScope();

        // Default values for other parameters
        Map<String, String> requestParameters = Collections.emptyMap();
        boolean approved = true;
        String redirectUrl = null;
        Set<String> responseTypes = Collections.emptySet();
        Map<String, Serializable> extensionProperties = Collections.emptyMap();

        // Create request
        OAuth2Request oAuth2Request = new OAuth2Request(requestParameters, CLIENT_ID, authorities, approved, scopes,
                resourceIds, redirectUrl, responseTypes, extensionProperties);
        
        return oAuth2Request;
    }
}
