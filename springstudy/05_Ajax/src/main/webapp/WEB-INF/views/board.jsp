<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="styleseet" href="${contextPath}/resources/css/board.css">
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>
	
	$(document).ready(function(){
		fn_ajax1();
		fn_ajax2();
		fn_ajax3();
	});
	
	function fn_ajax1() {
		$('#btn1').click(function() {
			
			$('#result').empty();
			
			$.ajax({
				/* request */
				type: 'get',
				url : '${contextPath}/board/detail1',
				data: $('#frm_board').serialize(),
				/* response */
				dataType: 'json',
				success : function(resData) {
					$('<ul>') // jquery로 $('<ul>')를 하면 ul을 만든다.
					.append( $('<li>').text(resData.title))
					.append( $('<li>').text(resData.content))
					.appendTo('#result'); // result에 넣을 때 (앞에가 자식(ul), 뒤에가 부모(result))
				},
				error : function(jqXHR) {
					$('#result').text(jqXHR.status); // text가 아니라 코드를 보내는 것 (응답코드 200, 400, 500 등)
				}
				
			});
			
		});
	} 
	function fn_ajax2() {
		$('#btn2').click(function() {
			
			$('#result').empty();
			
			$.ajax({
				/* request */
				type: 'get',
				url : '${contextPath}/board/detail2',
				data: $('#frm_board').serialize(),
				/* response */
				dataType: 'json',
				success : function(resData){
					$('<ul>')
					.append($('<li>').text(resData.title))
					.append($('<li>').text(resData.content))
					.appendTo('#result');
				},
				error : function(jqXHR) {
					if(jqXHR.status == 500) {
						alert('호에에엥.');
					}
				}
				
			});
			
		});
	} 
	function fn_ajax3() {
		$('#btn3').click(function() {
			
			$('#result').empty();
			
			$.ajax({
				/* request */
				type: 'get',
				url : '${contextPath}/board/detail3',
				data: $('#frm_board').serialize(),
				/* response */
				dataType: 'json',
				success : function(resData){
					$('<ul>')
					.append($('<li>').text(resData.title))
					.append($('<li>').text(resData.content))
					.appendTo('#result');
				},
				error : function(jqXHR){
					if(jqXHR.status == 500) {
						alert('호에에엥.');
					}
				}
				
			});
			
		});
	} 
	
	
</script>
</head>
<body>
	
	<form id="frm_board">
		<div>
			<label for="title">제목</label>
			<input type="text" name="title" id="title">
		</div>
		<div>
			<label for="content">내용</label>
			<input type="text" name="content" id="content">
		</div>
		<div>
			<input type="button" value="전송1" id="btn1">
			<input type="button" value="전송2" id="btn2">
			<input type="button" value="전송3" id="btn3">
		</div>
	</form>
	
	<hr>
	
	<%-- 결과 뿌려지는 곳 --%>
	<div id="result"></div>
	
</body>
</html>