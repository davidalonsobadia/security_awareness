package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.example.model.Role;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationEntryPoint customAuthenticationEntryPoint;
	
	@Autowired
	private LogoutSuccessHandler customLogoutSuccessHandler;
    
    @Autowired
    private AccessDecisionManager accessDecisionManager;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {

		http
            .exceptionHandling()
            	.authenticationEntryPoint(customAuthenticationEntryPoint)
            .and()
            	.logout()
            		.logoutUrl("/oauth/logout")
            		.logoutSuccessHandler(customLogoutSuccessHandler)
             .and()
	            .authorizeRequests()
	            	.accessDecisionManager(accessDecisionManager)
	            	.antMatchers("/**")
	            		.authenticated()
	            	.antMatchers("/register")
	            		.hasRole(Role.ANONYMOUS.name())
	            	.antMatchers("/users/**")
	            		.hasRole(Role.USER.name());
    }     
}

