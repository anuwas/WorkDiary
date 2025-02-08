/**
 *
 */
package com.org.pack.wd.db.exportimport;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author ambigo
 * 29-Jan-2025 1:20:35 am
 */
public class DBExportUtil {
	
	public static String getDateStr(Date date) {
		if(date==null) return "no-date";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}
	
	public static String getTimestampStr(Timestamp date) {
		if(date==null) return "no-date";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}

}
