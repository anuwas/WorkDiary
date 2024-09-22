package com.org.pack.wd.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiaryConfiguration {
	
	@Bean(name = "pastPresentFutureDateChecks")
    public PastPresentFutureDateChecks pastPresentFutureDateChecks() {
		
        return (x) -> checkDate(x);
    }
	
	private String checkDate(String sdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
			Date date1 = sdf.parse(sdate);
			Date date2 = new Date();
			if(date1.after(date2)){
                return "table-future-leave";
            }
			return "past-leave";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}
}
