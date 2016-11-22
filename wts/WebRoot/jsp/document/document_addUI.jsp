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
<title>档案管理——档案信息</title>
<%@ include file="../public/commons.jspf"%>
<script type="text/javascript">  
$(document).ready(function() {
		$("body").bind("click", check);
	});
	function check() {
		var pnumber = $("#pnumber").text();
		var pname = $("#pname").text();
		var phone = $("#phone").text();		
		var dnumber = $("#dnumber").text();		
		if (pnumber == " 可以使用！" && pname == " 可以使用！" && phone == " 可以使用！" && dnumber == " 可以使用！") {
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
        <div id="Title">档案管理——录入基本信息
        </div>
    </div>
</div>
<div id="MainArea">
    <s:form action="document_add"  method="post" namespace="/document">
    	<s:hidden name="id"></s:hidden>
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table class="mainForm">
                	<tr>
                        <td >档案编号</td>
                        <td><s:textfield name="dnumber" cssClass="InputStyle" maxlength="8" id="dnumberid" /><span id="dnumber"> *必填</span></td>
                    </tr>
                    <tr>
                        <td >人员姓名</td>
                        <td><s:textfield name="name" cssClass="InputStyle"  id="pnameid" /><span id="pname"> *必填</span></td>
                    </tr>
                    <tr>
                        <td >证件号码</td>
                        <td><s:textfield name="pnumber" cssClass="InputStyle" maxlength="18" id="pnumberid" /><span id="pnumber"> *必填</span></td>
                    </tr>
                    <tr>
                        <td >联系电话</td>
                        <td><s:textfield name="phone" cssClass="InputStyle"  maxlength="11" id="phoneid" /><span id="phone"> *必填</span></td>
                    </tr>
                    <tr>
                        <td >联系地址</td>
                        <td><s:textfield name="address" cssClass="InputStyle"  id="addressid" /><span id="address"> *必填</span></td>
                    </tr>
                    <tr>
                        <td >档案备注</td>
                        <td><s:textarea name="remark" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png" id="save"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>
</body>
</html>
