package com.mall.service;

import java.util.List;

import com.mall.common.pojo.TreeNode;

public interface ContentCategoryService {

	List<TreeNode> getTreeNodesByParentNodeId(Long parentId);

}
