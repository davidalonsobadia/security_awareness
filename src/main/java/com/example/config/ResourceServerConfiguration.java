package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import com.example.model.Role;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    
    @Autowired
    private AccessDecisionManager accessDecisionManager;
    
//    private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
//        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
//        defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy);
//        return defaultWebSecurityExpressionHandler;
//    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {

		http
//            .exceptionHandling()
//            	.authenticationEntryPoint(customAuthenticationEntryPoint)
//            .and()
//            	.logout()
//            		.logoutUrl("/oauth/logout")
//            		.logoutSuccessHandler(customLogoutSuccessHandler)
//            .and()
//            	.csrf()
//            		.requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
//            		.disable()
//            		.headers()
//            			.frameOptions().disable()
//            .and()
	            .authorizeRequests()
	            	.accessDecisionManager(accessDecisionManager)
		            //.expressionHandler(webExpressionHandler())
//					.antMatchers("/**")
//						.authenticated()
		            .antMatchers("/users")
		            	.hasRole(Role.USER.name())
		            .antMatchers("/users/**")
		            	.hasRole(Role.ADMIN.name())
		            .antMatchers("/authorities/**")
		            	.hasRole(Role.ADMIN.name());
    }     
}