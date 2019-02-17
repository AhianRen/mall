package com.mall.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mall.common.pojo.MallResult;
import com.mall.common.utils.JsonUtils;
import com.mall.mapper.TbItemDescMapper;
import com.mall.mapper.TbItemMapper;
import com.mall.mapper.TbItemParamItemMapper;
import com.mall.pojo.TbItem;
import com.mall.pojo.TbItemDesc;
import com.mall.pojo.TbItemParam;
import com.mall.pojo.TbItemParamItem;
import com.mall.pojo.TbItemParamItemExample;
import com.mall.pojo.TbItemParamItemExample.Criteria;
import com.mall.rest.dao.JedisClient;
import com.mall.rest.service.ItemService;

import redis.clients.jedis.Jedis;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired 
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;
	
	@Override
	public MallResult getItemInfo(long itemId) {
		
		String jsonStr = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":base");
		if (StringUtils.isNotBlank(jsonStr)) {
			TbItem item = JsonUtils.jsonToPojo(jsonStr, TbItem.class);
			return MallResult.ok(item);
		}
		
		TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":base",JsonUtils.objectToJson(tbItem));
		jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":base", REDIS_ITEM_EXPIRE);
		return MallResult.ok(tbItem);
	}
	
	@Override
	public MallResult getItemDesc(long itemId) {
		String jsonStr = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":desc");
		if (StringUtils.isNotBlank(jsonStr)) {
			TbItemDesc itemDesc = JsonUtils.jsonToPojo(jsonStr, TbItemDesc.class);
			return MallResult.ok(itemDesc);
		}
		
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":desc",JsonUtils.objectToJson(itemDesc));
		jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":desc", REDIS_ITEM_EXPIRE);
		return MallResult.ok(itemDesc);
	}
	
	@Override
	public MallResult getItemParam(Long itemId) {
		String jsonStr = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":param");
		if (StringUtils.isNotBlank(jsonStr)) {
			TbItemParam itemParam = JsonUtils.jsonToPojo(jsonStr, TbItemParam.class);
			return MallResult.ok(itemParam);
		}
		
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		
		List<TbItemParamItem> itemParamItems = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (itemParamItems != null && itemParamItems.size()>0) {
			TbItemParamItem itemParamItem = itemParamItems.get(0);
			jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":param",JsonUtils.objectToJson(itemParamItem));
			jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":param", REDIS_ITEM_EXPIRE);
			return MallResult.ok(itemParamItem);
		}
		
		return MallResult.build(400, "此商品无规格信息");
	}
	
	
}
