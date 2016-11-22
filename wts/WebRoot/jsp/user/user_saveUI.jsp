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
<title>用户管理——用户保存</title>
<%@ include file="../public/commons.jspf"%>
<script type="text/javascript">  
$(document).ready(function() {
		$("body").bind("click", check);
	});
	function check() {
		var uname = $("#uname").text();	
		if (uname == " 可以使用！") {
			$("#save").show();
		} else {
			$("#save").hide();
		}
	}
</script>
</head>

<body>
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title">用户管理——用户信息
        </div>
    </div>
</div>
	<div id=MainArea>
			<s:form action="user_%{id == null ? 'add' : 'edit'}" method="post" namespace="/user">
			<s:hidden name="id"></s:hidden>
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table class="mainForm">
						<tr>
							<td>登录名</td>
							<td><s:textfield name="account" cssClass="InputStyle" maxlength="20" id="accountid" /><span id="account"> *必填</span></td>
						</tr>
						 <tr>
                        <td >姓名</td>
                        <td><s:textfield name="name" cssClass="InputStyle" maxlength="6" id="unameid" /><span id="uname"> *必填</span></td>
                    </tr>
					</table>
				</div>
			</div>
			<!-- 表单操作 -->
			<div id="InputDetailBar">
				<input type="image" src="${pageContext.request.contextPath}/style/images/save.png" id="save"/>
				<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png" /></a>
			</div>
		</s:form>
	</div>
</body>
</html>
