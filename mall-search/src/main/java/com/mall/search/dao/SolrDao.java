package com.mall.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.mall.search.pojo.SearchResult;

public interface SolrDao {

	SearchResult search(SolrQuery solrQuery) throws Exception;

}
