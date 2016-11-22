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
<title>档案管理——档案列表</title>
<%@ include file="../public/commons.jspf"%>
</head>

<body>
	<div id="Title_bar_Head">
		<div id="Title">
			档案列表<s:a action="document_addUI" namespace="/document">——新建档案</s:a>
		</div>
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
					<td >联系电话</td>
					<td >联系地址</td>
					<td >相关操作</td>
					<td >档案位置</td>
					<td >档案状态</td>
					<td >档案备注</td>
					<td >档案时间</td>
					
				</tr>
			</thead>
			<tbody id="TableData" class="dataContainer">
				<s:iterator value="recordList" var="x" status="status">
					<tr class="TableDetail1 template">
						<td><s:property value="#status.index+1" />&nbsp;</td>
						<td><s:property value="dnumber" />&nbsp;</td>
						<td><s:property value="pnumber" />&nbsp;</td>
						<td><s:property value="name" />&nbsp;</td>
						<td><s:property value="phone" />&nbsp;</td>
						<td><s:property value="address" />&nbsp;</td>
							
					<s:if test="#x.state == '在档'">
						<td>
							<s:a action="document_editUI?id=%{id}" namespace="/document">修改</s:a>&nbsp;
							<s:a action="document_drawUI?id=%{id}"  namespace="/document">提档</s:a>&nbsp;
							<s:a action="document_borrowUI?id=%{id}"  namespace="/document">借档</s:a>&nbsp;
							<s:a action="document_delete?id=%{id}" onclick="return confirm('确定要删除吗？')" namespace="/document">删除</s:a>&nbsp;
							
						</td>
					</s:if>
					<s:elseif test="#x.state == '提档'">
						<td>
							<s:a action="document_resave?id=%{id}" onclick="return confirm('确定要重存吗？')" namespace="/document">重存</s:a>&nbsp;
							<s:a action="document_delete?id=%{id}" onclick="return confirm('确定要删除吗？')" namespace="/document">删除</s:a>&nbsp;
						</td>
					</s:elseif>
					<s:elseif test="#x.state == '借档'">
						<td>
							<s:a action="document_editUI?id=%{id}" namespace="/document">修改</s:a>&nbsp;
							<s:a action="document_return?id=%{id}" onclick="return confirm('确定要还档吗？')" namespace="/document">还档</s:a>&nbsp;
							<s:a action="document_delete?id=%{id}" onclick="return confirm('确定要删除吗？')" namespace="/document">删除</s:a>&nbsp;
						</td>
					</s:elseif>
					<s:else>
						<td>&nbsp;</td>
					</s:else>
					<td><s:property value="place" />&nbsp;</td>
					<td><s:property value="state" />&nbsp;</td>
					<td><s:property value="remark" />&nbsp;</td>
					<td><s:date name="time" format="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
	<s:form action="document_list" method="post" namespace="/document">
		<s:hidden name="pageNum" value="1"></s:hidden>
		关键词：<s:textfield name="query" label="查询内容" />&nbsp;&nbsp;
				<s:submit value="查询" />&nbsp;&nbsp;
				每页显示<s:select list="{'10','50','80'}" name="pageSize"></s:select>条记录
	<%@ include file="../public/pageView.jspf"%>
</s:form>
</body>
</html>
