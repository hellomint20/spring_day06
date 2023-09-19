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
	<%@ include file="../default/header.jsp" %>
	
	<h1 style="text-align: center;">게시판</h1>
	
	<table border="1">
		<tr>
			<th>번호</th> <th>아이디</th> <th>제목</th> <th>날짜</th> <th>조회수</th> <th>image_file_name</th>
		</tr>
		<c:choose>
			<c:when test="${ list.size() == 0 }">
				<tr>
					<th colspan="6">작성된 글이 없습니다</th>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="dto" items="${ list }">
					<tr>
						<td>${ dto.writeNo }</td>
						<td>${ dto.id }</td>
						<td><a href="content_view?writeNo=${ dto.writeNo }">${ dto.title }</a></td>
						<td>${ dto.saveDate }</td>
						<td>${ dto.hit }</td>
						<td>${ dto.imageFileName }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="6">
				<c:forEach var="n" begin="1" end="${repeat }">
					<a href="boardAllList?num=${ n }">${ n }</a>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td style="text-align: right;" colspan="6"><a href="writeForm">글작성</a></td>
		</tr>
	</table>
</body>
</html>