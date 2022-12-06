<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<jsp:include page="../layout/header.jsp">
	<jsp:param value="${blog.blogNo}번 블로그" name="title"/>
</jsp:include>

<style>
	.blind {
		display: none;
	}
</style>

<div>

	<h1 th:text="${blog.title}"></h1>
	
	<div> <!-- thymeleaf는 Java의 Template라서 Java문법이 많이 들어가 있다. -->
		<!-- 
			날짜 형식 지정하기
			1. DATE 타입    : ${#dates.format(날짜, '패턴')}
			2. VARCHAR 타입 : ${#temporals.format(날짜, '패턴')}
			   LocalDate 타입 (JDK 1.8 이후에 나온 타입들. 날짜만씀)
			   LocalTime 타입 (시간만씀)
			   LocalDateTime 타입 (날짜와 시간 같이씀)
			* Local~ 도 temporals를 사용한다. (Date를 쓰지않는이상 대부분 temporal
			4. Calendar타입 : ${#calendars.format(날짜, '패턴')}
		 	* '패턴'은 SimpleDateFormat의 패턴을 그대로 사용함
		 -->
		<span th:text="'▷ 작성일' + ${#dates.format(blog.createDate, 'yyyy. M. d HH:mm')}"></span> <!-- 이 방법과 아래방법 둘다 문자열 연결할 때 사용 가능 -->
		<span th:text="|▷ 작성일 ${#dates.format(blog.createDate, 'yyyy. M. d HH:mm')}|"></span> <!-- PIPE 표시를 이용하면 그 PIPE 내부에 있는 문자열을 연결할 수 있다. -->
		<!-- 문자열을 ''작은 따옴표로 연결하고, 그것을 +로 연결한다. -->
		&nbsp;&nbsp;&nbsp;
		<span th:text="'▷ 수정일' + ${#dates.format(blog.modifyDate, 'yyyy. M. d HH:mm')}"></span> <!-- 이 방법과 아래방법 둘다 문자열 연결할 때 사용 가능 -->
		<span th:text="|▷ 수정일 ${#dates.format(blog.modifyDate, 'yyyy. M. d HH:mm')}|"></span>
	</div>
	
	<div>
	
		<!-- 
			숫자 형식 지정하기
			1. 정수 형식      ${#numbers.formatInteger(숫자, 자릿수, 천단위구분기호)}
			2. 실수 형식 		${#numbers.formatDecimal(숫자, 정수자릿수, 천단위구분기호, 소수자릿수, 정수소수구분자)}
			3. 
		-->
		<span th:text="'조회수' + ${#number.formatInteger(blog.hit, 1, 'COMMA')}"></span>
	</div>
	
	<hr> d
	
	<div>
		${blog.content}
	</div>
	
	<div>
		<form id="frm_btn" method="post">
			<input type="hidden" name="blogNo" value="${blog.blogNo}">
			<input type="button" value="수정" id="btn_edit_blog">
			<input type="button" value="삭제" id="btn_remove_blog">
			<input type="button" value="목록" onclick="location.href='${contextPath}/blog/list'">
		</form>
		<script>
			$('#btn_edit_blog').click(function(){
				$('#frm_btn').attr('action', '${contextPath}/blog/edit');
				$('#frm_btn').submit();
			});
			$('#btn_remove_blog').click(function(){
				if(confirm('블로그를 삭제하면 블로그에 달린 댓글을 더 이상 확인할 수 없습니다. 삭제하시겠습니까?')){
					$('#frm_btn').attr('action', '${contextPath}/blog/remove');
					$('#frm_btn').submit();
				}
			});
		</script> 
	</div>
	
	<hr>
	
	<span id="btn_comment_list">
		댓글
		<span id="comment_count"></span>개
	</span>
	
	<hr>
	
	<div id="comment_area" class="blind">
		<div id="comment_list"></div>
		<div id="paging"></div>
	</div>
	
	<hr>
	
	<div>
		<form id="frm_add_comment">
			<div class="add_comment">
				<div class="add_comment_input">
					<input type="text" name="content" id="content" placeholder="댓글을 작성하려면 로그인 해 주세요">
				</div>
				<div class="add_comment_btn">
					<input type="button" value="작성완료" id="btn_add_comment">
				</div>
			</div>
			<input type="hidden" name="blogNo" value="${blog.blogNo}">
		</form>
	</div>
	
	<!-- 현재 페이지 번호를 저장하고 있는 hidden -->
	<input type="hidden" id="page" value="1">
	
	<script>
	
		// 함수 호출
		fn_commentCount();
		fn_switchCommentList();
		fn_addComment();
		fn_commentList();
		fn_changePage();
		fn_removeComment();
		fn_switchReplyArea();
		fn_addReply();
		
		// 함수 정의
		function fn_commentCount(){
			$.ajax({
				type: 'get',
				url: '${contextPath}/comment/getCount',
				data: 'blogNo=${blog.blogNo}',
				dataType: 'json',
				success: function(resData){  // resData = {"commentCount": 개수}
					$('#comment_count').text(resData.commentCount);
				}
			});
		}
		
		function fn_switchCommentList(){
			$('#btn_comment_list').click(function(){
				$('#comment_area').toggleClass('blind');
			});
		}
		
		function fn_addComment(){
			$('#btn_add_comment').click(function(){
				if($('#comment').val() == ''){
					alert('댓글 내용을 입력하세요');
					return;
				}
				$.ajax({
					type: 'post',
					url: '${contextPath}/comment/add',
					data: $('#frm_add_comment').serialize(),
					dataType: 'json',
					success: function(resData){  // resData = {"isAdd", true}
						if(resData.isAdd){
							alert('댓글이 등록되었습니다.');
							$('#content').val('');
							fn_commentList();   // 댓글 목록 가져와서 뿌리는 함수
							fn_commentCount();  // 댓글 목록 개수 갱신하는 함수
						}
					}
				});
			});
		}
		
		function fn_commentList(){
			$.ajax({
				type: 'get',
				url: '${contextPath}/comment/list',
				data: 'blogNo=${blog.blogNo}&page=' + $('#page').val(),
				dataType: 'json',
				success: function(resData){
					/*
						resData = {
							"commentList": [
								{댓글하나},
								{댓글하나},
								...
							],
							"pageUtil": {
								page: x,
								...
							}
						}
					*/
					// 화면에 댓글 목록 뿌리기
					$('#comment_list').empty();
					$.each(resData.commentList, function(i, comment){
						var div = '';
						if(comment.depth == 0){
							div += '<div>';
						} else {
							div += '<div style="margin-left: 40px;">';
						}
						if(comment.state == 1) {
							div += '<div>';
							div += comment.content;
							// 작성자만 지울 수 있도록 if 처리 필요
							div += '<input type="button" value="삭제" class="btn_comment_remove" data-comment_no="' + comment.commentNo + '">';
							// 댓글만 답글을 달 수 있도록 if 처리 필요
							if(comment.depth == 0) {
								div += '<input type="button" value="답글" class="btn_reply_area">'; // comment의 commentNo가 groupNo와 같다.
							}
							div += '</div>';
						} else {
							if(comment.depth == 0) {
								div += '<div>삭제된 댓글입니다.</div>';
							} else {
								div += '<div>삭제된 답글입니다.</div>';
							}
						}
						div += '<div>';
						moment.locale('ko-KR');
						div += '<span style="font-size: 12px; color: silver;">' + moment(comment.createDate).format('YYYY. MM. DD hh:mm') + '</span>';
						div += '</div>';
						div += '<div style="margin-left:40px;" class="reply_area blind">';
						div += '<form class="frm_reply">';
						div += '<input type="hidden" name="blogNo" value="' + comment.blogNo + '">'; // hidden에는 name속성이 있어야함(serialize로 보낼것임)
						div += '<input type="hidden" name="groupNo" value="' + comment.commentNo + '">';
						div += '<input type="text" name="content" placeholder="답글을 작성하려면 로그인을 해 주세요">'
						// 로그인한 사용자만 볼 수 있도록 if 처리
						div += '<input type="button" value="답글작성완료" class="btn_reply_add">' // type을 submit으로 해버리면 ajax 처리가 안됨. mvc처리가 됨
						div += '</form>';
						div += '</div>';
						div += '</div>';
						$('#comment_list').append(div);
						$('#comment_list').append('<div style="border-bottom: 1px dotted gray;"></div>');
					});
					// 페이징
					$('#paging').empty();
					var pageUtil = resData.pageUtil;
					var paging = '';
					// 이전 블록
					if(pageUtil.beginPage != 1) {
						paging += '<span class="enable_link" data-page="'+ (pageUtil.beginPage - 1) +'">◀</span>';
					}
					// 페이지번호
					for(let p = pageUtil.beginPage; p <= pageUtil.endPage; p++) {
						if(p == $('#page').val()){
							paging += '<strong>' + p + '</strong>';
						} else {
							paging += '<span class="enable_link" data-page="'+ p +'">' + p + '</span>';
						}
					}
					// 다음 블록
					if(pageUtil.endPage != pageUtil.totalPage){
						paging += '<span class="enable_link" data-page="'+ (pageUtil.endPage + 1) +'">▶</span>';
					}
					$('#paging').append(paging);
				}
			});
		}  // fn_commentList
		
		function fn_changePage(){
			$(document).on('click', '.enable_link', function(){
				$('#page').val( $(this).data('page') );
				fn_commentList();
			});
		}
		
		function fn_removeComment() {
			$(document).on('click', '.btn_comment_remove', function(){
				if(confirm('삭제된 댓글은 복구할 수 없습니다. 댓글을 삭제할까요?')){
					$.ajax({
						type : 'post', // 큰 차이는 없음
						url : '${contextPath}/comment/remove',
						data : 'commentNo=' + $(this).data('comment_no'), // 클릭한 버튼의 data속성에 넣음
						dataType : 'json',
						success : function(resData) { // resData = {"isRemove" : true}
							if(resData.isRemove) {
								alert('댓글이 삭제되었습니다.');
								fn_commentList(); // 목록갱신
								fn_commentCount(); // 개수갱신
							}
						}
					}); // ajax
				} // if
			}); // event
		} // function
		
		function fn_switchReplyArea() {
			$(document).on('click', '.btn_reply_area', function() {
				$(this).parent().next().next().toggleClass('blind'); // this의 부모의 형제의 형제
			}); // event
		} // function
		
		function fn_addReply() {
			$(document).on('click', '.btn_reply_add', function(){
				// 공백검사
				if($(this).prev().val() == '') {
					alert('답글 내용을 입력하세요.');
					return;
				}
				$.ajax({ // parent() 는 부모, closest()는 가장가까운
					type : 'post',
					url : '${contextPath}/comment/reply/add', 
					data : $(this).closest('.frm_reply').serialize(), // 이건 form이 많아서 안 됩니다 $('.frm_reply').serialize(),
					dataType : 'json',
					success : function(resData) { // resData = {"isAdd", true}
						alert('답글이 등록되었습니다.');
						fn_commentList(); // 목록갱신
						fn_commentCount(); // 개수갱신
					}
				});
			});
		}
		
		
	</script>
	
	
	
	
	


</div>

</body>
</html>