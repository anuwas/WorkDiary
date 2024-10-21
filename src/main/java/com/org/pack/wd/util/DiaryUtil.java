package com.org.pack.wd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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
	
	public static java.sql.Date getFirstDateOfYear() {
		LocalDate instance = LocalDate.now().withYear(getCurrentYear());
		LocalDate dateStart = instance.with(TemporalAdjusters.firstDayOfYear());
		java.sql.Date date = java.sql.Date.valueOf(dateStart);
		return date;
	}
	
	public static java.sql.Date getLastDateOfYear() {
		LocalDate instance = LocalDate.now().withYear(getCurrentYear());
		LocalDate dateEnd = instance.with(TemporalAdjusters.lastDayOfYear());
		java.sql.Date date = java.sql.Date.valueOf(dateEnd);
		return date;
	}
	
	
	

}
