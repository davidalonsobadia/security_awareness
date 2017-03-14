package org.security_awareness.controller;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.security_awareness.model.Activity;
import org.security_awareness.model.User;
import org.security_awareness.model.Zone;
import org.security_awareness.service.ActivityService;
import org.security_awareness.service.UserService;
import org.security_awareness.service.ZoneService;
import org.security_awareness.utils.ActivityContext;
import org.security_awareness.utils.DateFormatSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@RepositoryRestController
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private ZoneService zoneService;
	
	@Autowired
	private UserService userService;
		
	@RequestMapping(value="/activities", method =  RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Resource<Activity>> postActivity(
			@RequestBody ActivityContext activityContext) throws Exception{
		
		Activity activity = new Activity();
		
		activity.setName(activityContext.getName());
		activity.setDescription(activityContext.getDescription());
		activity.setNumRepeats(activityContext.getNumRepeats());
		activity.setPlace(activityContext.getPlace());
		
		activity.setType(activityContext.getType());
		
		int zoneId = objectParser(activityContext.getZone(), "Zone");
		Zone zone = zoneService.findById(zoneId);
		activity.setZone(zone);
		
		int managerId = objectParser(activityContext.getManager(), "Manager");
		User manager = userService.findById(managerId);
		activity.setManager(manager);
		

		
		String dateStartString = activityContext.getDateStart();
		Date dateStart = DateFormatSingleton.getDateFormat().parse(dateStartString);
		activity.setDateTimeStart(dateStart);
		
		String dateEndString = activityContext.getDateEnd();
		Date dateEnd = DateFormatSingleton.getDateFormat().parse(dateEndString);
		activity.setDateTimeEnd(dateEnd);
		
		Activity activityStored = activityService.save(activity);
		
	    BasicLinkBuilder builder = BasicLinkBuilder.linkToCurrentMapping()
	                                               .slash("activities")
	                                               .slash(activityStored.getId());
	    Resource<Activity> resource = new Resource<Activity>(activityStored,
                builder.withSelfRel());
	    
		return new ResponseEntity<Resource<Activity>>(resource, HttpStatus.CREATED);
	}
	
	
	// Non - API
	
	private int objectParser(Object object, String type) throws Exception{
		
		int objectId = 0;
		if(object instanceof Integer){
			objectId = (int) object;
		} else if(object instanceof String){
			final Pattern lastIntPattern = Pattern.compile("[^0-9]+([0-9]+)$");
			Matcher matcher = lastIntPattern.matcher(object.toString());
			if(matcher.find()){
				objectId = Integer.parseInt(matcher.group(1));
			} else {
				throw new Exception(type + " not Found");
			}
		} else {
			throw new Exception(type + " not Found");
		}
		return objectId;
		
	}
	
}
