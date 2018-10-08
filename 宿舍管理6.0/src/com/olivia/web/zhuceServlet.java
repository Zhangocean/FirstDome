package com.olivia.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.olivia.dao.UserDao;
import com.olivia.model.Dorm;
import com.olivia.model.User;
import com.olivia.util.DbUtil;
import com.olivia.util.ResponseUtil;
import com.olivia.util.StringUtil;

import net.sf.json.JSONObject;

public class zhuceServlet extends HttpServlet {

	DbUtil dbUtil=new DbUtil();
	UserDao UserDao=new UserDao();
	
	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String classname=request.getParameter("classname");
		//String remember=request.getParameter("remember");
		
		User user=new User(userName,password);
		user.setClassname(classname);
		Connection con=null;
		
		
		try {
			int saveNum=0;
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			
				saveNum=UserDao.usersave(con, user);
				System.out.println("µΩ÷˜“≥");
				
				response.sendRedirect("index.html");	
				
				
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

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
