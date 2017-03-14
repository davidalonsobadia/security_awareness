package org.security_awareness.utils;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

public class DateFormatSingleton {

	private static String dateFormat = "dd/MM/yyyy HH:mm";
		
	private static SimpleDateFormat simpleDateFormatSingleton;
	
	private DateFormatSingleton(){}
	
	public static SimpleDateFormat getDateFormat(){
		if(simpleDateFormatSingleton == null){
			simpleDateFormatSingleton = new SimpleDateFormat(dateFormat);
		}
		return simpleDateFormatSingleton;
	}
}
