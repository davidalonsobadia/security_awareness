package org.security_awareness.config;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import({springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class})
public class SwaggerConfig {
	
    @SuppressWarnings("unchecked")
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
        	.apiInfo(apiInfo())
        	.select()                                  
	          .apis(Predicates.and(
	        		  CRUDRequestHandlers(),
	        		  groupNameFilter())
	        	)
	          .paths(Predicates.and(
	        		  Predicates.not(PathSelectors.ant("/passwords/**")),
	        		  Predicates.not(PathSelectors.ant("/activityBlocks/**")),
	        		  Predicates.not(PathSelectors.ant("/authorities/**")),
	        		  Predicates.not(PathSelectors.ant("/oauth/authorize/**")),
	        		  Predicates.not(PathSelectors.ant("/profile/**"))	        		  )
	           )
	          .build();     
    }
    
    
    @Bean
    public UiConfiguration uiConfiguration(){
    	return new UiConfiguration("/swagger-ui.html", 
    			UiConfiguration.Constants.NO_SUBMIT_METHODS);
    }
    
    
    private Predicate<RequestHandler> CRUDRequestHandlers() {
        return new Predicate<RequestHandler>() {
              @Override
              public boolean apply(RequestHandler input) {
                  Set<RequestMethod> methods = input.supportedMethods();
                  return methods.contains(RequestMethod.GET)  
                		  || methods.contains(RequestMethod.POST)
                		  || methods.contains(RequestMethod.PUT)
                		  || methods.contains(RequestMethod.PATCH)
                		  || methods.contains(RequestMethod.DELETE);   
              }
        }; 
     }
    
    private Predicate<RequestHandler> groupNameFilter(){
    	return new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                return !input.groupName().equals("repository-controller");  
            }
    	};
    }
    
    
    private ApiInfo apiInfo() {

    	Contact contact = new Contact(
    			"Eurecat IT Security", // name
    			"www.eurecat.org", // url
    			"david.alonso@eurecat.org" // email
    			);     	

        ApiInfo apiInfo = new ApiInfo(
          "Security Awareness API",
          "Documentation for Security Awareness Project API",
          "v1",
          "",
          contact,
          "",
          "");
        
        
        return apiInfo;
    }
           
    
}