package com.example.controller;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Activity;
import com.example.service.ActivityService;

@Controller
public class ActivityFilterController {

	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(value="/activities/search/findByFilters", method= RequestMethod.POST)
	public @ResponseBody Resources<Activity> getActivitiesFilter(@RequestBody String jsonString){
		
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
		Resources<Activity> resources = new Resources<Activity>(activitiesFiltered);
		return resources;
		
	}
}
