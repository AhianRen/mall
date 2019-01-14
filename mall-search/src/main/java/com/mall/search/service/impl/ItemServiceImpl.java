package com.mall.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mall.common.pojo.MallResult;
import com.mall.common.utils.ExceptionUtil;
import com.mall.search.mapper.ItemMapper;
import com.mall.search.pojo.Item;
import com.mall.search.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
	private static final Logger logger = Logger.getLogger(ItemServiceImpl.class);
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private SolrServer solrServer; 
	
	@Override
	public MallResult importAllItem() {
		
		try {
			List<Item> items = itemMapper.selectAll();
			
			for (Item item : items) {
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", item.getId());
				document.setField("item_title", item.getTitle());
				document.setField("item_sell_point", item.getSell_point());
				document.setField("item_price", item.getPrice());
				document.setField("item_image", item.getImage());
				document.setField("item_category_name", item.getCategory_name());
				document.setField("item_desc", item.getItem_des());
				solrServer.add(document);
			}
			
			solrServer.commit();
		} catch (Exception e) {
			logger.error("向solr中导入商品错误",e);
			return MallResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return MallResult.ok();
	}

}
