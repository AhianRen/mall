package com.mall.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.common.pojo.TreeNode;
import com.mall.mapper.TbContentCategoryMapper;
import com.mall.pojo.TbContentCategory;
import com.mall.pojo.TbContentCategoryExample;
import com.mall.pojo.TbContentCategoryExample.Criteria;
import com.mall.service.ContentCategoryService;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<TreeNode> getTreeNodesByParentNodeId(Long parentId){
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TreeNode> treeNodes = new ArrayList<>();
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		for (TbContentCategory c : list) {
			TreeNode treeNode = new TreeNode(c.getId(),c.getName(),c.getIsParent()?"closed":"open");
			treeNodes.add(treeNode);
		}
		return treeNodes;
	}
}
