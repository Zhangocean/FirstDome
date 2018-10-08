package com.olivia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.olivia.model.Dorm;
import com.olivia.model.PageBean;
import com.olivia.model.Student;
import com.olivia.model.User;
import com.olivia.util.DateUtil;
import com.olivia.util.StringUtil;

public class StuDao {
	
	public ResultSet stuList(Connection con,PageBean pageBean,Student student,String Bbirthday,String Ebirthday )throws Exception {
		StringBuffer sb=new StringBuffer("select * from t_stu s ,t_dorm d where s.dormid=d.dormId ");  //
		if (!(StringUtil.isEmpty(student.getStuNum()))) {
			sb.append(" and s.stuNum like '%"+student.getStuNum()+"%'");
		}
		if (!(StringUtil.isEmpty(student.getStuName()))) {
			sb.append(" and s.stuName like '%"+student.getStuName()+"%'");
		}
		if (!(StringUtil.isEmpty(student.getSex()))) {
			sb.append(" and s.sex='"+student.getSex()+"'");
		}
		if (!(StringUtil.isEmpty(Bbirthday))) {
			sb.append(" and TO_DAYS(s.birthday)>= TO_DAYS('"+Bbirthday+"')");
		}
		if (!(StringUtil.isEmpty(Ebirthday))) {
			sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('"+Ebirthday+"')");
		}
		if (student.getDormid()!=-1) {
			sb.append(" and s.dormid='"+student.getDormid()+"'");
		}
		if (!(pageBean==null)) {
			sb.append(" limit " +pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	public int stuCount(Connection con,Student student,String Bbirthday,String Ebirthday)throws Exception {
		StringBuffer sb=new StringBuffer("SELECT COUNT(*) AS total FROM t_stu s ,t_dorm d where s.dormid=d.dormId ");
		if (!(StringUtil.isEmpty(student.getStuNum()))) {
			sb.append(" and s.stuNum like '%"+student.getStuNum()+"%'");
		}
		if (!(StringUtil.isEmpty(student.getStuName()))) {
			sb.append(" and s.stuName like '%"+student.getStuName()+"%'");
		}
		if (!(StringUtil.isEmpty(student.getSex()))) {
			sb.append(" and s.sex='"+student.getSex()+"'");
		}
		if (!(StringUtil.isEmpty(Bbirthday))) {
			sb.append(" and TO_DAYS(s.birthday)>= TO_DAYS('"+Bbirthday+"')");
		}
		if (!(StringUtil.isEmpty(Ebirthday))) {
			sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('"+Ebirthday+"')");
		}
		if (student.getDormid()!=-1) {
			sb.append(" and s.dormid='"+student.getDormid()+"'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		}
		return 0;
	}

	public int stuDelete(Connection con, String delIds) throws Exception {
		String sql="delete from t_stu where stuId in("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	public int stuSave(Connection con,Student student)throws Exception{
		String sql="insert into t_stu values(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, student.getStuName());
		pstmt.setString(2, student.getStuNum());
		pstmt.setString(3, student.getSex());
		pstmt.setString(4, DateUtil.formatDateToString(student.getBirthday()));
		pstmt.setString(5, String.valueOf(student.getDormid()));   //wei-1
		pstmt.setString(6, student.getEmail());
		pstmt.setString(7, student.getStuDes());
		pstmt.setInt(8, Integer.parseInt(student.getStuNum()));
		return pstmt.executeUpdate();
	}
	
	
	
	
	
	
	
	
	
	public int stuModify(Connection con,Student student)throws Exception{
		String sql="update t_stu set stuName=?,stuNum=?,sex=?,birthday=?,dormid=?,email=?,stuDes=? where stuId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, student.getStuName());
		pstmt.setString(2, student.getStuNum());
		pstmt.setString(3, student.getSex());
		pstmt.setString(4, DateUtil.formatDateToString(student.getBirthday()));
		pstmt.setInt(5, student.getDormid());   //wei-1
		pstmt.setString(6, student.getEmail());
		pstmt.setString(7, student.getStuDes());
		pstmt.setString(8,student.getStuId());
		return pstmt.executeUpdate();
	}
	
	
	
	
	
//	public Student sss(Connection con,Student student)throws Exception{
//		String sql="select * from t_dorm where dormId=? ";
//		PreparedStatement pstmt=con.prepareStatement(sql);
//		pstmt.setString(1, String.valueOf(student.getDormid()));
//		ResultSet rs=pstmt.executeQuery();
//		Student  resultUser=null;
//		if (rs.next()) {
//			resultUser=new Student();
//			resultUser.setDormid(Integer.parseInt(rs.getString("dormId")));
//			resultUser.setDormName(rs.getString("dormName"));
//		}
//		return resultUser;
//	}
}
