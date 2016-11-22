<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>账号管理——用户名及密码修改</title>
	<%@ include file="../public/commons.jspf"%>
  </head>
  <body>
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title">用户管理——用户名及密码修改
        </div>
    </div>
</div>
<div id=MainArea>
    <s:form action="user_change" method="post" namespace="/user">
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table class="mainForm">
                    <tr>
                    	<td>真实姓名</td>
                        <td>${name}</td>
                    </tr>
                    <tr>
                    	<td>登录名</td>
                        <td>${account}</td>
                    </tr>
                    <tr>
                    <td>新密码</td>
                        <td><s:textfield name="passwordCheck" cssClass="InputStyle" maxlength="18" id="passwordCheckid" /> *必填</span></td>
                    </tr>
                </table>
            </div>
        </div>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png" />
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>
</body>
</html>
