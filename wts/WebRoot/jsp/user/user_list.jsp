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
<title>用户管理——用户列表</title>
<%@ include file="../public/commons.jspf"%>
</head>
<body>
	<div id="Title_bar_Head">
		<div id="Title">用户管理<s:a action="user_addUI" namespace="/user">——新增用户</s:a></div>
	</div>
	<div id="MainArea">
		<table class="TableStyle">
			<thead>
				<tr id="TableTitle">
					<td >序号</td>
					<td >真实姓名</td>
					<td >登陆名称</td>
					<td >激活状态</td>
					<td >相关操作</td>
				</tr>
			</thead>
			<tbody id="TableData" class="dataContainer">
				<s:iterator value="recordList" var="x" status="status">
					<tr class="TableDetail1 template">
						<td><s:property value="#status.index+1" />&nbsp;</td>
				<td><s:property value="name" />&nbsp;</td>
				<td><s:property value="account" />&nbsp;</td>
				<td><s:property value="active" />&nbsp;</td>
				<s:if test="#x.active =='激活'">
					<td><s:a action="active_no?id=%{id}" namespace="/user">停用</s:a>&nbsp;
						<s:a action="user_roleUI?id=%{id}" namespace="/user">设置角色</s:a>&nbsp;
						<s:a action="user_reset?id=%{id}" onclick="return confirm('确定要重置密码吗？')" namespace="/user">密码重置</s:a>&nbsp;
						<s:a action="user_delete?id=%{id}" onclick="return confirm('确定要删除吗？')" namespace="/user">删除</s:a>&nbsp;
					</td>
				</s:if>
				<s:else>
					<td><s:a action="active_yes?id=%{id}" namespace="/user">启用</s:a>&nbsp;
						<s:a action="user_roleUI?id=%{id}" namespace="/user">设置角色</s:a> &nbsp;
						<s:a action="user_reset?id=%{id}" onclick="return confirm('确定要重置密码吗？')" namespace="/user">密码重置</s:a>&nbsp;
						<s:a action="user_delete?id=%{id}" onclick="return confirm('确定要删除吗？')" namespace="/user">删除</s:a>&nbsp;
					</td>
				</s:else>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
	<s:form action="user_list" method="post" namespace="/user">
		<s:hidden name="pageNum" value="1"></s:hidden>
		关键词：<s:textfield name="query" label="查询内容" />&nbsp;&nbsp;
				<s:submit value="查询" />&nbsp;&nbsp;
				每页显示<s:select list="{'10','50','80'}" name="pageSize"></s:select>条记录
	<%@ include file="../public/pageView.jspf"%>
</s:form>
	
</body>
</html>
