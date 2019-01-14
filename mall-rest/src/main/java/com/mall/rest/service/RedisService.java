package com.mall.rest.service;

import com.mall.common.pojo.MallResult;

public interface RedisService {
	
	MallResult syncContent(Long catId);
	
}
