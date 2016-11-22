<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>OA</title>
<%@ include file="../public/commons.jspf"%>
<LINK href="${pageContext.request.contextPath}/style/blue/top.css"
	type=text/css rel=stylesheet>

<script type="text/javascript">
	
</script>
<style type="text/css">
#messageArea {
	color: white;
	font-size: 14px;
	font-weight: bold;
}
</style>
</head>

<body CLASS=PageBody leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
	<div id="Head1">
		<div id="Logo">
			<a id="msgLink" href="javascript:void(0)"></a> <font color="#0000CC"
				style="color:#F1F9FE; font-size:28px; font-family:Arial Black, Arial">槐荫区职业介绍人事档案管理系统</font>
		</div>
		<div id="Head1Right">
			<div id="Head1Right_UserName">
				<img border="0" width="13" height="14" src="${pageContext.request.contextPath}/style/images/top/user.gif" />您好，<b>
					<s:a target="right" action="user_changeUI" namespace="/user">${user.name }</s:a></b>
			</div>
		</div>
		<div id="Head1Right_SystemButton">
			<a href="${pageContext.request.contextPath}/user/user_logout.action" target="_parent"> 
			<img width="78" height="20" alt="退出系统" src="${pageContext.request.contextPath}/style/blue/images/top/logout.gif" /></a>
			<a href="javascript: window.parent.right.location.reload(true);">刷新</a>
		</div>
	</div>
</html>
