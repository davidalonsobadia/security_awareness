package org.security_awareness.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.security_awareness.model.Activity;
import org.security_awareness.model.projections.ActivitiesExpanded;
import org.security_awareness.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.EmbeddedWrappers;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ActivityFilterController {

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ProjectionFactory projectionFactory;
	
	@RequestMapping(value="/activities/search/findByFilters", method= RequestMethod.POST)
	public @ResponseBody Resources<EmbeddedWrapper> getActivitiesFilter(@RequestBody String jsonString){
		
		JSONObject json = new JSONObject(jsonString);
		
		boolean myZones = json.getBoolean("myZones");
		long concreteZone = json.getLong("concreteZone");
		int month = json.getInt("month");
		int year = json.getInt("year");
		
		String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Set<Activity> activitiesFiltered = new HashSet<>();
		
		// My zones filter activated
		if (myZones == true) {
			activitiesFiltered = activityService.findAllByMonthAndYearAndUserZones(month, year, userEmail);
		//	Concrete Zone filter activated (and zone selected)
		} else if ( concreteZone != 0 ) {
			activitiesFiltered = activityService.findAllByMonthAndYearAndZone(month, year, concreteZone);
		// No filters selected	
		} else {
			activitiesFiltered = activityService.findByMonthAndyear(month, year);
		}
		
		
		List<ActivitiesExpanded> activitiesExpanded = new ArrayList<>();
		for(Activity activity : activitiesFiltered){
			activitiesExpanded.add(projectionFactory.createProjection(ActivitiesExpanded.class, activity));
		}
		
		EmbeddedWrappers wrappers = new EmbeddedWrappers(false);
		EmbeddedWrapper wrapper = (activitiesFiltered.size() == 0) ? wrappers.emptyCollectionOf(Activity.class) : wrappers.wrap(activitiesExpanded);

		Resources<EmbeddedWrapper> resources = new Resources<>(Arrays.asList(wrapper));
		resources.add(linkTo(methodOn(ActivityFilterController.class).getActivitiesFilter("json")).withSelfRel());
		
		return resources;
		
	}
}
