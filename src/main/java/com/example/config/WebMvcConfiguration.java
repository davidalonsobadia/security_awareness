package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.example.model.Activity;
import com.example.model.Notification;
import com.example.model.Resource;
import com.example.model.User;
import com.example.model.Zone;

@Configuration
public class WebMvcConfiguration extends RepositoryRestMvcConfiguration{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config){
		config.exposeIdsFor(User.class, Zone.class, Resource.class, 
				Activity.class, Notification.class);
	}
	
//	@Bean
//	public VerifyAccessInterceptor verifyAccessInterceptor() {
//		return new VerifyAccessInterceptor();
//	}
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {		
//		super.addInterceptors(registry);
//		registry.addInterceptor(verifyAccessInterceptor());
//	}
	
//	@Override
//	public JpaHelper jpaHelper() {
//		JpaHelper helper = new JpaHelper(); 
//		helper.getInterceptors().add(new VerifyAccessInterceptor());
//		
//		return helper;
//	}
	
//	@Bean
//	public MappedInterceptor myMappedInterceptor() {
//	    return new MappedInterceptor(new String[]{"/**"}, new VerifyAccessInterceptor());
//	}
	
}
