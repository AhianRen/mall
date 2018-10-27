package com.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.pojo.EUDataGridResult;
import com.mall.common.pojo.MallResult;
import com.mall.pojo.TbItemParam;
import com.mall.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public MallResult getItemParamByCId(@PathVariable long itemCatId) {
		
		return itemParamService.getItemParamByCId(itemCatId);
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public MallResult saveItemParam(@PathVariable long cid,String paramData) {
		
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		return itemParamService.insertItemParam(itemParam);
	}
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public EUDataGridResult itemParamList(@RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "30")int rows) {
		return itemParamService.getItemParamList(page, rows);
	}
	
}
