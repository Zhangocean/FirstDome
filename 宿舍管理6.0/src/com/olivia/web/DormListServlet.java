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
import com.olivia.model.Dorm;
import com.olivia.model.PageBean;
import com.olivia.util.DbUtil;
import com.olivia.util.JsonUtil;
import com.olivia.util.ResponseUtil;

public class DormListServlet extends HttpServlet{
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
			String rows=request.getParameter("rows");
			String page=request.getParameter("page");
			String dormName=request.getParameter("dormName");
			PageBean pageBean=new PageBean(Integer.parseInt(rows), Integer.parseInt(page));
			Connection con=null;
			try {
				con=dbUtil.getCon();
				if (dormName==null) {
					dormName="";
				}
				Dorm dorm=new Dorm();
				dorm.setDormName(dormName);
				JSONObject result=new JSONObject();
				JSONArray jsonArray=JsonUtil.formatRsToJsonArray(dormDao.dormList(con, pageBean,dorm));
				int total=dormDao.dormCount(con);
			    result.put("rows", jsonArray);
				result.put("total", total);
				ResponseUtil.write(response, result);
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
