package com.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.pojo.TreeNode;
import com.mall.service.ContentCategoryService;

@RequestMapping("/content/category")
@Controller
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<TreeNode> contentCategoryList(@RequestParam(value="id",defaultValue="0")Long id) {
		return contentCategoryService.getTreeNodesByParentNodeId(id);
	}
	
	
	
}
