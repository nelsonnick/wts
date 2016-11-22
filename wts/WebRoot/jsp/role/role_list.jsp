<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>角色管理——角色列表</title>
<%@ include file="../public/commons.jspf"%>
</head>

<body>
	<div id="Title_bar_Head">
		<div id="Title">角色管理<s:a action="role_addUI" namespace="/role">——新增角色</s:a></div>
	</div>
	<div id="MainArea">
		<table class="TableStyle">
			<thead>
				<tr id="TableTitle">
					<td >序号</td>
					<td >角色名称</td>
					<td >角色描述</td>
					<td >相关操作</td>
				</tr>
			</thead>
			<tbody id="TableData" class="dataContainer" >
				<s:iterator value="recordList" var="x" status="status">
					<tr class="TableDetail1 template">
						<td><s:property value="#status.index+1" />&nbsp;</td>
						<td><s:property value="name" />&nbsp;</td>
						<td><s:property value="description" />&nbsp;</td>
						<td>
							<s:a action="role_delete?id=%{id}" onclick="return confirm('确定要删除吗？')" namespace="/role">删除</s:a> 
							<s:a action="role_editUI?id=%{id}" namespace="/role">修改</s:a> 
							<s:a action="role_privilegeUI?id=%{id}" namespace="/role">设置权限</s:a></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
	<s:form action="role_list" method="post" namespace="/role">
		<s:hidden name="pageNum" value="1"></s:hidden>
		关键词：<s:textfield name="query" label="查询内容" />&nbsp;&nbsp;
				<s:submit value="查询" />&nbsp;&nbsp;
				每页显示<s:select list="{'10','50','80'}" name="pageSize"></s:select>条记录
	<%@ include file="../public/pageView.jspf"%>
	</s:form>
	
</body>
</html>
