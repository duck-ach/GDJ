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
	<script>
		$('#triple').click(function(){
			$('form').attr("action", ${contextPath}"/triple.do");
			$('#frm').submit();
		});
		$('#square').click(function(){
			$('form').attr("action", ${contextPath}"/square.do");
			$('#frm').submit();
		})
	</script>

	<%-- 너비/높이 입력 폼 : 삼각형 버튼, 사각형 버튼 --%>
	
	<div>
		<form id=frm>
			<div>
				가로<input type="text" name="width"> &nbsp;
				세로<input type="text" name="height">
				<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="triple">삼각형 넓이 확인</button>
				<button id="square">사각형 넓이 확인</button>
			</div>
		</form>
	</div>	
	
<hr>
	<%-- 반지름 입력 폼 : 원 버튼 --%>
	<div>
		<form action="${contextPath}/circle.do">
			<div>
				반지름<input type="text" name="radius"> &nbsp;
				<button>원 넓이 확인</button>
			</div>
		
		</form>
	</div>
	
</body>
</html>