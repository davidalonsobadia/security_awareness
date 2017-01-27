package com.example.controller;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Activity;
import com.example.service.ActivityService;

@RestController
public class ActivityController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ActivityService activityService;
//	
	//todo: recheck this
//	@RequestMapping(value="/activities/search/findAllByMonthAndYear", method=RequestMethod.GET)
//	public ResponseEntity<Void> findByMonthAndYear(
//			@RequestParam("month") int month,
//			@RequestParam("year") int year){
//		
//		Iterable<Activity> activities = activityService.findAll();
//		
//		for( Iterator<Activity> it = activities.iterator(); it.hasNext(); ){
//			Activity activity = it.next();
//			activity.getDateTimeStart();
//			
//		}
//		
//		logger.error("ERRRRRORRRRR NOOOOOOOOOOOOOOOOOOO I AM HEREEEEE!");
//		
//		return null;
//	}

}
