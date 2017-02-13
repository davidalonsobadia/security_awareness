package com.example.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Activity;
import com.example.model.ActivityBlock;
import com.example.repository.ActivityBlockRepository;
import com.example.repository.ActivityRepository;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private ActivityBlockRepository activityBlockRepository; 
	
	@Override
	public Iterable<Activity> findAll(){
		return activityRepository.findAll();
	}

	@Override
	public Set<Activity> findByMonthAndyear(int month, int year) {
		return activityRepository.findAllByMonthAndYear(month, year);
	}
	
	@Override
	public void save(Activity activity){	
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
		
		activityRepository.save(activity);
		
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
