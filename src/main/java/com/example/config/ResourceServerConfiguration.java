package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.model.Role;
import com.example.security.CustomAuthenticationEntryPoint;
import com.example.security.CustomLogoutSuccessHandler;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;
    


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
            	.csrf()
            		.requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
            		.disable()
            		.headers()
            			.frameOptions().disable()
            .and()
            
	            .authorizeRequests()
		            //.accessDecisionManager(accessDecisionManager())
		            .expressionHandler(expressionHandler())
		            //.antMatchers("/**").permitAll()
		            .antMatchers("/users/**")
		            	.authenticated()
		            .antMatchers("/authorities")
		            	.hasRole(Role.ADMIN.name());
		        	//TODO: hacer las restricciones necesarias
        	
    }

    @Bean 
    DefaultWebSecurityExpressionHandler expressionHandler(){
    	DefaultWebSecurityExpressionHandler oauth2WebSecurityExpressionHandler = new OAuth2WebSecurityExpressionHandler();
    	oauth2WebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
    	return oauth2WebSecurityExpressionHandler;
    }
    
    @Bean
    RoleHierarchy roleHierarchy(){
    	String hierarchy = Role.GOD.getRoleName() + " > " 
    			+ Role.SUPERADMIN.getRoleName() + " > " 
    			+ Role.ADMIN.getRoleName() + " > " 
    			+ Role.USER.getRoleName();
    	RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    	roleHierarchy.setHierarchy(hierarchy);
    	return roleHierarchy;
    }
}