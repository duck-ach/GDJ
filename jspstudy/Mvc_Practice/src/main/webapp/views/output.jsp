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
	
	<%-- 
		사각형, 삼각형, 원 넓이를 구분해서 출력하기 
		1. 너비?, 높이?인 사각형의 넓이는 ?입니다.
		2. 너비?, 높이?인 삼각형의 넓이는 ?입니다.
		3. 반지름 ?인 원의 넓이는 ? 입니다.
	--%>
	
<c:choose>
	<c:when test="${command == square.do}">
		<h1>너비 ${width}, 높이 ${height}인 사각형의 넓이는 ${result} 입니다. </h1>
	</c:when>
	<c:when test="${command == triple.do}">
		<h1>너비 ${width}, 높이 ${height}인 삼각형의 넓이는 ${result} 입니다. </h1>
	</c:when>
	<c:when test="${command == circle.do}">
		<h1>반지름 ${radius}인 원의 넓이는 ${result} 입니다. </h1>
	</c:when>
</c:choose>

<div>
	<a href="${contextPath}/input.do">입력폼으로 가기</a>
</div>

</body>
</html>