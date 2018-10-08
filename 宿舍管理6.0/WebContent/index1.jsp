<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index1.jsp' starting page</title>
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
	<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
</head>
<script type="text/javascript">
	$(function(){
		var treedata=[{
			text:"根目录",
			children:[
				{
					text:"学生信息管理",
					attributes:{
						url:"stuManage.jsp"
					}
				},{
					text:"修改密码",
					attributes:{
						url:"codeManage.jsp"
				}
			}],
		}];
	 
		$("#tree").tree({
			data:treedata,
			lines:true,
			onClick:function(node){
				if(node.attributes){
					openTab(node.text,node.attributes.url);
				}
			}
		});
	
		function openTab(text,url){
			if($("#tabs").tabs('exists',text)){
				$("#tabs").tabs("select",text);
			}else{
				var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"></iframe>";		
				$("#tabs").tabs('add',{
					title:text,
					content:content,
					closable:true
				});
			}
		};
		
		/* function unload(){
			$.post("unload");
		} */
	})
</script>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:175px;background-color:#CADEE5" overflow="hidden">
			<div><img  src="images/btlbg.jpg" style="float: left;"></div>
			<div style="margin-top:145px;margin-right: 0px;"><font color="black" style="font-family: '微软雅黑'" size="3">当前用户：</font><font color="red" style="font-family: '楷体'" size="3">${currentuser.userName }</font>&emsp;&emsp;&emsp;&emsp;&emsp;<a href="http://localhost:8080/StuManage/login.jsp"><font color="black" style="font-family: '楷体'" size="3">退出系统</font></a></div>
		</div>
		<div data-options="region:'west',split:true" style="width:190px" title="导航菜单" >
			<ul id=tree></ul>
		</div>
		<div data-options="region:'center'" >
			<div class="easyui-tabs" fit="true" border="false" id="tabs">
				<div title="首页" align="center" style="padding-top: 130px"><font color="red" size="12px">欢迎</font></div>
			</div>
		</div>
		<div data-options="region:'south',split:true" style="height:50px" align="center">
			<font  color="red" size="3px" >版权所有</font>
		</div>
</body>
</html>
