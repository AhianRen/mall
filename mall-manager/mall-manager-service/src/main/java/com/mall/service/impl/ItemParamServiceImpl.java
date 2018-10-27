package com.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.EUDataGridResult;
import com.mall.common.pojo.MallResult;
import com.mall.mapper.TbItemParamMapper;
import com.mall.pojo.TbItemParam;
import com.mall.pojo.TbItemParamExample;
import com.mall.pojo.TbItemParamExample.Criteria;
import com.mall.service.ItemParamService;
@Service
public class ItemParamServiceImpl implements ItemParamService{
	
	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Override
	public MallResult getItemParamByCId(long catId) {
		
		TbItemParamExample itemParamExample = new TbItemParamExample();
		Criteria criteria = itemParamExample.createCriteria();
		criteria.andItemCatIdEqualTo(catId);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(itemParamExample);
		
		if (list!=null && list.size()>0) {
			return MallResult.ok(list.get(0));
		}
		//无数据，js中判断有没有数据
		return MallResult.ok();
	}

	@Override
	public MallResult insertItemParam(TbItemParam itemParam) {
		Date date = new Date();
		itemParam.setCreated(date);
		itemParam.setUpdated(date);
		itemParamMapper.insertSelective(itemParam);
		return MallResult.ok();
	}
	@Override
	public EUDataGridResult getItemParamList(int page,int rows) {
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		return new EUDataGridResult(pageInfo.getTotal(), list);
	}

}
