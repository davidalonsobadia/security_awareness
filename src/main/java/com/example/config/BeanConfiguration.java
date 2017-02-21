package com.example.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import com.example.model.Role;

@Configuration
@PropertySource("classpath:application.properties")
public class BeanConfiguration {
	
	@Autowired
    private Environment env;

    @Bean
    public RoleHierarchy roleHierarchy(){
    	String hierarchy = Role.SUPERADMIN.getRoleName() + " > " + Role.ADMIN.getRoleName() + " "
    			+ Role.ADMIN.getRoleName() + " > "  + Role.USER.getRoleName() + " "
    			+ Role.USER.getRoleName() + " > " + Role.ANONYMOUS.getRoleName();
    	RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    	roleHierarchy.setHierarchy(hierarchy);
    	return roleHierarchy;
    }
    
    @Bean
    AccessDecisionManager accessDecisionManager(){
  	
	  	List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<>();
	  	WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
	  	webExpressionVoter.setExpressionHandler(expressionHandler());
	  	decisionVoters.add(webExpressionVoter);
	  	
	  	AffirmativeBased accessDecisionManager = new AffirmativeBased(decisionVoters);  	
	  	return accessDecisionManager;
  }
  
	 private SecurityExpressionHandler<FilterInvocation> expressionHandler() {
		DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new OAuth2WebSecurityExpressionHandler();
		defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
		return defaultWebSecurityExpressionHandler;
	 }
	 
	   @Bean
	   public JavaMailSenderImpl javaMailSenderImpl() {
			final JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
			
			try {
				mailSenderImpl.setHost(env.getRequiredProperty("smtp.host"));
				mailSenderImpl.setPort(env.getRequiredProperty("smtp.port", Integer.class));
				mailSenderImpl.setProtocol(env.getRequiredProperty("smtp.protocol"));
				mailSenderImpl.setUsername(env.getRequiredProperty("smtp.username"));
				mailSenderImpl.setPassword(env.getRequiredProperty("smtp.password"));
			} catch (IllegalStateException ise) {
				//logger.error("Could not resolve email.properties.  See email.properties.sample");
			    throw ise;
			}
			final Properties javaMailProps = new Properties();
			javaMailProps.put("mail.smtp.auth", env.getRequiredProperty("mail.smtp.auth"));
			javaMailProps.put("mail.smtp.starttls.enable", env.getRequiredProperty("mail.smtp.starttls.enable"));
			// To see what is going on behind the scene
			javaMailProps.put("mail.debug", env.getProperty("mail.debug"));          
			mailSenderImpl.setJavaMailProperties(javaMailProps);
			return mailSenderImpl;
	   }
}
