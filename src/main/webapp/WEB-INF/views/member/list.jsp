<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../default/header.jsp" %>
<h3>list page</h3>

<table border="1" style="text-align: center;">
	<tr>
		<th>이름</th> <th>비밀번호</th> <th>주소</th>
	</tr>
	<c:forEach var="dto" items="${ list }">
		<tr>
			<td><a href="info?id=${ dto.id }">${ dto.id }</a></td> <td>${ dto.pw }</td> <td>${ dto.addr }</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>