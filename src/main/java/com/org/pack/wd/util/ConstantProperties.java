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
	
}
