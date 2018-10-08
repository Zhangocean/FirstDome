package com.olivia.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.olivia.dao.DormDao;
import com.olivia.model.Dorm;
import com.olivia.util.DbUtil;
import com.olivia.util.ResponseUtil;
import com.olivia.util.StringUtil;

public class DormSaveServlet extends HttpServlet{
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
			request.setCharacterEncoding("utf-8");
			String dormName=request.getParameter("dormName");
			String dormDes=request.getParameter("dormDes");
			String dormId=request.getParameter("dormId");
			Connection con=null;
			Dorm dorm=new Dorm(dormName, dormDes);
			if (!(StringUtil.isEmpty(dormId))) {
				dorm.setDormId(Integer.parseInt(dormId));
			}
			try {
				int saveNum=0;
				con=dbUtil.getCon();
				JSONObject result=new JSONObject();
				if (!(StringUtil.isEmpty(dormId))) {
					saveNum=dormDao.dormModify(con, dorm);
				}else {
					saveNum=dormDao.dormSave(con, dorm);
				}
				if (saveNum>0) {
					result.put("success", "true");
				}else {
					result.put("success", "true");
					result.put("errorMsg", "ÃÌº” ß∞‹!");
				}
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
