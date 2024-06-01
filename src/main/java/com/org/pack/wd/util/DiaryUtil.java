package com.org.pack.wd.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;


public class DiaryUtil {
	
	public static int getCurrentYear() {
		int year = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getYear();
		return year;
	}
	
	public static java.sql.Date getCurrentDate() {
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		return date;
	}
	
	
	

}
