package com.mall.search;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SoljTest {
	/**
	 * 添加索引
	 * @throws Exception
	 */
	@Test
	public void add() throws Exception{
		SolrServer server = new HttpSolrServer("http://180.76.50.167:8983/solr/core1/");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test01");
		document.addField("item_title", "测试商品");
		document.addField("item_price", 12345);
		server.add(document);
		
		server.commit();
	
	}
	
	@Test
	public void simpleQuery() throws Exception {
		SolrServer server = new HttpSolrServer("http://180.76.50.167:8983/solr/core1/"); 
		SolrQuery query = new SolrQuery();
		query.setQuery("item_title:商品");
		QueryResponse response = server.query(query);
		SolrDocumentList solrDocumentList = response.getResults();
		long count = solrDocumentList.getNumFound();
		System.out.println("查询结果总数："+count);
		for(SolrDocument document :solrDocumentList) {
			System.out.println(document.get("id"));
			System.out.println(document.get("item_title"));
			System.out.println(document.get("item_price"));
		}
	}
	
	@Test
	public void delete() throws Exception{
		SolrServer server = new HttpSolrServer("http://180.76.50.167:8983/solr/core1/");
		server.deleteByQuery("*:*");
		server.commit();
	}
	
	
}
