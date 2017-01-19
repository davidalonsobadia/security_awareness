package com.example.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class BeanConfiguration {

    @Bean
    public RoleHierarchy roleHierarchy(){
    	String hierarchy = Role.GOD.getRoleName() + " > " + Role.SUPERADMIN.getRoleName() + " "
    			+ Role.SUPERADMIN.getRoleName() + " > "  + Role.ADMIN.getRoleName() + " "
    			+ Role.ADMIN.getRoleName() + " > " + Role.USER.getRoleName();
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
}
