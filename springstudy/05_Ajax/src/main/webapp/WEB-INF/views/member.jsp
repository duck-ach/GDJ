<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#frm_member {
		width: 480px;
		margin: 0 auto;
	}
	label {
		display: inline-block;
		width: 80px;	
	}
</style>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>
	
	$(document).ready(function(){
		$('#btn1').click(function(){ fn_ajax1(); });
		$('#btn2').click(function(){ fn_ajax2(); });
		$('#btn3').click(function(){ fn_ajax3(); });
		$('#btn4').click(function(){ fn_ajax4(); });
	});
	
	function fn_ajax1() {
		
		$('#result').empty(); // 결과자리에 있던 것들을 지우기
		
		$.ajax({
			/* request */
			type: 'get',
			url : '${contextPath}/member/detail1',
			data: 'id=' + $('#id').val() + '&pw=' + $('#pw').val(),
			/* response */
			dataType: 'text',
			success : function(resData) {
				$('#result').text(resData);
			},
			error : function(jqXHR) {
				$('#result').text(jqXHR.responseText);
			}
		}); // ajax
		
	} // function
	
	function fn_ajax2() {
		
		$('#result').empty(); // 결과자리에 있던 것들을 지우기
		
		$.ajax({
			/* request */
			type: 'get',
			url : '${contextPath}/member/detail2',		// ▼ serialize() 에 관한 설명 ▼
			data: $('#frm_member').serialize(), // Jquery Ajax로 호출하기 전에 serialize를 해주면 form안에 값들을 한번에 전송 가능한 data로 만들 수 있어 많은 data를 보낼 때 유용
			/* response */						// serialize를 사용하지 않는다면 하나씩 담아줘야 하기 때문에 보낼 data가 많을 수록 노가다를 엄청 해야한다.
			dataType: 'json',					// $("frm_memeber").serialize()를 찍어보면, name1=value1&name2=value2의 형식으로 나온다.
			success : function(resData){		// serializeArray()도 있는데 이것은 {name1=value1}, {name2=value2}의 형태로 반환된다.
				var ul='<ul>';
	            ul +='<li>' +resData.id + '</li>';
	            ul +='<li>' +resData.pw + '</li>';
	            ul += '</ul>';
	            $('#result').html(ul);
			},
			error : function(jqXHR) {
				$('#result').text(jqXHR.responseText);
			}
		}); // ajax
		
	} // function
	
	function fn_ajax3() {
		
		$("#result").empty();
		
		$.ajax({
			/* request */
			type: 'get',
			url : '${contextPath}/member/detail3',
			data: $('#frm_member').serialize(),
			/* response */
			dataType: 'json',
			success: function(resData) {
				var ul='<ul>';
	            ul +='<li>' +resData.id + '</li>';
	            ul +='<li>' +resData.pw + '</li>';
	            ul += '</ul>';
	            $('#result').html(ul);
			}
		}); // ajax
	} // function
	
	
	function fn_ajax4() {
		
		$('#result').empty();
		
		$.ajax({
			/* request */
			// JSON 데이터를 서버로 보낼 때는 반드시 post방식을 사용해야 함
			type: 'post',
			url : '${contextPath}/member/detail4',
			// data에 파라미터가 없음을 주의!
			// 파라미터로 전달되지 않기 때문에 주소창을 이용한 get방식이 불가능함.
			// JSON 데이터를 서버로 보낼 때는 반드시 post 방식을 사용해야 함
			data: JSON.stringify({ // JSON.stringify : 자바스크립트 데이터를 JSON문자열로 변환
				'id': $('#id').val(),   // 이렇게 생성된 {"id":"admin"}, {"pw":"1111"} 과 같은 형태는 JSON 이 아니고 객체(Object) 상태 
				'pw': $('#pw').val()	
			}),
			// 서버로 보내는 JSON 데이터의 MIME-TYPE을 작성해 줌
			contentType : 'application/json',
			/* response */
			dataType: 'json',
			success : function(resData) {
				var ul='<ul>';
	            ul +='<li>' +resData.id + '</li>';
	            ul +='<li>' +resData.pw + '</li>';
	            ul += '</ul>';
	            $('#result').html(ul);
			}
			
		}); // ajax
	}
	
</script>
</head>
<body>
	
	<form id="frm_member">
		<div>
			<label for="id">아이디</label>
			<input type="text" name="id" id="id">
		</div>
		<div>
			<label for="pw">패스워드</label>
			<input type="password" name="pw" id="pw">
		</div>
		<div>
			<input type="button" value="전송1" id="btn1">
			<input type="button" value="전송2" id="btn2">
			<input type="button" value="전송3" id="btn3">
			<input type="button" value="전송4" id="btn4">
		</div>
	</form>
	
	<hr>
	
	<%-- 결과 뿌려지는 곳 --%>
	<div id="result"></div>
	
</body>
</html>