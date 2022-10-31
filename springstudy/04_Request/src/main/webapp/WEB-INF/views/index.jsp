<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>

	<h1>쏴리질럿~!</h1>
	<img src="${contextPath}/resources/images/hooray.jpg" width="200px">
	
	<hr>
	
	<h3>Member 관련 요청</h3>
	
	<div>
		<a href="${contextPath}/member/detail1?id=admin&pw=1234">Detail View</a> <!-- 파라미터가 있으므로 GET방식 -->
	</div>
	
	<div>
		<input type="button" value="전송" id="btn">
	</div>
	<script>
		$('#btn').click(function(){
		// location.href='${contextPath}/member/detail2?id=admin&pw=1234';
		// location.href='${contextPath}/member/detail2 //파라미터 없는 값
		// location.href='${contextPath}/member/detail3?id=admin&pw=1234';
        location.href='${contextPath}/member/detail3?';   // 전달되지 않고 null 전달
		});
	</script>
	
	<form action="${contextPath}/member/detail4" method="post">
      	<div>
         	<input type="text" name="id" placeholder="아이디">
      	</div>
      	<div>
         	<input type="password" name="pw" placeholder="패스워드">
      	</div>
      	<button>submit</button>
   	</form>

	<hr>
	
	<div>
		<a href="${contextPath}/board/detail1?title=notice&hit=10">전송</a>
	</div>
	
		
</body>
</html>