<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<a href="${contextPath}/board/detail">상세보기</a>
	<a href="${contextPath}/notice/detail">공지사항</a>
	
	<!-- 
		${contextPath}/board/detail 와 ${contextPath}/notice/detail 은 Mapping 이름이다. (*.do)와 같은 역할
		그래서 detail.do라고 하게되면 Controller에서 @GetMapping("board/detail")값에 @GetMapping("board/detail.do") 라고 해주면 된다. 
		
	-->
	
	
</body>
</html>