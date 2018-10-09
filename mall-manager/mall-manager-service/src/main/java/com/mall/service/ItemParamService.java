package com.mall.service;

import com.mall.common.pojo.MallResult;
import com.mall.pojo.TbItemParam;

public interface ItemParamService {
	/**
	 * 通过Id获取商品规格模板
	 * @param catId
	 * @return
	 */
	public MallResult getItemParamByCId(long catId);
	/**
	 * 插入商品规格模板
	 * @param itemParam
	 * @return
	 */
	public MallResult insertItemParam(TbItemParam itemParam);
	
	
	
}
