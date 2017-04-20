<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>mybatis pagehelper分页</title>
<link rel="stylesheet" type="text/css" href="resources/css/index.css" />
<!-- <script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/index.js"></script> -->
</head>
<body>
	<%-- <h2>Hello World!</h2>
用户名： ${user.userName}<br>
 密码：${user.userPassword}<br> --%>

	${page.pageNum}
	<br /> ${page.pageSize}
	<br /> ${page.list}
	<br />

	<table>
		<tr>
			<td>编号</td>
			<td>姓名</td>
			<td>密码</td>
		</tr>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.userId}</td>
				<td>${user.userName}</td>
				<td>${user.userPassword}</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<!-- 页数 -->
	<div id="area_div">
		共${page.total}条记录，当前显示第${page.pageNum}/${page.pages}页<br />
		<c:if test="${!page.isFirstPage}">
			<li><a
				href="list?pageNum=${page.firstPage}&pageSize=${page.pageSize}">首页</a></li>
			<li><a
				href="list?pageNum=${page.prePage}&pageSize=${page.pageSize}">上一页</a></li>
		</c:if>

		<c:forEach items="${page.navigatepageNums}" var="navigatepageNum">
			<%-- <c:if test="${navigatepageNum==page.pageNum}">
				<li><a
					href="list?pageNum=${navigatepageNum}&pageSize=${page.pageSize}"></a></li>
			</c:if> --%>
			<c:if test="${navigatepageNum!=page.pageNum}">
				<li><a
					href="list?pageNum=${navigatepageNum}&pageSize=${page.pageSize}">${navigatepageNum}</a></li> 
			</c:if>
		</c:forEach>

		<c:if test="${!page.isLastPage}">
			<li><a
				href="list?pageNum=${page.nextPage}&pageSize=${page.pageSize}">下一页</a></li> 
			<li><a
				href="list?pageNum=${page.lastPage}&pageSize=${page.pageSize}">最后一页</a></li> 
		</c:if>
	</div>
</body>
</html>