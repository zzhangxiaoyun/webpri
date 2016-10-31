package com.dytt;
public class Logger {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);
	/**
	 * 输出日志
	 * @param tagObj
	 * @param msg
	 */
	public static void info(Object tagObj,String msg){
		info(tagObj.getClass(),msg);
	}
	/**
	 * 输出日志
	 * @param clazz
	 * @param msg
	 */
	public static void info(Class<?> clazz,String msg){
		info(clazz.getName(),msg);
	}
	/**
	 * 输出日志
	 * @param tag
	 * @param msg
	 */
	public static void info(String tag,String msg){
		logger.info(tag+" --> "+msg);
	}
}
