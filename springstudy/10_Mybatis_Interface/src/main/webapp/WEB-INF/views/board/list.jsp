<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>
	
	$(document).ready(function(){
		
	});
	
</script>
</head>
<body>
	
	<div>
		<a href="${contextPath}/brd/write">새글작성</a> <!-- brd/write라는것은 controller로 보내기위함일 뿐 -->
	</div>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>글번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${boards}" var="board"> <!-- items : 서버에서 받아오는 값들의 이름만큼 반복. var : 각각의 이름 -->
					<tr>
						<td>${board.boardNo}</td>
						<td><a href="${contextPath}/brd/detail?board_no=${board.boardNo}">${board.title}</a></td>
						<td>${board.writer}</td>
						<td>${board.createDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	
	
	

</body>
</html>