package org.security_awareness.config;

import org.security_awareness.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

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
	            	.antMatchers("/authorities/**")
            			.hasRole(Role.ADMIN.name())
	            	.antMatchers("/users/**")
	            		.hasRole(Role.USER.name())
	            	.antMatchers("/activities/**")
	            		.hasRole(Role.USER.name())
	            	.antMatchers("/notifications/**")
	            		.hasRole(Role.USER.name())
	            	.antMatchers("/resources/**")
	            		.hasRole(Role.USER.name())
	            	.antMatchers("/zones/**")
	            		.hasRole(Role.USER.name())
		            .antMatchers("/register")
	            		.hasRole(Role.ANONYMOUS.name())
	            	.antMatchers("/isRegistered")
	            		.hasRole(Role.ANONYMOUS.name())
			        .antMatchers("/**")
	            		.hasRole(Role.USER.name())
		            .anyRequest()
	            		.authenticated();
    }
    
}

