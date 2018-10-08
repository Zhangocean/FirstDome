<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>宿舍信息管理系统</title>
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
	<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		var url;
		function searchDorm(){
				//alert($("#s_dorm").val());
			$("#dg").datagrid('load',{
				dormName:$("#s_dorm").val(),
			})
		}
		
		function deleteDorm(){
			var selectedRows=$("#dg").datagrid('getSelections');
			//alert(selectedRows);
			if(selectedRows.length==0){
				$.messager.alert("系统提示","请选择要删除的数据！");
				return;
			}
			var strIds=[];
			for(var i=0;i<selectedRows.length;i++){
				strIds.push(selectedRows[i].dormId);
			}
			var ids=strIds.join(",");
			$.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
				if(r){
					$.post("dormDelete",{delIds:ids},function(result){
						if(result.success){
							$.messager.alert("系统提示","您已成功删除<font color=red>"+result.deleteNum+"</font>条数据!");
							$("#dg").datagrid("reload");
						}else{
							$.messager.alert("系统提示",result.errorMsg);
						}
					},"json");	
				}
			})	
		}
		
		function openDormAddDialog(){
			$("#dlg").dialog('open').dialog('setTitle',"添加宿舍信息");
			url="dormSave";
		}
		
		function openDormModifyDialog(){
			var selectedRows=$("#dg").datagrid('getSelections');
			if(selectedRows.length!=1){
				$.messager.alert("系统提示","请选择1条数据！");
				return;
			}
			var row=selectedRows[0];
			$("#dlg").dialog('open').dialog('setTitle',"编辑宿舍信息");
			$("#fm").form('load',row);
			url="dormSave?dormId="+row.dormId;
		}
		
		function closeDialog(){
			$("#dlg").dialog('close');
			cleanDialog();
		}
		
		function cleanDialog(){
			$("#fm").form('clear');
			/* $("#studentName").val("");
			$("#sex").val("女");
			$("#age").val("");
			$("#phone").val("");
			$("#description").val(""); */
		}
		
		function save(){
			//alert("hello hello");
			$("#fm").form('submit',{
				url:url,
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				success:function(result){
					if(result.errorMsg){
						$.messager.alert("系统提示","result.errorMsg");
					}else{
						$.messager.alert("系统提示","保存成功！");
						cleanDialog();
						$("#dlg").dialog('close');
						$("#dg").datagrid("reload");
					}
				}
			});
		}
	</script>
</head>
<body style="margin:3px">
	<table id="dg" class="easyui-datagrid" title="宿舍信息管理" rownumbers="true"
		pagination="true" fitColumns="true" toolbar="#toolbar" url="dormList" fit="true" singleSelected="true" >
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				
				<th field="dormName" width="30">宿舍名称</th>
				<th field="dormDes" width="50">宿舍描述</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="javascript:openDormAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:openDormModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		<a href="javascript:deleteDorm()" class="easyui-linkbutton" iconCls="icon-remove" plain="true" >删除</a>
		<div>&nbsp;&nbsp;宿舍查询：&nbsp;&nbsp;<input type="text" width="20" name="s_dorm" id="s_dorm"><a href="javascript:searchDorm()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
	</div>
	
	<div id="dlg"  class="easyui-dialog" style="width:400px;height: 280px;padding:10px 20px" closed="true" buttons="#dlg-buttons" >
		<form id="fm" method="post" >
			<table>
				<tr>
					<td >宿舍名称：</td>
					<td ><input type="text" name="dormName" id="dormName" size="33" class="easyui-validatebox" required="true"  /></td>
				</tr>
				<tr>
					<td valign="top">宿舍描述：</td>
					<td><textarea rows="8" cols="35" name="dormDes" id="dormDes" class="easyui-validatebox" required="true"></textarea></td>
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