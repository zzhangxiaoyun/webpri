package com.dytt.utils;
import java.util.Properties;
public class ConfigProperties {
	private static Properties properties;
	static{
		properties = new Properties();
		try {
			properties.load(ConfigProperties.class.getResourceAsStream("/config.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static Object get(String key){
		return properties.get(key);
	}
	
	public static String getProperty(String key){
		return properties.getProperty(key);
	}
	public static Properties getProperties(){
		return properties;
	}

}
