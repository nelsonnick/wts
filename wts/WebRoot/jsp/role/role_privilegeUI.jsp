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
<title>角色管理——配置权限</title>
<%@ include file="../public/commons.jspf"%>
<script language="javascript"
	src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.js"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/file.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/script/jquery_treeview/jquery.treeview.css" />
<script type="text/javascript">
	$(function() {
		// 指定事件处理函数
		$("[name=privilegeIds]").click(function() {

			// 当选中或取消一个权限时，也同时选中或取消所有的下级权限
			$(this).siblings("ul").find("input").attr("checked", this.checked);

			// 当选中一个权限时，也要选中所有的直接上级权限
			if (this.checked == true) {
				$(this).parents("li").children("input").attr("checked", true);
			}

		});
	});
</script>
</head>
<body>
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title">角色管理——配置权限</div>
		</div>
	</div>
	<div id=MainArea>

		<s:form action="role_privilege" method="post" namespace="/role">
			<s:hidden name="id"></s:hidden>

			<div class="ItemBlock_Title1">
				<!-- 信息说明 -->
				<div class="ItemBlock_Title1">
					<img border="0" width="4" height="7"
						src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
					正在为${name}配置权限
				</div>
			</div>

			<!-- 表单内容显示 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<table class="mainForm">
						<thead>
							<tr align="LEFT" valign="MIDDLE" id="TableTitle">
								<td width="300px" style="padding-left: 7px;">
									<!-- 如果把全选元素的id指定为selectAll，并且有函数selectAll()，就会有错。因为有一种用法：可以直接用id引用元素 -->
									<input type="checkbox" id="cbSelectAll"
									onClick="$('[name=privilegeIds]').attr('checked', this.checked)" />
									<label for="cbSelectAll">全选</label>
								</td>
							</tr>
						</thead>

						<tbody id="TableData">
							<tr class="TableDetail1">
								<td>
									<ul id="tree">
										<s:iterator value="#application.topPrivilegeList">
											<li><input type="checkbox" name="privilegeIds"
												value="${id}" id="cb_${id}"
												<s:property value="%{id in privilegeIds ? 'checked' : ''}"/> />
												<label for="cb_${id}"><span class="folder">${name}</span></label>
												<ul>
													<s:iterator value="children">
														<li><input type="checkbox" name="privilegeIds"
															value="${id}" id="cb_${id}"
															<s:property value="%{id in privilegeIds ? 'checked' : ''}"/> />
															<label for="cb_${id}"><span class="folder">${name}</span></label>
															<ul>
																<s:iterator value="children">
																	<li><input type="checkbox" name="privilegeIds"
																		value="${id}" id="cb_${id}"
																		<s:property value="%{id in privilegeIds ? 'checked' : ''}"/> />
																		<label for="cb_${id}"><span class="folder">${name}</span></label>
																	</li>
																</s:iterator>
															</ul></li>
													</s:iterator>
												</ul></li>
										</s:iterator>
									</ul>


								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<script language="javascript">
				$("#tree").treeview();
			</script>

			<!-- 表单操作 -->
			<div id="InputDetailBar">
				<input type="image" src="${pageContext.request.contextPath}/style/images/save.png" /> 
				<a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png" /></a>
			</div>
		</s:form>
	</div>
</body>
</html>
