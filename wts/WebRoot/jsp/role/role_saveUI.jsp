<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>角色管理——新增角色</title>
<%@ include file="../public/commons.jspf"%>
</head>
<body>
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title">角色设置
        </div>
    </div>
</div>
<div id="MainArea">
    <s:form action="role_%{id == null ? 'add' : 'edit'}"  method="post" namespace="/role">
<s:hidden name="id"></s:hidden>
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table class="mainForm">
                    <tr>
                        <td >角色名称</td>
                        <td><s:textfield name="name" cssClass="InputStyle" /> *</td>
                    </tr>
                    <tr>
                        <td>角色描述</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>
</body>
</html>
