package com.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.EUDataGridResult;
import com.mall.common.pojo.MallResult;
import com.mall.common.utils.IDUtils;
import com.mall.mapper.TbItemDescMapper;
import com.mall.mapper.TbItemMapper;
import com.mall.pojo.TbItem;
import com.mall.pojo.TbItemDesc;
import com.mall.pojo.TbItemExample;
import com.mall.service.ItemService;
/**
 * 商品Servie
 * @author ren1
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Override
	public TbItem getItemById(Long id) {
		TbItem item = itemMapper.selectByPrimaryKey(id);
		return item;
	}
	/**
	 * 商品列表查询
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	/**
	 * 商品添加
	 */
	@Override
	public MallResult addItem(TbItem item,String desc) {
		
		Date date = new Date();
		//补全TbItem
		item.setId(IDUtils.genItemId());
		item.setStatus((byte) 1);
		item.setCreated(date);
		item.setUpdated(date);
		itemMapper.insert(item);
		
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(item.getId());
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		
		itemDescMapper.insert(tbItemDesc);
		return MallResult.ok();
	}

}
