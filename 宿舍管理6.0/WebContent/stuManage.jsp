<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生信息管理系统</title>
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
</head>
<script type="text/javascript">
	var url;
	function searchStu(){
		//alert($("#s_stuDorm").combobox('getValue'));
		//alert($("#s_stuNum").val());
		$("#dg").datagrid('load',{
			stuName:$("#s_stuName").val(),
			stuNum:$("#s_stuNum").val(),
			dormId:$("#s_stuDorm").combobox('getValue'),
			Bbirthday:$("#s_Bbirthday").datebox('getValue'),
			Ebirthday:$("#s_Ebirthday").datebox('getValue'),
			sex:$("#s_sex").combobox('getValue'),
		})
		
	}
	
	function openStuAddDialog(){
		$("#dlg").dialog('open').dialog('setTitle',"添加学生信息");
		url="stuSave";
	}
	
	function openStuModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("温馨提示！","请选择一条数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog('open').dialog('setTitle',"修改学生信息");
		$("#fm").form('load',row);
		url="stuSave?stuId="+row.stuId;
	}
	
	function closeDialog(){
		$("#dlg").dialog('close');
		cleanDialog();
	}
	
	function cleanDialog(){
		$("#fm").form('clear');
	}
	
	function save(){
		$("#fm").form('submit',{
			url:url,
			onSubmit:function(){
				if($("#sex").combobox('getValue')==""){
					$.messager.alert("系统提示","请选择性别！");
					return false;
				}
				if($("#dormName").combobox('getValue')==""){
					$.messager.alert("系统提示","请选择宿舍！");
					return false;
				}
				return $(this).form('enableValidation').form('validate');
			},
			success:function(result){
				if(result.errorMsg){
					$.messager.alert("系统提示","result.errorMsg");
				}else{
					$.messager.alert("系统提示","保存成功！");
					cleanDialog();
					$("#dlg").dialog('close');
					$("#dg").datagrid('reload');
				}
			}
		})
	}
	
	function deleteStu(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("温馨提示！","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].stuId);
		}
		var ids=strIds.join(",");
		$.messager.confirm("温馨提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("stuDelete",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("温馨提示！","您已成功删除<font color=red>"+result.deleteNum+"</font>条数据!");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示",result.errorMsg);
					}
				},"json");
			}
		});
	}
	
</script>
<body style="margin: 3px">
	<table id="dg" class="easyui-datagrid" title="学生信息管理" fit="true"
		pagination="true" url="stuList" fitColumns="true" rownumbers="true"
		toolbar="#toolbar">
		<thead>
			<tr>
				<th field="cb" checkbox="true" align="center"></th>
				<th field="stuName" width="20" align="center">学生姓名</th>
				<th field="stuNum" width="20" align="center">学号</th>
				<th field="sex" width="10" align="center">性别</th>
				<th field="dormid" width="10" align="center" hidden="true">宿舍编号</th>
				<th field="birthday" width="20" align="center">出生日期</th>
				<th field="dormName" width="20" align="center">宿舍</th>
				<th field="email" width="20" align="center">Email</th>
				<th field="stuDes" width="40" align="left">学生备注</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="javascript:openStuAddDialog()" class="easyui-linkbutton"
			iconCls="icon-add" plain="true">添加</a> <a
			href="javascript:openStuModifyDialog()" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true">编辑</a> <a
			href="javascript:deleteStu()" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true">删除</a>
		<div>
			&ensp;学号：<input type="text" name="s_stuNum" id="s_stuNum" size="10"/>
			&ensp;姓名：<input type="text" name="s_stuName" id="s_stuName" size="12"/>
			&ensp;性别：<select class="easyui-combobox" name="s_sex" id="s_sex" panelHeight='auto' editable="false">
							<option value="">请选择...</option>
							<option value="男">男</option>
							<option value="女">女</option></select>
			&ensp;宿舍：<input class="easyui-combobox" name="s_stuDorm" id="s_stuDorm" size="12"
						 data-options="panelHeight:'auto',
										 editable:false,
										 valueField:'dormId',
										 textField:'dormName',
										  url:'dormCombList'"/>
			&ensp;出生日期：<input class="easyui-datebox" name="s_Bbirthday" id="s_Bbirthday" size="11">--><input
				class="easyui-datebox" name="s_Ebirthday" id="s_Ebirthday" size="11">
		<a href="javascript:searchStu()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<div id="dlg"  class="easyui-dialog" style="width:550x;height: 350px;padding:10px 20px" closed="true" buttons="#dlg-buttons" >
		<form id="fm" method="post" >
			<table>
				<tr>
					<td >学号：</td>
					<td ><input type="text" name="stuNum" id="stuNum" class="easyui-validatebox" required="true"  /></td>
					<td >学生姓名：</td>
					<td ><input type="text" name="stuName" id="stuName" class="easyui-validatebox" required="true"  /></td>
				</tr>
				<tr>
					<td >性别：</td>
					<td ><select class="easyui-combobox" name="sex" id="sex" panelHeight='auto' editable="false" required="true" style="width: 160px">
							<option value="">请选择...</option>
							<option value="男">男</option>
							<option value="女">女</option></select></td>
					<td >出生日期：</td>
					<td ><input class="easyui-datebox" name="birthday" id="birthday" required="true"></td>
				</tr>
				<tr>
					<td >宿舍：</td>
					<td ><input class="easyui-combobox" name="dormName" id="dormName" 
						 data-options="panelHeight:'auto',
										 editable:false,
										 required:true,
										 valueField:'dormId',
										 textField:'dormName',
										 url:'dormCombList'"/></td>
					<td >Email：</td>
					<td ><input type="text" name="email" id="email" class="easyui-validatebox" required="true" validType="email" /></td>
				</tr>
				<tr>
					<td valign="top">学生备注：</td>
					<td><textarea rows="8" cols="35" name="stuDes" id="stuDes" class="easyui-validatebox" required="true"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:save()" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">取消</a>
	</div>
</body>
</html>