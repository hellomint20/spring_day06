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

	<h1 style="text-align: center;">게시판</h1>
	
	<form action="writeSave" method="post" enctype="multipart/form-data">
		<b>작성자</b><br>
		<input type="text" readonly name="id" value="${ login }"><hr>
		
		<b>제목</b><br>
		<input type="text" name="title"><hr>
		
		<b>내용</b><br>
		<textarea rows="5" cols="20" name="content"></textarea><hr>
		
		<b>이미지 파일 첨부</b><br>
		 <input type="file" name="image_file_name" onchange="readURL(this)"><br>
		<img id="preview" alt="선택된 이미지가 없습니다" width="100" height="100"><hr>
		
		<input type="submit" value="글쓰기">
		<a href="boardAllList">목록 이동</a>
	</form>
</body>
</html>