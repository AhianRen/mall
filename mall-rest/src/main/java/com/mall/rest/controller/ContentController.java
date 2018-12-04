package com.mall.rest.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.pojo.MallResult;
import com.mall.common.utils.ExceptionUtil;
import com.mall.pojo.TbContent;
import com.mall.rest.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	private static final Logger logger = Logger.getLogger(ContentController.class);
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/list/{catId}")
	@ResponseBody
	public MallResult contentList(@PathVariable Long catId){
		try {
			List<TbContent> result = contentService.getContentsByCatId(catId);
			return MallResult.ok(result);
		} catch (Exception e) {
			logger.error("根据分类ID查询内容列表失败",e);
			return MallResult.build(500,ExceptionUtil.getStackTrace(e));
		}
	}
	
	
}
