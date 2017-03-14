package org.security_awareness.service;

import java.util.Set;

import org.security_awareness.model.Activity;

public interface ActivityService {
	
	Iterable<Activity> findAll();
	
	Activity findOne(long id);
	
	Set<Activity> findByMonthAndyear(int month, int year);
	
	Activity save(Activity activity);

	Set<Activity> findAllByMonthAndYearAndUserZones(int month, int year, String email);

	Set<Activity> findAllByMonthAndYearAndZone(int month, int year, long zone);

}
