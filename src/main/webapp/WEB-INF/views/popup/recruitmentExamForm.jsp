<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.exam-container {
	display: flex;
	gap: 1rem;
}

.left-panel {
	flex: 1 1 60%;
}

.right-panel {
	flex: 0 0 35%;
	border-left: 1px solid #ccc;
	padding-left: 1rem;
}

.option-label {
	display: block;
	margin-bottom: .5rem;
}
/* 답안 표기란 카드 스타일 (업데이트) */
.status-item {
	display: flex;
	align-items: center;
	padding: 0.75rem 1rem;
	background-color: #f8f9fa; /* 연한 회색 배경 */
	border-radius: 0.5rem;
	box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
	flex-wrap: nowrap;
}

#status {
	margin-top: 2rem; /* 왼쪽 보기 카드와 수평 정렬을 위해 */
	gap:0.75rem;
}

.status-item .flex-grow-1 {
	/* circle 컨테이너도 flex 로 바꾸고 wrapping 금지 */
	display: flex;
	align-items:center;
	flex-wrap: nowrap;
	gap: 5px; /* circle 간격 */
	overflow-x: auto; /* 필요한 경우 스크롤 생김 */
}

.status-item .qno {
	width: 2rem;
	text-align: left;
	margin-right: 0.5rem;
	font-weight: 500;
	color: #495057; /* 진한 회색 텍스트 */
}

.status-item .circle {
	display: inline-flex;
	justify-content: center;
	align-items: center;
	width: 1.2rem; /* 기존 1.5rem → 1.2rem */
	height: 1.2rem; /* 기존 1.5rem → 1.2rem */
	font-size: 0.75rem; /* 기존 0.875rem → 0.75rem */
	border: 1px solid #adb5bd; /* 연한 테두리 */
	border-radius: 50%;
	color: #6c757d;
}

.status-item .circle.selected {
	background-color: #6f42c1;
	border-color: #6f42c1;
	color: #fff;
}

.exam-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 1rem;
}
/* 타이머 스타일 */
#timer {
	font-size: 1.25rem;
	font-weight: bold;
	color: #d63384;
}

.option-card {
	display: flex;
	align-items: center;
	border: 1px solid #ccc;
	border-radius: 0.5rem;
	padding: 0.75rem 1rem;
	margin-bottom: 0.75rem;
	cursor: pointer;
	transition: border-color 0.2s, background-color 0.2s;
	background-color: #ffffff;
}

.option-card input {
	margin-right: 0.75rem;
}

.option-card.selected {
	background-color: #ffffff;
}

#q-title {
	margin-bottom: 2rem; /* 기존보다 더 크게, 필요에 따라 값 조정 */
}

#q-options {
	margin-top: 1rem; /* 보기 그룹 위쪽에도 여유 추가 */
}
</style>
<script
	src="/js/member/recruitment/recruitmentExam/recruitmentExamForm.js?v=${System.currentTimeMillis()}""></script>
</head>
<body>
	<div class="container my-4">
		<div class="exam-header">
			<div id="exam-name" class="h3 fw-bold"></div>
			<div>
				남은 시간: <span id="timer"></span>
			</div>
		</div>
		<div class="exam-container">
			<!-- 왼쪽: 문제 + 보기 -->
			<div class="left-panel">
				<h4 id="q-title">로딩 중…</h4>
				<div id="q-options"></div>
				<div class="mt-3 d-flex justify-content-between gap-3">
					<button id="prevBtn" class="btn btn_violet_line" disabled>이전 문제</button>
					<button id="nextBtn" class="btn btn_violet_line" disabled>다음 문제</button>
					<button id="submitBtn" class="btn btn_violet d-none">제출하기</button>
				</div>
			</div>
			<!-- 오른쪽: 전체 문제 답안 여부 -->
			<div class="right-panel">
				<h5 class="fw-bold">답안 표기란</h5>
				<div id="status" class="d-flex flex-column"></div>
			</div>
		</div>
	</div>
	
	<!-- 결과 표시 전용 모달 -->
<div class="modal fade" id="resultModal" tabindex="-1"
     aria-labelledby="resultModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header border-0">
        <h5 class="modal-title fw-bold text-success fs-5" id="resultModalLabel">
          시험 결과
        </h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"aria-label="Close"></button>
      </div>
      <div class="modal-body text-center py-4">
        <!-- 합격/불합격 아이콘 -->
        <div id="resultIcon"
             style="font-size: 2.4rem; margin-bottom: 10px;"></div>
        <!-- 점수 & 합격여부 메시지 -->
        <p class="fs-5 mb-0 fw-semibold" id="resultMessage"></p>
      </div>
      <div class="modal-footer border-0 justify-content-center">
        <button type="button" class="btn btn_violet px-4"
                data-bs-dismiss="modal"
                onclick="window.close()"
                >확인</button>
      </div>
    </div>
  </div>
</div>



</body>
</html>