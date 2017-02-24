package com.example.service;

import java.util.Set;

import com.example.model.Activity;

public interface ActivityService {
	
	Iterable<Activity> findAll();
	
	Activity findOne(long id);
	
	Set<Activity> findByMonthAndyear(int month, int year);
	
	void save(Activity activity);

	Set<Activity> findAllByMonthAndYearAndUserZones(int month, int year, String email);

	Set<Activity> findAllByMonthAndYearAndZone(int month, int year, long zone);

}
