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
	
	<h1 style="text-align: center;">${ dto.writeNo } 게시글</h1>
	
	<table border="1">
		<tr>
			<th>글 번호</th> <td>${ dto.writeNo }</td> <th>작성자</th> <td>${ dto.id }</td>
		</tr>
		<tr>
			 <th>제목</th> <td>${ dto.title }</td> <th>등록일자</th> <td>${ dto.saveDate }</td>
		</tr>
		<tr>
			<th>내용</th> <td>${ dto.content }</td> 
			<td colspan="2">
				<a href="download?name=${ dto.imageFileName }"><img src="download?name=${ dto.imageFileName }" width="100" height="100" alt="없음"> </a>
			</td>
		</tr>
		<tr>
			<td style="text-align: center;" colspan="4">
				<c:if test="${ login = dto.id }">
					<button type="button" onclick="location.href='modify_form'">수정</button>
					<button type="button" onclick="">삭제</button>
				</c:if>
				<button type="button" onclick="">답글</button>
				<button type="button" onclick="location.href='boardAllList'">목록</button>
			</td>
		</tr>
	</table>
</body>
</html>