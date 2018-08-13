package com.mall.service;

import java.util.List;

import com.mall.common.pojo.TreeNode;

public interface ItemCatService {
	
	List<TreeNode> getItemCatList(Long parentId);
	
	
}
