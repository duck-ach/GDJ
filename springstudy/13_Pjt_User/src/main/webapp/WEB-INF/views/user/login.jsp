<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
	$(function(){
		fn_login();
		fn_displayRememberId();
	});
	
	function fn_login() {
		$('#frm_login').submit(function(event){ // submit을 취소할 수 있게끔 event 객체를 잡아준다.
			
			// 아이디와 패스워드가 입력되지 않았을 경우
			if($('#id').val() == '' || $('#pw').val() == '') {
				alert('아이디와 패스워드를 모두 입력하세요');
				event.preventDefault();
				return; // 아래 if문을 막는다.
			}
		
			// 아이디 저장
			if($('#rememberId').is(':checked')) {		// Java에서 쿠키를 처리하려면 Service단에서 Cookie 클래스를 이용하여 request에 저장해주는 방식을 사용했다.
				$.cookie('rememberId', $('#id').val()); // 쿠키ID, 값 순으로 저장을 하면 편리
			} else {
				$.cookie('rememberId', '');
			}											
			
		});
	}
	
	function fn_displayRememberId() {
		// 아이디저장 쿠키 불러오기
		let rememberId = $.cookie('rememberId');
		if(rememberId == '') {
			$('#id').val('');
			$('#rememberId').prop('checked', false); // check 해제
		} else {
			$('#id').val(rememberId);
			$('#rememberId').prop('checked', true); // check 해제
		}
		
	}
</script>
</head>
<body>

	<div>
	
		<h1>로그인</h1>
		
		<form id="frm_login" action="${contextPath}/user/login" method="post">
			
			<input type="hidden" name="url" value="${url}">
			
			<div>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id">
			</div>
			
			<div>
				<label for="pw">비밀번호</label>
				<input type="password" name="pw" id="pw">
			</div>
			
			<div>			
				<button>로그인</button>
			</div>
			
			<div>			
				<label for="rememberId">
					<input type="checkbox" id="rememberId">
					아이디 저장
				</label>
				<label for="keepLogin">
					<input type="checkbox" name="keepLogin" id="keepLogin"> <!-- keep이라는 값을 가지고 DB request로 간다. -->
					로그인 유지
				</label>
			</div>
		
		</form>
			
		<div>
			<a href="${contextPath}/user/findId">아이디 찾기</a> | 
			<a href="${contextPath}/user/findPw">비밀번호 찾기</a> <!-- 임시 비번 email로 받기 구현 -->
		</div>
		
		<hr>
		
		<div>
			<a href="${apiURL}"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
		</div>
	
	</div>
	
</body>
</html>