<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
	<h1>s _ project</h1>
	
	<div style="text-align: right;">
		<a href="${contextPath }/index">HOME</a>
		<c:if test="${ login != null }">
			<a href="${contextPath }/member/logout">LOGOUT</a>
		</c:if>
		<c:if test="${ login == null }">
			<a href="${contextPath }/member/login">LOGIN</a>
		</c:if>
		<a href="${contextPath }/member/list">MEMBERS</a>
		<a href="${contextPath }/board/boardAllList">board</a>
	</div>
	<hr>
</body>
</html>