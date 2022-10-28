<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!-- JSTL이 잘 되는 이유는 JSTL.jar 파일이 Maven Dependencies에 자동으로 내려받아져서 존재하기 때문 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<a href="${contextPath}/animal">동물보러가기</a>
<hr>
	<a href="${contextPath}/flower">꽃보러가기</a>
<hr>
	<a href="${contextPath}/animal/flower">동물보러갔다가 꽃보러가기</a>
<hr>
	<a href="${contextPath}/want/animal?filename=animal5.jpg">animal5 보러가기</a>
<hr>
	<a href="${contextPath}/response">response 동물보러가기</a>
</body>
</html>