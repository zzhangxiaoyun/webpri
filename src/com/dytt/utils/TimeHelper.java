package com.dytt.utils;

import java.text.SimpleDateFormat;

public class TimeHelper {

	private static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	/**
	 * 格式化时间为 yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String format(long date) {
		return ((SimpleDateFormat) yyyyMMddHHmmss.clone()).format(date);
	}
}
