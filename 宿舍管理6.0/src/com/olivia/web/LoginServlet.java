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
import com.olivia.model.User;
import com.olivia.util.DbUtil;
import com.olivia.util.StringUtil;

public class LoginServlet extends HttpServlet{
	DbUtil dbUtil=new DbUtil();
	UserDao UserDao=new UserDao();
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
			String userName=request.getParameter("userName");
			String password=request.getParameter("password");
			//String remember=request.getParameter("remember");
			if (StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)) {
				request.setAttribute("error", "用户名或密码不能为空！");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			
			User user=new User(userName, password);
			Connection con=null;
			User currentuser=new User();
			try {
				con=dbUtil.getCon();
				currentuser=UserDao.login(con, user);
				if (currentuser==null) {
					request.setAttribute("user", user);
					request.setAttribute("error", "用户名或密码错误！");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}else if(currentuser.getClassname().equals("1")) {
					/*HttpSession session=request.getSession();
					session.setAttribute("currentuser", currentuser);*/
					ServletContext sc=this.getServletContext();
					sc.setAttribute("currentuser", currentuser);
					response.sendRedirect("index.jsp");
					
				}
				else if(currentuser.getClassname().equals("0")){
					ServletContext sc=this.getServletContext();
					sc.setAttribute("currentuser", currentuser);
					response.sendRedirect("index1.jsp");
				
				}
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
