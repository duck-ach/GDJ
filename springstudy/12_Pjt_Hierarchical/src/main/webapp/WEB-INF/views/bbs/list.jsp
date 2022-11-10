<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
$(function(){ // 익명함수를 묶은 것. ready와 같음
	
	if('${recordPerPage}' != ''){
		$('#recordPerPage').val(${recordPerPage});			
	} else {
		$('#recordPerPage').val(10);
	}
	
	$('#recordPerPage').change(function(){
		location.href = '${contextPath}/bbs/list?recordPerPage=' + $(this).val();
	});
	
});
</script>
</head>
<body>
	<div>
		<a href="${contextPath}/bbs/write">작성하러 가기</a>
	</div>
	<div>
		<select id="recordPerPage">
			<option value="10">10</option>
			<option value="20">20</option>
			<option value="30">30</option>
		</select>
	</div>
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td>작성자</td>
					<td>제목</td>
					<td>IP</td>
					<td>작성일</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bbs" items="${bbsList}" varStatus="vs">
					<tr>
						<td>${beginNo - vs.index}</td>
						<td>${bbs.writer}</td>
   						<td>
							<c:if test="${bbs.state == 0}">
								삭제된 게시글입니다
							</c:if>
							<c:if test="${bbs.state == 1}">
								${bbs.title}
							</c:if>
						</td>
						<td>${bbs.ip}</td>
						<td>${bbs.createDate}</td>
						<td style="text-align: center;"> 
							<!-- data-아무거나 : 데이터소스 -->
							<form method="post" action="${contextPath}/bbs/remove"> <!-- class로 처리했던 이유 : foreach 문 안이라서 id는 중복이 불가능하기 때문 -->
								<input type="hidden" name="bbsNo" value="${bbs.bbsNo}">
								<a id="lnk_remove${bbs.bbsNo}"><i class="fa-solid fa-trash"></i></a>
							</form> <!-- id로 하면 button1, button2 등 다 다를 것이니까 id로줌 -->
							<script>
								$('#lnk_remove${bbs.bbsNo}').click(function(){
									if(confirm('삭제할까요?')){
										$(this).parent().submit();
									}
								});
							</script>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6">${paging}</td>
				</tr>
			</tfoot>
		</table>
	</div>
	
</body>
</html>