<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<head>
		<meta charset="UTF-8">
		<title>시험 상세보기</title>

		<style type="text/css">
			.card {
				border: 1px solid var(--gray70);
				box-shadow: none;
			}
			
			.question-card{
				border-radius:10px;
				padding:30px;
			}
		</style>
		<script src="/js/company/recruitment/companyExam/companyExamDetail.js"></script>
	</head>

	<body>
		<h2 class="h2 mb-5 fw-bold">시험 상세보기</h2>
		
		<div class="exam-no" data-exam-id="${examNo }">
			<div class="card exam-question gap-5"></div>
			
			
			<div class="exam-header d-flex justify-content-between align-items-center mt-4">
				<button id="exitBtn" class="btn btn_gray_line">목록</button>
				<div>
				<button class="btn btn_red_line" data-bs-toggle="modal" data-bs-target="#deleteExamModal" id="openDeleteModalBtn" style="display: none;">삭제</button>
					<button id="exam-edit-btn" data-exam-id="${examNo}" class="btn btn_violet_line" style="display: none;">수정</button>
				</div>
			</div>
		</div>

		<!-- 삭제 확인 모달 -->
		<div class="modal fade" id="deleteExamModal" tabindex="-1" aria-labelledby="deleteExamLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header border-0">
						<h5 class="modal-title text-danger" id="deleteExamLabel">
							<i class="bi bi-exclamation-triangle-fill me-1"></i> 시험 삭제
						</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
					</div>
					<div class="modal-body text-center py-4">
						<div style="font-size: 2.4rem; color: #dc3545; margin-bottom: 10px;">❗</div>
						<p class="fs-5 mb-2 fw-semibold" style="color: #c82333;">
							이 시험을 <span class="text-danger">정말 삭제</span>하시겠습니까?
						</p>
						<p class="text-secondary mb-0" style="font-size: 1.08rem;">
							삭제된 데이터는 복구할 수 없습니다.<br> 실행 전 반드시 다시 한 번 확인해 주세요.
						</p>
					</div>
					<div class="modal-footer border-0 justify-content-center">
						<button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
						<button type="button" id="confirmDeleteBtn" class="btn btn-danger px-4">삭제하기</button>
					</div>
				</div>
			</div>
		</div>




	</body>