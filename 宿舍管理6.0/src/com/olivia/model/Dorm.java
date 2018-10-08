package com.olivia.model;

public class Dorm {
	private int dormId;
	private String dormName;
	private String dormDes;
	
	
	public Dorm() {
		super();
	}
	
	
	public Dorm(String dormName, String dormDes) {
		super();
		this.dormName = dormName;
		this.dormDes = dormDes;
	}


	
	
	public int getDormId() {
		return dormId;
	}


	public void setDormId(int dormId) {
		this.dormId = dormId;
	}


	public String getDormName() {
		return dormName;
	}
	public void setDormName(String dormName) {
		this.dormName = dormName;
	}
	public String getDormDes() {
		return dormDes;
	}
	public void setDormDes(String dormDes) {
		this.dormDes = dormDes;
	}
	
	
}
