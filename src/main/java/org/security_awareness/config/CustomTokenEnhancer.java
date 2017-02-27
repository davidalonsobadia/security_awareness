package org.security_awareness.config;

import java.util.HashMap;
import java.util.Map;

import org.security_awareness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {
	
	@Autowired
	private UserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        
        long userId = userService.findByEmail(user.getUsername()).getId();
        boolean isRegistered = userService.findByEmail(user.getUsername()).isRegistered();
        
        final Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("userId", userId);
        additionalInfo.put("isRegistered", isRegistered);

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
	}

}
