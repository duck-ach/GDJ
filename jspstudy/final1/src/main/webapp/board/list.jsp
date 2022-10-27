<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function(){
		
		$('#btn_add').click(function(event){
			location.href = "${contextPath}/board/write.do";
		});
		
	});
</script>
</head>
<body>
	<table border="1">
		<caption>총 게시글 : ${count}개</caption>
		<thead>
			<tr>
				<td>순번</td>
				<td>작성자</td>
				<td>제목</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty boards}">
				<tr>
					<td colspan="4">게시물이 없습니다.</td>
				</tr>
				<tr>
					<td>
						<button id="btn_add">새글작성</button>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty boards}">
				<c:forEach items="${boards}" var="board">
					<tr>
						<td>${board.boardNo}</td>
						<td>${board.writer}</td>
						<td><a href="${contextPath}/board/detail.do?boardNo=${board.boardNo}">${board.title}</a></td>
						<td>${board.writeDate}</td>		
					</tr>
				</c:forEach>
					<tr>
						<td>
							<button id="btn_add">새글작성</button>
						</td>
					</tr>
			</c:if>
		</tbody>
	</table>
</body>
</html>