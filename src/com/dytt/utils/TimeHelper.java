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

	/**
	 * 把 yyyyMMdd 格式化为yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String format(String date) {
		if(date != null && date.length() == 8){
			return new StringBuilder(date.substring(0,4)).append("-").append(date.substring(4,6)).append("-").append(date.substring(6,8)).toString();
		}else {
			return date;
		}
	}
}
