package com.olivia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.olivia.model.Dorm;
import com.olivia.model.User;

public class UserDao {

	public User login(Connection con,User user)throws Exception{
		String sql="select * from t_user where userName=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		User resultUser=null;
		if (rs.next()) {
			resultUser=new User();
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setClassname(rs.getString("classname"));
		}
		return resultUser;
	}
	
	
	
	public int usersave(Connection con,User user)throws Exception{
		System.out.println(user.getUserName());
		String sql="insert into t_user values(?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3,user.getClassname());
		return pstmt.executeUpdate();
	}
	
	
	
}
