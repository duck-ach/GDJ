<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.board_no}번 게시물</title>
</head>
<body>

	<h1>게시글 상세 보기</h1>
	<div>
		게시글 번호 : ${board.board_no}
	</div>
	<div>
		게시글 제목 : ${board.title}
	</div>
	<div>
		<pre>${board.content}</pre>
	</div>
	<div>
		작성일자 : ${board.create_date}
	</div>
	<div>
		<input type="button" value="수정" id="btn_modify">
		<input type="button" value="삭제" id="btn_remove">
		<input type="button" value="목록" id="btn_list">
	</div>
	
</body>
</html>