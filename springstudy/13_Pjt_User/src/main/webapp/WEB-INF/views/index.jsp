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
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<body>
	
	<!-- 로그인이 안 된 상태 -->
	<c:if test="${loginUser == null}">
		<a href="${contextPath}/user/agree">회원가입 페이지</a>
		<a href="${contextPath}/user/login/form">로그인 페이지</a>
	</c:if>
	
	<!-- 로그인이 된 상태 -->
	<c:if test="${loginUser != null}">
		<div>
			<a href="${contextPath}/user/check/form">${loginUser.name}</a>님 반갑습니다.
		</div>
		<a href="${contextPath}/user/logout">로그아웃</a>
		<a id="link_retire" href="${contextPath}/user/retire">회원탈퇴</a>
		<script>
			$('#link_retire').click(function(event){
				if(confirm('탈퇴하시겠습니까?') == false) {
					event.preventDefault(); // 해당 기본 이벤트를 막아버림 (a태그의 기본 이벤트인 href 속성 실행을 막음)
					return;
				}
			});
		</script>
	</c:if>
</body>
</html>