package com.mall.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.search.dao.SolrDao;
import com.mall.search.pojo.SearchResult;
import com.mall.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SolrDao solrDao;
	
	@Override
	public SearchResult search(String queryStr,int page,int rows) throws Exception {
		
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(queryStr);
		//设置默认搜索域,df
		solrQuery.set("df", "item_keywords");
		solrQuery.setStart((page-1)*rows); //设置开始条数
		solrQuery.setRows(rows);
		//设置高亮显示
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<em style=\"color:read\">");
		solrQuery.setHighlightSimplePost("</em>");
		
		SearchResult searchResult = solrDao.search(solrQuery);
		long recordCount = searchResult.getRecordCount();
		long pageCount = recordCount / rows;
		if (recordCount % rows > 0) {
			pageCount++;
		}
		searchResult.setPageCount(pageCount);
		searchResult.setCurPage(page);
		
		return searchResult;
	}
	
}
