<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
	
	<div>아이디 ${member.id}</div> 	<%-- ${member.id}는 member.getId()를 호출한다. --%>
	<div>비밀번호 ${member.pw}</div>	<%-- ${member.pw}는 member.getPw()를 호출한다. --%>

</body>
</html>