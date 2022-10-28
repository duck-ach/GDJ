<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 
	<!-- 
		animal5가 request의 parameter에 filename="animal5.jpg"라고 저장되어 있기 때문에 
		param.filename라고 해서 빼서 사용할수 있다.
		${param.filename} = animal5.jpg
		속성으로 전달되었다면 el열어서 보면되고
		파라미터로 전달되었다면 param열어서 보면 된다.
	-->
	<h1>보고 싶은 동물 ${param.filename}</h1>
	<img src="${contextPath}/resources/images/${param.filename}" width="200px">

</body>
</html>