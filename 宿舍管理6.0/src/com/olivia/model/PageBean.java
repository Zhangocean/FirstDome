package com.olivia.model;

public class PageBean {
	
	private int rows;//��ҳ��¼��
	private int page;//��ǰҳ
	private int start;//��ʼҳ
	
	
	
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
