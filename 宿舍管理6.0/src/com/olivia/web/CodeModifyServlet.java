package com.olivia.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.olivia.dao.CodeDao;
import com.olivia.model.User;
import com.olivia.util.DbUtil;
import com.olivia.util.ResponseUtil;

/**
 * Servlet implementation class CodeModifyServlet
 */
public class CodeModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       DbUtil dbUtil=new DbUtil();
       CodeDao codeDao=new CodeDao();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oCode=request.getParameter("oCode");
		String nCode=request.getParameter("nCode");
		ServletContext sc=getServletConfig().getServletContext();
		User currentuser=(User)sc.getAttribute("currentuser");
		User user=new User(currentuser.getUserName(), nCode);
		Connection con=null;
		/*System.out.println(oCode);
		System.out.println(currentuser.getPassword());
		System.out.println(oCode==currentuser.getPassword());*/
		if (!currentuser.getPassword().equals(oCode)) {    //原密码和当前密码不等
			return;
		}
		try {
			int num=0;
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			num=codeDao.updateCode(con, user);
			result.put("result", "true");
			//result.put("errorMsg", "修改失败！");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
