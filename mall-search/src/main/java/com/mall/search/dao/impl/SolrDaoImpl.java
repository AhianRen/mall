package com.mall.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mall.search.dao.SolrDao;
import com.mall.search.pojo.Item;
import com.mall.search.pojo.SearchResult;
@Repository
public class SolrDaoImpl implements SolrDao {
	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public SearchResult search(SolrQuery solrQuery) throws Exception {
		
		QueryResponse response = solrServer.query(solrQuery);
		SolrDocumentList documentList = response.getResults();
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		
		List<Item> items = new ArrayList<>();
		for (SolrDocument doc : documentList) {
			Item item = new Item();
			item.setId((String) doc.get("id"));
			
			//取高亮结果
			List<String> list = highlighting.get(doc.get("id")).get("item_title");
			String title = "";
			if (null != list && list.size() > 0){
				title = list.get(0);
			}else {
				title = (String) doc.get("item_title");
			}
			item.setTitle(title);
			
			item.setImage((String) doc.get("item_image"));
			item.setPrice((long) doc.get("item_price"));
			item.setSell_point((String) doc.get("item_sell_point"));
			item.setCategory_name((String) doc.get("item_category_name"));
			
			items.add(item);
		}
		
		SearchResult result = new SearchResult();
		result.setItems(items);
		result.setRecordCount(documentList.getNumFound());
		return result;
	}
}
