package com.mall.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.pojo.MallResult;
import com.mall.rest.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/info/{itemid}")
	@ResponseBody
	public MallResult ItemInfo(@PathVariable("itemid") Long itemid) {
		
		return itemService.getItemInfo(itemid);
	}
	
	@RequestMapping("/desc/{itemid}")
	@ResponseBody
	public MallResult ItemDesc(@PathVariable("itemid") Long itemid) {
		return itemService.getItemDesc(itemid);
	}
	
	@RequestMapping("/param/{itemid}")
	@ResponseBody
	public MallResult ItemParam(@PathVariable("itemid") Long itemId) {
		return itemService.getItemParam(itemId);
	}
	
}
