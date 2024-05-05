package com.org.pack.wd.util;

import java.util.HashMap;
import java.util.Map;

public class ConstantProperties {

	public static  Map<Integer, String> TASK_PRIORITY_MAP;
	static {
		TASK_PRIORITY_MAP = new HashMap<>();
		TASK_PRIORITY_MAP.put(1, "High");
		TASK_PRIORITY_MAP.put(2, "Medium");
		TASK_PRIORITY_MAP.put(3, "Low");
    }
	
	public static  Map<String,Integer> MONTH_NAME_INDEX_MAP;
	static {
		MONTH_NAME_INDEX_MAP = new HashMap<>();
		MONTH_NAME_INDEX_MAP.put("January", 1);
		MONTH_NAME_INDEX_MAP.put("February", 2);
		MONTH_NAME_INDEX_MAP.put("March", 3);
		MONTH_NAME_INDEX_MAP.put("April", 4);
		MONTH_NAME_INDEX_MAP.put("May", 5);
		MONTH_NAME_INDEX_MAP.put("Jun", 6);
		MONTH_NAME_INDEX_MAP.put("July", 7);
		MONTH_NAME_INDEX_MAP.put("August", 8);
		MONTH_NAME_INDEX_MAP.put("September", 9);
		MONTH_NAME_INDEX_MAP.put("October", 10);
		MONTH_NAME_INDEX_MAP.put("November", 11);
		MONTH_NAME_INDEX_MAP.put("December", 12);
    }
	
}
