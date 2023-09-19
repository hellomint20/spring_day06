<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/img_view.js"></script>

</head>
<body>
	<%@ include file="../default/header.jsp" %>

	<h1 style="text-align: center;">수정 페이지</h1>
	
	<form action="modify" method="post" enctype="multipart/form-data">
		<input type="hidden" name="imageFileName" value="${ content.imageFileName }">
		<b>글 번호</b><br>
		<input type="text" name="writeNo" readonly value="${ content.writeNo }"><hr>
		
		<b>제목</b><br>
		<input type="text" name="title" value="${ content.title }"><hr>
		
		<b>내용</b><br>
		<textarea rows="5" cols="20" name="content">${ content.content }</textarea><hr>
		
		<b>이미지 파일 첨부</b><br>
		<img id="preview" src="download?name=${ content.imageFileName }" alt="선택된 이미지가 없습니다" width="100" height="100"><br>
		<input type="file" name="file" onchange="readURL(this);"><hr>
		
		<input type="submit" value="수정">
		<input type="button" onclick="history.back()" value="이전">
		<button type="button" onclick="location.href='boardAllList'">목록</button>
	</form>
</body>
</html>