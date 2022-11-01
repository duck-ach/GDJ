<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPath}/resources/css/jquery-ui.min.css">
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script src="${contextPath}/resources/js/jquery-ui.min.js"></script> <!-- 순서! jquery가 먼저 있고, jquery ui를 두어야함. (jquery를 사용하기 때문) -->
<script>

	$(document).ready(function(){
		
		$('#targetDt').datepicker({
			dateFormat:'yymmdd' // 실제로는 yyyymmdd로 적용
		});
		
		$('#btn').click(function(){
			$.ajax({
				type: 'get',
				url : '{contextPath}/movie/boxOfficeList',
				data: 'targetDt=' + $('#targetDt').val(),
				dataType: 'json',
				success:function() {
					// 기존 목록 초기화
					$('#boxOfficeList').empty();
					// 가져온 목록 나타내기
					$.each(resData.boxOfficeResult.dailyBoxOfficeList, function(i, movie){
						var tr = '<tr>';
						tr += '<td>' + resData.rank + '</td>';
						tr += '<td>' + resData.movieNm + '</td>';
						tr += '<td>' + resData.openDt + '</td>';
						tr += '<td>' + resData.audiCnt + '</td>';
						tr += '<td>' + resData.audiAcc + '</td>';
						tr += '</tr>';
						$('#boxOfficeList').html(tr);
					});
				}
			});
		});
	});

</script>
</head>
<body>

	<div>
		<label for="targetDt">조회날짜</label>
		<input type="text" id="targetDt">
		<input type="button" value="조회" id="btn">
	</div>

<hr>

	<div>
		<table border="1">
			<thead>
				<tr>
					<td>순위</td>
					<td>영화제목</td>
					<td>개봉일</td>
					<td>일일관객수</td>
					<td>누적관객수</td>
				</tr>
			</thead>
			<tbody id="boxOfficeList"></tbody>
		</table>
	</div>
</body>
</html>