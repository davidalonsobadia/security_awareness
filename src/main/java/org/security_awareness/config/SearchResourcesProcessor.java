package org.security_awareness.config;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.security_awareness.controller.ActivityFilterController;
import org.security_awareness.controller.UserFilterController;
import org.security_awareness.model.Activity;
import org.security_awareness.model.User;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

@Component
public class SearchResourcesProcessor implements ResourceProcessor<RepositorySearchesResource> {

    @Override
    public RepositorySearchesResource process(RepositorySearchesResource repositorySearchesResource) {
    	   	
    	if (repositorySearchesResource.getDomainType().getCanonicalName().equals(User.class.getCanonicalName()) ) {
    		repositorySearchesResource.add(linkTo(methodOn(UserFilterController.class)
    				.getUsersFiltered("json"))
    				.withRel("findByFilters"));
    	} else if (repositorySearchesResource.getDomainType().getCanonicalName().equals(Activity.class.getCanonicalName()) ) {
    		repositorySearchesResource.add(linkTo(methodOn(ActivityFilterController.class)
    				.getActivitiesFilter("json"))
    				.withRel("findByFilters"));
    	}
        return repositorySearchesResource;
    }
}