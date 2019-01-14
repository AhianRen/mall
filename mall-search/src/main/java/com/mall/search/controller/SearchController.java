package com.mall.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.pojo.MallResult;
import com.mall.common.utils.ExceptionUtil;
import com.mall.search.pojo.SearchResult;
import com.mall.search.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	@ResponseBody
	public MallResult search(@RequestParam("q")String queryStr,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="60")Integer rows){

		if (StringUtils.isBlank(queryStr)) {
			return MallResult.build(400,"查询条件不能为空");
		}
		
		try {
			queryStr = new String(queryStr.getBytes("iso8859-1"), "utf-8");
			SearchResult result = searchService.search(queryStr, page, rows);
			return MallResult.ok(result);
		} catch (Exception e) {
			return MallResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	} 
}
