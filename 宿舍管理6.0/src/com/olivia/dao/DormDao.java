package com.olivia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.olivia.model.Dorm;
import com.olivia.model.PageBean;
import com.olivia.util.StringUtil;

public class DormDao {

	public ResultSet dormList(Connection con,PageBean pageBean,Dorm s_dorm)throws Exception {
		StringBuffer sb=new StringBuffer("select * from t_dorm ");
		if (s_dorm!=null&&!(StringUtil.isEmpty(s_dorm.getDormName()))) {
			sb.append(" and dormName like '%"+s_dorm.getDormName()+"%'");   //sqlÒ×ÉÙ¼Ó''
		}
		if (!(pageBean==null)) {
			sb.append(" limit " +pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	public int dormCount(Connection con)throws Exception{
		String  sql="select count(*) as total from t_dorm";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		}else {
			return 0;
		}
	}

	public int dormDelete(Connection con, String delIds) throws Exception {
		String sql="delete from t_dorm where dormId in("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	public int dormSave(Connection con,Dorm dorm)throws Exception{
		System.out.println(dorm.getDormName());
		String sql="insert into t_dorm values(?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dorm.getDormName());
		pstmt.setString(2, dorm.getDormDes());
		pstmt.setString(3,dorm.getDormName());
		return pstmt.executeUpdate();
	}
	
	public int dormModify(Connection con,Dorm dorm)throws Exception{
		String sql="update t_dorm set dormName=?,dormDes=?,dormId=? where dormId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dorm.getDormName());
		pstmt.setString(2, dorm.getDormDes());
		pstmt.setString(3, dorm.getDormName());
		pstmt.setInt(4, dorm.getDormId());
		return pstmt.executeUpdate();
	}
}
