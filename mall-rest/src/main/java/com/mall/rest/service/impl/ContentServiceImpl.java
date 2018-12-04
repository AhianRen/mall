package com.mall.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.mapper.TbContentMapper;
import com.mall.pojo.TbContent;
import com.mall.pojo.TbContentExample;
import com.mall.pojo.TbContentExample.Criteria;
import com.mall.rest.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Override
	public List<TbContent> getContentsByCatId(Long catId){
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(catId);
		List<TbContent> list = contentMapper.selectByExample(example);
		return list;
	}
	
}
