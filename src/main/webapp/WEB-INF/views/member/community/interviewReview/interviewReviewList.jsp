<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.badge-fail {
	background-color: #ff6b6b;
}

.badge-pass {
	background-color: #4caf50;
}

.badge-neutral {
	background-color: #ffc107;
}

.section-title {
	font-weight: bold;
	margin-top: 1.5rem;
}

.accordion-button:not(.collapsed) {
  background-color: var(--violet30) !important;
  color: white; /* 선택 사항: 텍스트 색상도 함께 조정 */
}

</style>
<script
	src="/js/member/community/interviewReview/interviewReviewList.js"></script>
</head>
<body class="bg-light">

	<div class="container my-5">
		
		
		<p class="h2">면접 후기</p>
		<div class="accordion" id="interviewReviewList"></div>
	</div>

</body>
</html>