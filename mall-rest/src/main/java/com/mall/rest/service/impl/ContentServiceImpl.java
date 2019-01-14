package com.mall.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mall.common.utils.JsonUtils;
import com.mall.mapper.TbContentMapper;
import com.mall.pojo.TbContent;
import com.mall.pojo.TbContentExample;
import com.mall.pojo.TbContentExample.Criteria;
import com.mall.rest.dao.JedisClient;
import com.mall.rest.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public List<TbContent> getContentsByCatId(Long catId)throws Exception{
		
		String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, String.valueOf(catId));
		if (StringUtils.isNotBlank(result)) {
			return JsonUtils.jsonToList(result, TbContent.class);
		}
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(catId);
		List<TbContent> list = contentMapper.selectByExample(example);
		
		String jsonStr = JsonUtils.objectToJson(list);
		
		jedisClient.hset(INDEX_CONTENT_REDIS_KEY,String.valueOf(catId),jsonStr);
		
		return list;
	}
	
}
