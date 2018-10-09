package com.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.common.pojo.EUDataGridResult;
import com.mall.common.pojo.MallResult;
import com.mall.pojo.TbItem;
import com.mall.service.ItemService;
/**
 * 商品controller
 * @author ren1
 *
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		return itemService.getItemById(itemId);
	}
	/**
	 * 获取商品列表
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page,Integer rows) {
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping("/item/save")
	@ResponseBody
	public MallResult addItem(TbItem item,String desc) {
		return itemService.addItem(item,desc);
	}
}
