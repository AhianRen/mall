package com.mall.common.pojo;

import java.util.List;

public class EUDataGridResult {
	private Long total;
	private List<?> rows;
	
	
	public EUDataGridResult(Long total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public EUDataGridResult() {
		super();
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
