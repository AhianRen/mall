package com.mall.service.impl;

import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.common.pojo.TreeNode;
import com.mall.mapper.TbItemCatMapper;
import com.mall.pojo.TbItemCat;
import com.mall.pojo.TbItemCatExample;
import com.mall.pojo.TbItemCatExample.Criteria;
import com.mall.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<TreeNode> getItemCatList(Long parentId) {
		
		TbItemCatExample example = new TbItemCatExample();
		
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		
		List<TreeNode> returnList = new ArrayList<>();
		for(TbItemCat itemCat : list) {
			TreeNode treeNode = new TreeNode(itemCat.getId(), itemCat.getName(), itemCat.getIsParent()?"closed":"open");
			returnList.add(treeNode);
		}
		
		return returnList;
	}

}
