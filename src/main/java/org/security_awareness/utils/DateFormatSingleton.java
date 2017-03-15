package org.security_awareness.utils;

import java.text.SimpleDateFormat;

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
