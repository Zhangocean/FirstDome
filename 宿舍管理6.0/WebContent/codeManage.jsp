<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生信息管理系统</title>
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
	<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
</head>
<script type="text/javascript">
	function  reset(){
		$("#ff").form('clear');
	}
	
	function submit(){
		/* if($("#oCode").val()!=#){
			$.messager.alert("系统提示","原密码输入错误")
		} */
		var o=$("#oCode").val();
		var n=$("#nCode").val();
		var rn=$("#repeatnCode").val();
		$("#ff").form('submit',{
			url:"codeModify",
			onSubmit:function(param){
			if(n!=rn){
				$.messager.alert("系统提示","两次输入密码不一致！");
				return false;
			}
			/* 	param.Code=o;
				param.nCode=n; */
			},
		success:function(result){
			//var result = eval('(' + result + ')'); 
			if(result){
				$.messager.alert("系统提示","密码修改成功！");
				$("#ff").form('clear');
			}else{
				$.messager.alert("系统提示","密码修改失败！");
				o="";
			}
		}
		})
	}
	
</script>
<body align="center">
	    <div class="easyui-panel" title="密码修改" style="width: 500px;height: 250px" fit:true >
        <div style="padding:10px 60px 20px 60px">
        <form id="ff" method="post">
            <table cellpadding="5" >
                <tr>
                    <td>原&emsp;密&emsp;码:</td>
                    <td><input class="easyui-textbox" type="text" name="oCode" id="oCode" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>新&emsp;密&emsp;码:</td>
                    <td><input class="easyui-textbox" type="text" name="nCode" id="nCode" data-options="required:true"></input></td>
                </tr>
                 <tr>
                    <td>确认新密码:</td>
                    <td><input class="easyui-textbox" type="text" name="repeatnCode" id="repeatnCode" data-options="required:true"></input></td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:submit()" class="easyui-linkbutton">确定</a>
            <a href="javascript:reset()" class="easyui-linkbutton">取消</a>
        </div>
        </div>
    </div>
</body>
</html>