package com.olivia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.olivia.model.User;

public class CodeDao {

	public int updateCode(Connection con,User user) throws Exception {
		String sql="update t_user set password=? where userName=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getPassword());
		pstmt.setString(2, user.getUserName());
		return pstmt.executeUpdate();
	}
}
