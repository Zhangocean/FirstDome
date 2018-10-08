<%@page import="com.olivia.model.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生信息管理系统</title>
</head>
<script type="text/javascript">
	var remember=0;
	//var uName;
	//var uPassword;
	function reset(){
		document.getElementById("userName").value="";
		document.getElementById("password").value="";
	}
	
	
		//alert(remember);
	
	

	
	
</script>
<link rel="stylesheet" href="css1/font-awesome.css"> <!-- Font-Awesome-Icons-CSS -->
<link rel="stylesheet" href="css1/style.css" type="text/css" media="all" /> 
<body >
<div class="banner-overlay-agileinfo">
 <form action="zhuce" method="post" onsubmit="check()">
		<!--header-->
		<div class="header-w3l">
			<h1>学生公寓管理系统</h1>
		</div>
		<!--//header-->
		<!--main-->
		<div class="main-w3layouts-agileinfo">
              
						<div class="wthree-form">
							<h2>用户注册</h2>
				
								<div class="form-sub-w3">
									<input type="text" name="userName" placeholder="userName " required />
								  <h3>用户名</h3>
							  </div>
								<div class="form-sub-w3">
									<input type="password" name="password" placeholder="password" required />
								  <h3>密码</h3>
							  </div>
							 <div class="form-sub-w3">
									    <select id="classname" name="classname"  >
									     <option value="0">请选择</option>
                                          <option value="1">老师</option>
                                          <option value="0" >学生</option>

                                         </select>
							  </div>
								<p class="forgot-w3ls">优美环境你我创<a class href="#"> </a></p>
								<p class="forgot-w3ls1">温馨宿舍齐共享<a class href="#">  </a></p>
								
								<div class="clear"></div>
								<div class="submit-agileits">
                    
								
                             <td><input type="submit" value="注册"></td>
							<td><input type="submit" value="重置" onclick="reset()"></td>
							
							
							 
                           

						</div>
				

		</div>
		
		<div class="footer">
			<p><a href="index.html">主页</a></p>
		</div>
 
</body>
</html>