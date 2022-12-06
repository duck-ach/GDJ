<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>${title}</title>
<script th:src="@{/js/jquery-3.6.1.min.js}"></script>
<script th:src="@{/js/moment-with-locales.js}"></script> <!-- ajax 날짜 시간 처리 -->
<script th:src="@{/summernote-0.8.18-dist/summernote-lite.js}"></script>
<script th:src="@{/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js}"></script>
<link rel="stylesheet" th:href="@{/summernote-0.8.18-dist/summernote-lite.css}">
</head>
<body>

<!-- 
	ajax로 page 목록을 만든다는 뜻 = javascript로 전부 다 만든다는 뜻. 날짜/시간 형태로 바꿔줄 수 있는 코드가 어려워서
	js에 moment-with-locales.js 라는 라이브러리 넣어놓았음
 -->
	<div>
		<h1>Welcome To My Blog</h1>
	</div>
	