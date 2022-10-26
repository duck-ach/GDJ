<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("contextPath", request.getContextPath()); // c:set이 하는 일
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<script>
$(document).ready(function(){
	$('#btn_refresh').click(function(){
		$.ajax({
			/* 요청 */
			type:'get',
			url : '${contextPath}/member/refreshCaptcha.do',
			/* 응답 */
			dataType:'json',
			success : function(resData){ // resData : {"dirname":"", "filename":"", "key":""}
				$('#ncaptcha').prop('src', '../' + resData.dirname + '/' + resData.filename) // attr() 와 prop() 둘다 상관없음
				$('#key').val(resData, key); // 그림이 바뀌면 키도 바뀌어야함
			}
		});
	});
});

</script>

	<div class="wrap">
		
		<h1>로그인</h1>
		<form action="${contextPath}/member/validateCaptcha.do" method="post">
			<div>
				<input type="text" name="id" id="id" placeholder="아이디">
			</div>
			<div>
				<input type="password" name="pw" id="pw" placeholder="패스워드">
			</div>
				<p>아래 이미지를 보이는 대로 입력해주세요.</p>
			<div style="display: flex;">
				<div>
					<img src="../${dirname}/${filename}" id="ncaptcha"><!-- 이미지는 절대경로에 보관하지 못함. (이미지가 커짐 피곤해짐 ㅋㅋ) -->
				</div>
				<div>
					<input type="button" value="새로고침" id="btn_refresh">
				</div>
			</div>
			<div>	<!-- 아래 name값을 value로 바꾼이유는 파라미터이름 안바꾸려고 -->
				<input type="text" name="value" placeholder="자동입력 방지문자">
				<input type="hidden" name="key" id="key" value="${key}"> <!--  이미지별로 키가 다름. -->
			</div> <!-- submit을 누르면 name값 두개 파라미터로 날아감(value, key) -->
			<div>
				<button>로그인</button>
			</div>
		</form>
		
	</div>


</body>
</html>