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
<title>档案管理——档案提取</title>
<%@ include file="../public/commons.jspf"%>
</head>
<body>
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title">档案管理——档案提取
        </div>
    </div>
</div>
<div id="MainArea">
    <s:form action="document_draw"  method="post" namespace="/document">
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table class="mainForm">
               		<tr>
                        <td >档案编号</td>
                        <td>${dnumber}</td>
                    </tr>
                 	<tr>
                        <td >证件号码</td>
                        <td>${pnumber}</td>
                    </tr>
                  	<tr>
                        <td>姓名</td>
                        <td>${name}</td>
                    </tr>
                    <tr>
                        <td >联系电话</td>
                        <td>${phone}</td>
                    </tr>
                    <tr>
                        <td >联系地址</td>
                        <td>${address}</td>
                    </tr>
                    <tr>
                        <td >提档单位</td>
                        <td><s:textfield name="cname" cssClass="InputStyle" /> *必填</td>
                    </tr>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png" onclick="return confirm('确定为该人员办理提档吗？')"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>
</body>
</html>
