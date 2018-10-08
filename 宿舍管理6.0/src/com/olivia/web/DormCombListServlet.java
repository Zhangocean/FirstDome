package com.olivia.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.olivia.dao.DormDao;
import com.olivia.util.DbUtil;
import com.olivia.util.JsonUtil;
import com.olivia.util.ResponseUtil;

public class DormCombListServlet extends HttpServlet{
	DbUtil dbUtil=new DbUtil();
	DormDao dormDao=new DormDao();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setCharacterEncoding("utf-8");
			Connection con=null;
			try {
				con=dbUtil.getCon();
				JSONArray jsonArray=new JSONArray();
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("dormId", "");
				jsonObject.put("dormName", "«Î—°‘Ò...");
				jsonArray.add(jsonObject);
				jsonArray.addAll(JsonUtil.formatRsToJsonArray(dormDao.dormList(con, null,null)));
				ResponseUtil.write(response, jsonArray);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

	
}
