package com.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.EUDataGridResult;
import com.mall.mapper.TbContentMapper;
import com.mall.pojo.TbContent;
import com.mall.pojo.TbContentExample;
import com.mall.pojo.TbContentExample.Criteria;

@Service
public class ContentService implements com.mall.service.ContentService {
	
	@Autowired
	private TbContentMapper contentMapper;
	@Override
	public EUDataGridResult getContentListByCategoryId(Integer page,Integer rows,Long categoryId) {
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		PageHelper.startPage(page,rows);
		List<TbContent> list = contentMapper.selectByExample(example);
		
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		EUDataGridResult result = new EUDataGridResult(pageInfo.getTotal(), list);
		return result;
	}
	
}
