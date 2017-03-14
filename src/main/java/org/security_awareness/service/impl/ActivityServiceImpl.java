package org.security_awareness.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.security_awareness.model.Activity;
import org.security_awareness.model.ActivityBlock;
import org.security_awareness.repository.ActivityBlockRepository;
import org.security_awareness.repository.ActivityRepository;
import org.security_awareness.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private ActivityBlockRepository activityBlockRepository; 
	
	@Override
	public Set<Activity> findAll(){
		return activityRepository.findAll();
	}

	@Override
	public Set<Activity> findByMonthAndyear(int month, int year) {
		return activityRepository.findAllByMonthAndYear(month, year);
	}
	
	@Override
	public Set<Activity> findAllByMonthAndYearAndUserZones(int month, int year, String email){
		return activityRepository.findAllByMonthAndYearAndUserZones(month, year, email);
	}
	
	@Override
	public Set<Activity> findAllByMonthAndYearAndZone(int month, int year, long zone){
		return activityRepository.findAllByMonthAndYearAndZone(month, year, zone);
	}
	
	@Override
	public Activity save(Activity activity){	
		Date dateStart = activity.getDateTimeStart();
		Date dateEnd = activity.getDateTimeEnd();
		
		List<ActivityBlock> activityBlocks = new ArrayList<>();
		
		for( int numRepetitions = activity.getNumRepeats(); numRepetitions >= 0 ; numRepetitions--){

			ActivityBlock activityBlock = new ActivityBlock();
			activityBlock.setStartDate(dateStart);
			activityBlock.setEndDate(dateEnd);
			activityBlockRepository.save(activityBlock);			
			
			activityBlocks.add(activityBlock);
			
			dateStart = sumOneDay(dateStart);
			dateEnd = sumOneDay(dateEnd);
			
		}
		
		activity.setActivitiesBlock(activityBlocks);
		
		return activityRepository.save(activity);
		
	}
	
	@Override
	public Activity findOne(long id){
		return activityRepository.findOne(id);
	}
	
	private Date sumOneDay(Date date){
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}

}
