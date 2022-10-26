<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body> <!-- application session request  -->
<c:if test="${login == null}"> <!-- 회원가입이 안되어있다면 login page를 보여주겠다. -->
	<div>
		<form method="post" action="${contextPath}/member/login.me">
			<div>
				<input type="text" name="id" placeholder="아이디">
			</div>
			<div>
				<input type="password" name="pw" placeholder="패스워드">
			</div>
			<div>
				<button>로그인</button>
			</div>
		</form>
	</div>
</c:if>
<c:if test="${login != null}"> <!-- 회원가입이 되어있다면 OO님 어서오세요 페이지를 보여준다. -->
	<div>
		${login.name}님 어서오세요
		<input type="button" value="로그아웃" onclick="location.href='${contextPath}/member/logout.me';">
	</div>
</c:if>

</body>
</html>