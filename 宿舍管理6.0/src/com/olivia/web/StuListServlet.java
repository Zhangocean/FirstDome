package com.olivia.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.olivia.dao.StuDao;
import com.olivia.model.PageBean;
import com.olivia.model.Student;
import com.olivia.util.DbUtil;
import com.olivia.util.JsonUtil;
import com.olivia.util.ResponseUtil;
import com.olivia.util.StringUtil;

public class StuListServlet extends HttpServlet{
	DbUtil dbUtil=new DbUtil();
	StuDao stuDao=new StuDao();
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
			String page=request.getParameter("page");
			String rows=request.getParameter("rows");
			String stuName=request.getParameter("stuName");
			String stuNum=request.getParameter("stuNum");
			String dormId=request.getParameter("dormId");
			String Bbirthday=request.getParameter("Bbirthday");
			String Ebirthday=request.getParameter("Ebirthday");
			String sex=request.getParameter("sex");
			Connection con=null;
			Student student=null;
			try {
				con=dbUtil.getCon();
				PageBean pageBean=new PageBean( Integer.parseInt(rows), Integer.parseInt(page));
				student=new Student();
				if (stuNum!=null) {
					//System.out.println(stuNum);
					student.setStuName(stuName);
					student.setStuNum(stuNum);
					student.setSex(sex);
					if (!(StringUtil.isEmpty(dormId))) {
						student.setDormid(Integer.parseInt(dormId));
					}
				}
				ResultSet rs=stuDao.stuList(con, pageBean, student,Bbirthday,Ebirthday);
				JSONObject result=new JSONObject();
				JSONArray jsonArray=JsonUtil.formatRsToJsonArray(rs);
				int total=stuDao.stuCount(con, student,Bbirthday,Ebirthday);
				result.put("rows", jsonArray);
				result.put("total", total);
				ResponseUtil.write(response, result);
			} catch (Exception e) {
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