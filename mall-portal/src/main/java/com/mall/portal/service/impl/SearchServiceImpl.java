package com.mall.portal.service.impl;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mall.common.pojo.MallResult;
import com.mall.common.utils.HttpClientUtil;
import com.mall.common.utils.JsonUtils;
import com.mall.portal.pojo.SearchResult;
import com.mall.portal.service.SearchService;
@Service
public class SearchServiceImpl implements SearchService{
	
	@Value("${SEARCH_BASE_URL}")
	private String url;
	
	
	@Override
	public SearchResult search(String queryStr,int page) {
		
		Map<String, String> param = new HashMap<>();
		param.put("q", queryStr);
		param.put("page", ""+page);
		String jsonStr = HttpClientUtil.doGet(url, param);
		MallResult mallResult = MallResult.formatToPojo(jsonStr, SearchResult.class);
		//MallResult mallResult = JsonUtils.jsonToPojo(jsonStr, MallResult.class);
		if (mallResult.getStatus() == 200) {
			return (SearchResult) mallResult.getData();
		}
		
		return null;
		
		
	}
	
	
	
}
