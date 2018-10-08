package com.olivia.model;

public class PageBean {
	
	private int rows;//单页记录数
	private int page;//当前页
	private int start;//起始页
	
	
	
	public PageBean(int rows, int page) {
		super();
		this.rows = rows;
		this.page = page;
	}
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getStart() {
		return (page-1)*rows;
	}
	
	
	

}
