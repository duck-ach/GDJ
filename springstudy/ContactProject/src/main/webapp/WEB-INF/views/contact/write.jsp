<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연락처등록</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function(){
		
		// 목록
		$('#btn_list').click(function(){
			location.href='${contextPath}/contact/list';
		});
		
		// 등록
		$('#frm_contact').submit(function(event){
			if ($('#name').val() == '' || 
					$('#tel').val() == '' || 
					$('#addr').val() == '' ||
					$('#email').val() == '') {
					alert('필수 정보를 모두 입력하세요.');
					event.preventDefault();
					return;
				}
			alert('연락처가 등록되었습니다.');
		});
		
	});
</script>
</head>
<body>
<h1>연락처 등록</h1>
<form id="frm_contact" action="${contextPath}/card/register" method="post">
	<div>
		<label for="name">이름*</label><br>
		<input type="text" name="name" id="name" required="required">
	</div>
	<br>
	<div>
		<label for="tel">전화*</label><br>
		<input type="text" name="tel" id="tel" required="required">
	</div>
	<br>
	<div>
		<label for="addr">주소*</label><br>
		<input type="text" name="addr" id="addr" required="required">
	</div>
	<br>
	<div>
		<label for="email">이메일*</label><br>
		<input type="text" name="email" id="email" required="required">
	</div>
	<br>
	<div>
		<label for="etc">비고</label><br>
		<input type="text" name="etc" id="etc">
	</div>
	<br>
	<div>
		<button>연락처 저장하기</button>
		<input type="button" value="전체연락처" id="btn_list">
	</div>
</form>
</body>
</html>