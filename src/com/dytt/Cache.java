package com.dytt;

import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;

public class Cache {
//	private static final boolean stopUseRedis = true;
//	private static JedisPool pool = new JedisPool(new JedisPoolConfig(),"127.0.0.1");
	public static String getCache(String key) {
//		if(stopUseRedis)return null;
//
//		Jedis jedis = null;
//		try {
//
//			jedis = getJedis();
//			return jedis.get(key);
//		} catch (Exception e) {
//			Logger.info(Cache.class, "jedis 不能用了！");
//		} finally {
//			// 这里很重要，一旦拿到的jedis实例使用完毕，必须要返还给池中
//			if(jedis!=null){
//				pool.returnResource(jedis);
//			}
//		}
		return null;
	}

	public static void setCache(String key, String value) {
//		if(stopUseRedis)return;
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			jedis.set(key, value);
//		}catch (Exception e) {
//			Logger.info(Cache.class, "jedis 不能用了！");
//		} finally {
//			// 这里很重要，一旦拿到的jedis实例使用完毕，必须要返还给池中
//			if(jedis!=null){
//				pool.returnResource(jedis);
//			}
//		}
	}
	
//	private static synchronized Jedis getJedis()throws Exception{
//		return pool.getResource();
//	}

	// public static String getCache(String key){
	// try{
	// return getJedis().get(key);
	// }catch(Exception e){
	// Logger.info(Cache.class, "jedis 不能用了！");
	// }
	// return null;
	// }
	//	
	// public static void setCache(String key,String value){
	// try{
	// getJedis().set(key, value);
	// }catch(Exception e){
	// Logger.info(Cache.class, "jedis 不能用了！");
	// }
	// }
}
