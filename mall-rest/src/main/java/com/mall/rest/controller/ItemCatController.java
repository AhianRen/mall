package com.mall.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.utils.JsonUtils;
import com.mall.rest.pojo.CatResult;
import com.mall.rest.service.ItemCatService;

@Controller
@RequestMapping("/itemcat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	/*
	@RequestMapping(value = "/all",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getCatAll(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		String json = JsonUtils.objectToJson(catResult);
		return callback+"("+json+");";
	}*/
	@RequestMapping(value = "/all",method = RequestMethod.GET)
	@ResponseBody
	public Object getCatAll(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
	
}
