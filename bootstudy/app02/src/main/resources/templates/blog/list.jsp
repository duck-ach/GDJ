<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<div>
	
	<h1>블로그 목록(전체 ${totalRecord}개)</h1>
	
	<div>
		<input type="button" value="블로그 작성하기" id="btn_write">
		<script th:inline="javascript">
			/* thymeleaf의 expression은 [[]]로 묶는다. */
			// 자바스크립트 내부에서 thymeleaf의 expression 사용을 위해서 th:inline="javascript"를 사용함
			$('#btn_write').click(function(){
				location.href = [[@{/blog/write}]];
			});
		</script>
	</div>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td>제목</td>
					<td>조회수</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>
				<!-- Variable Expression : ${} (변수값을 나타내는 것은 그대로 ${}를 사용한다.) -->
				<tr th:each="blog,rowStat:${blogList}"> <!-- :을 기준으로 varStatus는 rowStat으로 바뀌고, var는 앞으로가고, List목록은 : 콜론 뒤로 간다. -->
					<td>${beginNo - vs.index}</td>
					<td><a th:href="@{/blog/increse/hit(blogNo=${blog.blogNo})}" th:text="${blog.title}"></a></td> <!-- 주소는 @{}로 전체적으로 감싸고, 파라미터부분은 ?를 지우고 ()로 감싸준다. -->
					<td th:text="${blog.hit}"></td>	<!-- 얘도 text 해도되고 안해도되고 -->		<!-- 태그의 내용을 속성으로 보내는 것이 있다. text속성. 꼭 이걸로 해야하는 것은 아니고 같은 것이다. -->
					<td th:text="${blog.createDate}"></td>
				</tr>
			</tbody>
			<tfoot>
				<tr> <!-- paging에는 그 속에 수많은 tag가 들어가 있기 때문에 text라고 하면 link를 읽지 못한다. -->
					<!-- th:utext="HTML 구성요소 포함한 텍스트 -->
					<td colspan="4">
						${paging}
					</td>
				</tr>
			</tfoot>
		</table>
	</div>

</div>

</body>
</html>