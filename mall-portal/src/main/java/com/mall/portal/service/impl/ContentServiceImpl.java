package com.mall.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mall.common.pojo.MallResult;
import com.mall.common.utils.HttpClientUtil;
import com.mall.common.utils.JsonUtils;
import com.mall.pojo.TbContent;
import com.mall.portal.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;

	@Override
	public String getIndexBigAD() {

		String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
		MallResult mallResult = MallResult.formatToList(result, TbContent.class);
		List<TbContent> data = (List<TbContent>) mallResult.getData();
		List<Map> list = new ArrayList<>();
		for (TbContent content : data) {
			Map map = new HashMap<>();
			map.put("src", content.getPic());
			map.put("alt", content.getSubTitle());
			map.put("href", content.getUrl());
			map.put("height", 240);
			map.put("width", 670);
			map.put("srcB", content.getPic2());
			map.put("heightB", 240);
			map.put("widthB", 550);
			list.add(map);
		}

		return JsonUtils.objectToJson(list);
	}

}
