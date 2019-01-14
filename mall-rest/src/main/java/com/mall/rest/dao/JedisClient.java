package com.mall.rest.dao;

public interface JedisClient {
	String set(String key,String value);
	String get(String key);
	long hset(String hkey,String key,String value);
	String hget(String hkey,String key);
	/**
	 *	将key的过期时间设置为second秒
	 * @param key
	 * @param second
	 * @return
	 */
	long expire(String key,int seconds);
	/**
	 * 	获取key的剩余过期时间
	 * @param key
	 * @return
	 */
	long ttl(String key);
	
	long del(String key);
	long hdel(String hkey,String key);
	
}
