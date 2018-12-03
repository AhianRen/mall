package com.mall.service;

import com.mall.common.pojo.EUDataGridResult;

public interface ContentService {

	EUDataGridResult getContentListByCategoryId(Integer page, Integer rows, Long categoryId);

}
