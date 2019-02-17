package com.mall.rest.service;

import com.mall.common.pojo.MallResult;

public interface ItemService {

	MallResult getItemInfo(long itemId);

	MallResult getItemDesc(long itemId);

	MallResult getItemParam(Long itemId);

}
