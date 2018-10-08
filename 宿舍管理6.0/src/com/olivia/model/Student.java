package com.olivia.model;

import java.util.Date;

public class Student {

	private String stuId;
	private String stuNum;
	private String stuName;
	private String sex;
	private String dormName;
	private Date birthday;
	private String email;
	private String stuDes;
	private int dormid=-1;
	
	
	
	


	public Student(String stuNum, String stuName, String sex, Date birthday,
			String email, String stuDes, String stuId) {
		super();
		this.stuNum = stuNum;
		this.stuName = stuName;
		this.sex = sex;
		this.birthday = birthday;
		this.email = email;
		this.stuDes = stuDes;
		//this.dormid = dormid;
		this.stuId=stuId;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDormName() {
		return dormName;
	}
	public void setDormName(String dormName) {
		this.dormName = dormName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStuDes() {
		return stuDes;
	}
	public void setStuDes(String stuDes) {
		this.stuDes = stuDes;
	}
	public int getDormid() {
		return dormid;
	}
	public void setDormid(int dormid) {
		this.dormid = dormid;
	}
	public Student() {
		super();
	}
	
	public void show(){
		System.out.println(this.dormid+this.stuDes);
	}
	

}
