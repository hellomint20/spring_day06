<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/daumPost.js"></script>

</head>
<body>

	<%@ include file="../default/header.jsp" %>
	<h3>info page</h3>
	id : ${ mem.dto.id }<br>
	pw : ${ mem.dto.pw }<br>
	
	<c:choose>
		<c:when test="${ mem.addr1 == null }">
			addr : ${ mem.dto.addr }
		</c:when>
		<c:otherwise>
			addr1 : ${ mem.addr1 }<br>
			addr2 : ${ mem.addr2 }<br>
			addr3 : ${ mem.addr3 }<br>
		</c:otherwise>
	</c:choose>
	
	<hr>
	<h3>modify</h3>
	<form action="modify" method="post">
		<input type="text" name="id" readonly value="${ mem.dto.id }"><br>
		<input type="text" readonly id="addr1" name="addr" value="${mem.addr1 }">
		<button type="button" onclick="daumPost()">우편번호 찾기</button><br>
		<input type="text" readonly id="addr2" name="addr" value="${mem.addr2 }"><br>
		<input type="text" id="addr3" name="addr" value="${mem.addr3 }"><br>
		<input type="submit" value="수정"><br>
	</form>

</body>
</html>