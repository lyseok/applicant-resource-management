<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.banner {
	background: #ebe8ff;
}

.btn-purple {
	background: #6f49fd;
	color: #fff;
}

.exam-item {
	cursor: pointer;
	transition: background .2s;
}

.exam-item:hover {
	background: #f8f9fa;
}
</style>

<script src = "/js/member/recruitment/recruitmentExam/recruitmentExamList.js"></script>
</head>
<body class="bg-light">

	<div class="container py-5">
		<!-- 헤더 / 배너 -->
		<div class="p-4 mb-4 banner rounded">
			<div class="d-flex justify-content-between align-items-center">
				<div class="fs-5">📋 응시 시험 관리</div>
				<button class="btn btn-purple">지원한 공고 보러가기</button>
			</div>
		</div>

		<!-- 검색바 -->
		 <!-- 검색바 -->
		<div class="mb-3 d-flex justify-content-end">
			<input id="examSearch" type="search" class="form-control w-50"
				placeholder="시험명·공고명·기업명으로 검색" />
		</div>

		<!-- 총 개수 -->
		<div id="exam-count" class="mb-2 text-muted">총 0건</div>

		<!-- 시험 리스트 -->
		<div id="exam-list" class="list-group">
			<div class="list-group-item text-center text-muted">로딩 중...</div>
		</div>
	</div>



	<div class="modal fade" id="recruitExamModal" tabindex="-1"
		aria-labelledby="addModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header border-0">
					<h1 class="modal-title fs-5 fw-bold text-success"
						id="recruitExamModalLabel">
						<i class="bi bi-plus-circle-fill me-1"></i> 시험 응시
					</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body text-center py-4">
					<div
						style="font-size: 2.4rem; color: #28a745; margin-bottom: 10px;">🟢</div>
					<p class="fs-5 mb-2 fw-semibold" style="color: #218838;">
						시험을 <span style="color: #28a745;">응시 </span>하시겠습니까?
					</p>
					<p class="text-secondary mb-0" style="font-size: 1.08rem;">
						입력한 정보가 등록됩니다.<br> 실행 전 내용을 다시 한 번 확인해 주세요.
					</p>
				</div>
				<div class="modal-footer border-0 justify-content-center">
					<button type="button" class="btn btn-outline-secondary px-4"
						data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-success px-4"
						id="confirmExamBtn" data-exam-no="" data-applicant-id=""
						data-exam-time="">응시</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 시험 기간 아닐 때 경고 모달 -->
	<div class="modal fade" id="notAvailableModal" tabindex="-1"
		aria-labelledby="notAvailableLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content text-center">
				<div class="modal-header border-0">
					<h5 class="modal-title fw-bold text-warning" id="notAvailableLabel">⚠️
						응시 불가</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="닫기"></button>
				</div>
				<div class="modal-body">
					<p class="fs-5 mb-0">시험 응시 기간이 아닙니다.</p>
				</div>
				<div class="modal-footer border-0 justify-content-center">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>




</body>
</html>