package org.security_awareness.others;

import static org.security_awareness.config.ResultMatchersImpl.isOk;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.security_awareness.config.AbstractMvcTest;
import org.security_awareness.model.Activity;
import org.security_awareness.utils.DateFormatSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.ResultActions;


public class checkTimeOfActivities extends AbstractMvcTest{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private SimpleDateFormat simpleFormat = DateFormatSingleton.getDateFormat();
	
	private static final String RESOURCE_NAME = "activities";
		
	@Override
	protected String getResourceName() {
		return RESOURCE_NAME;
	}

	@Test
	public void Should_Ok_When_RightTime() throws Exception{
		
		ResultActions result = readWithParams(user());

		List<Activity> activities = getEntitiesList(
				result.andReturn().getResponse().getContentAsString(),
				Activity.class);
		
		logger.info("Function to check manually (if necessary) activities Date and Time");
		
		for(Activity activity : activities){
			
			logger.info("----------------------------------------------------------------------");
			logger.info(activity.getName());
			logger.info("Start Date Time: " + simpleFormat.format(activity.getActivitiesBlock().get(0).getStartDate()) );
			logger.info("End Date Time: " + simpleFormat.format(activity.getActivitiesBlock().get(0).getEndDate()) );
		}
		verify(result, isOk());
	}
}
