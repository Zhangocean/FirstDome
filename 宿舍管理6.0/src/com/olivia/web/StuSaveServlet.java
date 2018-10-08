package com.olivia.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.olivia.dao.StuDao;
import com.olivia.model.Student;
import com.olivia.util.DateUtil;
import com.olivia.util.DbUtil;
import com.olivia.util.ResponseUtil;
import com.olivia.util.StringUtil;

public class StuSaveServlet extends HttpServlet{
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
			request.setCharacterEncoding("utf-8");
			String stuName=request.getParameter("stuName");
			String stuNum=request.getParameter("stuNum");
			String sex=request.getParameter("sex");
			String birthday=request.getParameter("birthday");
			String dormid=request.getParameter("dormName");   //有问题，传出来的是宿舍名字，不是他的id
			String email=request.getParameter("email");
			String stuDes=request.getParameter("stuDes");
			String stuId=request.getParameter("stuId");//xuehaojin
			System.out.println("测试dormid:"+dormid);
			
			System.out.println("ceshi "+stuId);
			Connection con=null;
			Student student=new Student(stuNum, stuName, sex, DateUtil.formStringToDate(birthday), email, stuDes, stuId);
			//Student student1=new Student(stuNum, stuName, sex, DateUtil.formStringToDate(birthday), email, stuDes, stuId);
			
			
			
			
			
		
				
					
//				Student xxx=new Student();
//				try {
//					xxx=stuDao.sss(con, student);
//					int di=xxx.getDormid();
//					String dn=xxx.getDormName();
//					
//					System.out.println("导出来的dormid"+di);
//					System.out.println("导出来的dormname"+dn);
//					
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
					
				
			
			
			
			
			
			
			
			//ceshi,不用直接删除
			
			student.setDormid(Integer.parseInt(dormid));
			
			System.out.println("int型dormid:"+Integer.parseInt(dormid));
			
			if (!(StringUtil.isEmpty(stuId))) {
//				student.setStuId(Integer.parseInt(stuId));
			}
			try {
				int saveNum=0;
				con=dbUtil.getCon();
				JSONObject result=new JSONObject();
				
				System.out.println("qqqqqq");
				
				if(!(StringUtil.isEmpty(stuId))) {
					System.out.println("修改aaaa");
					saveNum=stuDao.stuModify(con, student);
				}else{
					System.out.println("修改bbbbb");
					saveNum=stuDao.stuSave(con, student);
				}
				System.out.println(saveNum);
				if (saveNum>0) {
					result.put("success", "true");
				}else {
					result.put("success", "true");
					result.put("errorMsg", "添加失败!");
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
