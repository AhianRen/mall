package com.mall.portal.pojo;

import java.util.List;

public class SearchResult {
	/**
	 * 分页查询结果
	 */
	private List<Item> items;
	/**
	 * 查询结果总数
	 */
	private long recordCount;
	/**
	 * 查询结果总页数
	 */
	private long pageCount;
	/**
	 * 查询结果当前页数
	 */
	private long curPage;
	
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public long getCurPage() {
		return curPage;
	}
	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}
	
	
	
}
