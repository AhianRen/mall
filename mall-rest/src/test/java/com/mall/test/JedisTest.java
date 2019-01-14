package com.mall.test;

import java.io.IOException;
import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.istack.internal.Pool;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
public class JedisTest {
	
	@Test
	public void testXmlConfig() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring:/applicationContext-*.xml");
		JedisPool pool = (redis.clients.jedis.JedisPool) applicationContext.getBean("jedisPool");
		Jedis jedis = pool.getResource();
		String value = jedis.get("key1");
		System.out.println(value);
		jedis.close();
		pool.close();
	}
	
	@Test
	public void JedisPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(30);//最大闲置个数
		config.setMinIdle(10);//最小闲置个数
		config.setMaxTotal(50);//最大连接数
		JedisPool pool = new JedisPool(config, "180.76.50.167", 6389, 10000, "Sid@r!");
		Jedis jedis = pool.getResource();
		System.out.println(jedis.get("key1"));
		jedis.close();
		pool.close();
	}
	
	
	@Test
	public void simpleJedisCluster() throws IOException {
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("180.76.50.167",7001));
		nodes.add(new HostAndPort("180.76.50.167",7002));
		nodes.add(new HostAndPort("180.76.50.167",7003));
		nodes.add(new HostAndPort("180.76.50.167",7004));
		nodes.add(new HostAndPort("180.76.50.167",7005));
		nodes.add(new HostAndPort("180.76.50.167",7006));
		
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("key2", "value2");
		System.out.println(cluster.get("key2"));
		cluster.close();
		
		
	}
	
	@Test
	public void  simpleJedis() {
		Jedis jedis = new Jedis("180.76.50.167",6379);
		jedis.auth("Sid@r!");
		//jedis.set("key2", "value2");
		System.out.println(jedis.get("key1"));
		jedis.close();
	}
	
	
}
