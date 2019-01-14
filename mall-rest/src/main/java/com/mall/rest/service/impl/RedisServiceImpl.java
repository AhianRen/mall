package com.mall.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mall.common.pojo.MallResult;
import com.mall.rest.dao.JedisClient;
import com.mall.rest.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public MallResult syncContent(Long catId) {
		
		jedisClient.hdel(INDEX_CONTENT_REDIS_KEY,String.valueOf(catId));
		return MallResult.ok();
	}

}
