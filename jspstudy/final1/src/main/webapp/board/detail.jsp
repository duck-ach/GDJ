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
		
		$('#btn_modify').submit(function(event){
			if($('#title').val() == ''){
				alert('제목은 필수입니다.');
				$('#writer').focus();
				event.preventDefault();
				return;
			}
		});		
		
		$('#btn_remove').click(function(event){
			if(confirm('게시글을 삭제할까요?')){
				location.href = '${contextPath}/board/remove.do?boardNo=${board.boardNo}';
			} else {
				alert('취소되었습니다.');
			}
		});
		
		$('#btn_list').click(function(event){
			location.href = '${contextPath}/board/list.do';
		});
		
	});
	
</script>
</head>
<body>
	<form id="frm_edit" action="${contextPath}/board/modify.do" method="POST">
		<table border="1">
			<tbody>
				<tr>
					<td>순번</td>
					<td>
						<label for="boardNo"></label>
						<input type="text" id="boardNo" name="boardNo" value="${board.boardNo}" readonly>
					</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>
						${board.writer}
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>
						<label for="title"></label>
						<input type="text" id="title" name="title" value="${board.title}">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<label for="content"></label>
						<textarea id="content" name="content" rows="5" cols="30">${board.content}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="수정" id="btn_modify">
						<input type="button" value="목록" id="btn_list">
						<input type="button" value="삭제" id="btn_remove">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>