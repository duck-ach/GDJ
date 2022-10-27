<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	
	$(document).ready(function(){
		
		$('#frm_add').submit(function(event){
			if($('#writer').val() == ''){
				alert('작성자의 이름을 입력하세요.');
				$('#writer').focus();
				event.preventDefault();
				return;
			}
			if($('#title').val() == ''){
				alert('제목은 필수입니다.');
				$('#writer').focus();
				event.preventDefault();
				return;
			}
		});
		
		$('#btn_list').click(function(event){
			location.href = '${contextPath}/board/list.do';
		});
		
	});
	
</script>
</head>
<body>
	<form id="frm_add" action="${contextPath}/board/add.do" method="POST">
		<table border="1">
			<tbody>
				<tr>
					<td>작성자</td>
					<td>
						<label for="writer"></label>
						<input type="text" id="writer" name="writer">
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>
						<label for="title"></label>
						<input type="text" id="title" name="title">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<label for="content"></label>
						<textarea id="content" name="content" rows="5" cols="30"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="등록">
						<input type="button" value="목록" id="btn_list">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>