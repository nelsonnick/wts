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
<title>档案管理——借档列表</title>
<%@ include file="../public/commons.jspf"%>
</head>

<body>
<div id="Title_bar_Head">
		<div id="Title">借档列表</div>
	</div>
	<div id="MainArea">
		<table class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr id="TableTitle">
					<td >序号</td>
					<td >档案编号</td>
					<td >证件号码</td>
					<td >人员姓名</td>
					<td >借档时间</td>
					<td >借档单位</td>
					<td >操作人员</td>
				</tr>
			</thead>
			<tbody id="TableData" class="dataContainer">
				<s:iterator value="recordList" var="x" status="status">
					<tr class="TableDetail1 template">
						<td><s:property value="#status.index+1" />&nbsp;</td>
						<td><s:property value="dnumber" />&nbsp;</td>
						<td><s:property value="pnumber" />&nbsp;</td>
						<td><s:property value="name" />&nbsp;</td>
						<td><s:date name="time" format="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
						<td><s:property value="company" />&nbsp;</td>	
						<td><s:property value="user" />&nbsp;</td>	
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
	<s:form action="document_borrow_list" method="post" namespace="/document">
		<s:hidden name="pageNum" value="1"></s:hidden>
		关键词：<s:textfield name="query" label="查询内容" />&nbsp;&nbsp;
				<s:submit value="查询" />&nbsp;&nbsp;
				每页显示<s:select list="{'10','50','80'}" name="pageSize"></s:select>条记录
	<%@ include file="../public/pageView.jspf"%>
</s:form>
</body>
</html>
