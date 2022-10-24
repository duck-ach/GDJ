<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String contextPath = request.getContextPath();
	pageContext.setAttribute("contextPath", contextPath);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	/*
		ajax는 A에서 요청하면 A로 응답이온다.
		MVC는 A에서 요청하면 B로 응답이온다.
	*/
	$(document).ready(function(){
		fn_getAllMembers();
		fn_getMember();
		fn_registration();
	});
	function fn_getAllMembers() {
		$.ajax({
			/* 요청 */
			type: 'get',
			url: '${contextPath}/member/list.do',
			/* 응답 */
			dataType: 'json',
			success: function(resData) { // resData : {"count" : 3, "members" : [{}, {}, {}]}
				// 1. resData.count, resData['count']
				// console.log(resData.count, resData['count']); 3 3 같은 결과.
				$('#count').text(resData.count);
				// 2. member_list 영역 초기화
				$('#member_list').empty();
				// 3. resData.members : [{}, {}, {}]
				// $.each(배열, function(인덱스, 배열요소){})
				$.each(resData.members, function(i, member){
					var tr = '<tr>';
					tr += '<td>' + member.memberNo + '</td>';
					tr += '<td>' + member.id + '</td>';
					tr += '<td>' + member.name + '</td>';
					tr += '<td>' + (member.gender == 'M' ? '남자' : '여자') + '</td>';
					tr += '<td>' + member.grade + '</td>';
					tr += '<td>' + member.address + '</td>'; 
					tr += '<td><input type="hidden" value="'+member.memberNo+'"><input type="button" value="조회" class="btn_detail"></td>'; // 문자열과 문자열 사이에 요소를 삽입할 때 ++를 이용한다.
					tr += '</tr>';
					$('#member_list').append(tr); // html(tr)은 원래있던거 제거하고 덮어쓰기
				})
			}
		});
	}
	
	function fn_getMember() {
		// jquery로 만든 버튼이라서 클릭같은 이벤트가 먹지 않음.
		// "조회" 버튼은 동적 요소이기 때문에 다음 이벤트 방식을 사용해야 한다
		// $(부모요소).on(이벤트타입, 이벤트대상, 이벤트리스너)
		$('body').on('click', '.btn_detail', function(){
			// alert($(this).prev().val()); // hidden을 앞에두면 prev(), 뒤에두면 next()
			$.ajax({
				/* 요청 */
				type:'get',
				url: '${contextPath}/member/detail.do',
				data: 'memberNo=' + $(this).prev().val(),
				/* 응답 */
				dataType : 'json',
				success: function(resData) { // resData : {"exists" : true, "member" : {"id", "name"}}
					if(resData.exists) {
						alert('회원 정보가 조회되었습니다.');
						$('#id').val(resData.member.id).prop('readonly', true);
						$('#name').val(resData.member.name);
						$(':radio[name=gender][value=' + resData.member.gender + ']').prop('checked', true);
						$('#grade').val(resData.member.grede);
						$('#address').val(resData.member.address);
					} else {
						alert('조회된 회원 정보가 없습니다.');
					}
				}
			});
		});
	}
	
	function fn_registration() {
		
		$('#btn_add').click(function(){
			
			$.ajax({
				/* 요청 */
				type : 'post',
				url: '${contextPath}/member/add.do',
				data: $('#frm_member').serialize(), // serialize() : 폼의 모든 입력 요소를 파라미터로 변환
				/* 응답 */
				dataType : 'json',
				success : function(resData) {
					if(resData.isSuccess) {
						alert('신규 회원이 등록되었습니다.');
						fn_getAllMembers(); // 목록을 새로 가져와서 갱신함
					} else {
						alert('신규 회원 등록이 실패했습니다.')
					}
				},
				// 예외 응답
				error : function(jqXHR) { // 예외 처리 응답 데이터(일반 텍스트)는 jqXHR 객체의 responseText 속성에 저장됨
					alert(jqXHR.responseText);
				}
				
			}); // ajax
			
		}); // click
	
	} // function
	
</script>
</head>
<body>

	<div class="wrap">
		<h1 class="title">회원관리</h1>
		<form id="frm_member">
			<div>
				<label for="id">아이디</label>
				<input type="text" id="id" name="id">
			</div>
			<div>
				<label for="name">이름</label>
				<input type="text" id="name" name="name">
			</div>
			<div>
				<label for="male">남자</label>
				<input type="radio" id="male" name="gender" value="M">
				<label for="female">여자</label>
				<input type="radio" id="female" name="gender" value="F">
			</div>
			<div>
				<label for="grade">회원등급</label>
				<select id="grade" name="grade">
					<option value="">등급선택</option>
					<option value="gold">골드</option>
					<option value="silver">실버</option>
					<option value="bronze">브론즈</option>
				</select>
			</div>
			<div>
				<label for="address">주소</label>
				<input type="text" id="address" name="address">
			</div>
			<div>
				<input type="button" value="초기화" id="btn_init">
				<input type="button" value="신규등록" id="btn_add">
				<input type="button" value="변경내용저장" id="btn_modify">
				<input type="button" value="회원삭제" id="btn_remove">
			</div>
		</form>
	<hr>
		<table class="member_table">
			<caption>전체 회원 수 : <span id="count"></span>명</caption>
			<thead>
				<tr>
					<td>회원번호</td>
					<td>아이디</td>
					<td>이름</td>
					<td>성별</td>
					<td>등급</td>
					<td>주소</td>
					<td></td>
				</tr>
			</thead>
			<tbody id="member_list">
			</tbody>
		</table>
	</div>
</body>
</html>