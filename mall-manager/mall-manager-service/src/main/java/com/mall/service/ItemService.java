package com.mall.service;

import com.mall.common.pojo.EUDataGridResult;
import com.mall.common.pojo.MallResult;
import com.mall.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(Long id);
	EUDataGridResult getItemList(int page,int rows);
	MallResult addItem(TbItem item,String desc);
}
