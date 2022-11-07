<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail.jsp창입니다.</title>
</head>
<body>
<h1>자유게시판 게시글 상세보기화면</h1>
	
	<form action="${contextPath}/free/modify.do?freeNo=${free.freeNo}" method="POST">
		작성자 ${free.writer}<br>
		작성IP ${free.ip}<br>
		조회수 ${free.hit}<br>
		제목 <input type="text" name="title" value="${free.title}"><br>
		내용<br>
		<textarea name="content" rows="5" cols="30">${free.content}</textarea>
		<br><br>
		<input type="submit" value="수정">
		<input type="button" value="목록" onclick="location.href='${contextPath}/free/list.do'">
	</form>
</body>
</html>