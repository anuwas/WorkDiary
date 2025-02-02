/**
 *
 */
package com.org.pack.db.exportimport;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @author ambigo
 * 29-Jan-2025 1:20:35 am
 */
public class Util {
	
	public static String getDateStr(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}

}
