package org.security_awareness.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthTokenResponse {
	
	@JsonProperty("access_token")
	public String accessToken;
	
	@JsonProperty("refresh_token")
	public String refreshToken;
	
	@JsonProperty("error")
	public String error;
	
	@JsonProperty("error_description")
	public String errorDescription;

}