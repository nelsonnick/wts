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
<title>导航菜单</title>
<%@ include file="../public/commons.jspf"%>
<link type="text/css" rel="stylesheet" href="style/blue/menu.css" />
<script type="text/javascript">
		function menuClick(menu){
				$(menu).next().toggle();
			}
</script>
</head>
<body style="margin: 0">
	<div id="Menu">
		<ul id="MenuUl">
			<s:iterator value="#application.topPrivilegeList">
				<s:if test="#session.user.hasPrivilegeByName(name)">
					<li class="level1"><div onClick="menuClick(this);"
							class="level1Style"><img src="style/images/MenuIcon/FUNC20024.gif" class="Icon" />${name}</div>
						<ul style="" class="MenuLevel2">
							<s:iterator value="children">
								<s:if test="#session.user.hasPrivilegeByName(name)">
									<li class="level2">
										<div class="level2Style">
										<img src="style/images/MenuIcon/menu_arrow_single.gif" />
											<a target="right"
												href="${pageContext.request.contextPath}${url}.action">${name}</a>
										</div>
									</li>
								</s:if>
							</s:iterator>
						</ul></li>
				</s:if>
			</s:iterator>
		</ul>
	</div>
</body>
</html>
