<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
  <title>면접 정보 수정</title>
  <script defer src="/js/company/recruitment/interview/interviewForm.js"></script>
</head>

<body>
  <div class="container my-5 ">
    <div class="bg-white rounded-4 mb-5 px-4 py-5">
      <h2 class="fw-bold h1 mb-4">면접 정보 수정</h2>
      <form id="interviewEditForm" method="post" action="/company/interview/update">
        <!-- 채용 공고명, 직무명 등은 수정 불가/가능에 따라 input 또는 div로 사용 -->
        <div class="mb-4 pb-2 border-bottom">
          <div class="row g-3">

            <div class="col-md-7">
              <label class="fw-bold fs-5 text-secondary mb-1">채용 공고 명</label>
              <input type="text" class="form-control" id="recruitmentTitle" name="recruitmentTitle" required disabled>
            </div>

            <div class="col-md-5">
              <label class="fw-bold fs-5 text-secondary mb-1">채용 직무 명</label>
              <input type="text" class="form-control" id="jobName" name="jobName" required disabled>
            </div>

          </div>
        </div>
        <div class="row gy-3 mb-4">

          <div class="col-md-3">
            <label class="fw-bold fs-5 text-secondary mb-1">채용 인원수</label>
            <input type="number" min="1" class="form-control" id="hireCount" name="hireCount" required disabled>
          </div>

          <div class="col-md-3">
            <label class="fw-bold fs-5 text-secondary mb-1">면접 일시</label>
            <input type="datetime-local" class="form-control" id="interviewDate" name="interviewDate" required>
          </div>

          <div class="col-md-3">
            <label class="fw-bold fs-5 text-secondary mb-1">면접 방식</label>
            <select class="form-select" id="interviewType" name="interviewType" required>
              <option value="Y">화상면접</option>
              <option value="N">대면면접</option>
            </select>
          </div>

          <div class="col-md-3">
            <label class="fw-bold fs-5 text-secondary mb-1">면접 장소</label>
            <input type="text" class="form-control" id="interviewLocation" name="interviewLocation" required>
          </div>
        </div>

        <div class="mb-4">
          <label class="fw-bold fs-5 text-secondary mb-1">면접 평가항목</label>
          <div id="scoreItemsBox"></div>
          <button type="button" class="btn btn-outline-primary btn-sm" id="addScoreItemBtn">+ 항목 추가</button>
        </div>

        <div class="d-flex justify-content-between mt-4">
          <a class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#cancelInterviewModal">취소</a>
          <button type="button" class="btn btn-primary px-4" data-bs-toggle="modal" data-bs-target="#editInterviewModal">저장</button>
        </div>
      </form>
    </div>
  </div>

  <!-- 취소 확인 모달 -->
	<div class="modal fade" id="cancelInterviewModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header border-0">
					<h1 class="modal-title fs-5 fw-bold text-danger" id="deleteModalLabel">
						<i class="bi bi-exclamation-triangle-fill me-1"></i> 면접 정보 수정 취소
					</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body text-center py-4">
					<div style="font-size:2.4rem; color:#dc3545; margin-bottom:10px;">❗</div>
					<p class="fs-5 mb-2 fw-semibold" style="color:#c82333;">
						해당 면접 정보 수정을 <span style="color:#dc3545;">정말 취소</span>하시겠습니까?
					</p>
					<p class="text-secondary mb-0" style="font-size:1.08rem;">
						취소된 데이터는 복구할 수 없습니다.<br>
						실행 전 반드시 다시 한 번 확인해 주세요.
					</p>
				</div>
				<div class="modal-footer border-0 justify-content-center">
					<button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger px-4" id="cancelBtn">취소</button>
				</div>
			</div>
		</div>
	</div>

  <!-- 수정 확인 모달 -->
	<div class="modal fade" id="editInterviewModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header border-0">
					<h1 class="modal-title fs-5 fw-bold text-primary" id="exampleModalLabel">
						<i class="bi bi-pencil-square me-1"></i> 면접 정보 수정
					</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body text-center py-4">
					<div style="font-size:2.4rem; color:#007bff; margin-bottom:10px;">📝</div>
					<p class="fs-5 mb-2 fw-semibold" style="color:#333;">
						면접 정보를 <span style="color:#0d6efd;">수정</span>하시겠습니까?
					</p>
					<p class="text-secondary mb-0" style="font-size:1.1rem;">
						변경한 내용은 즉시 반영되며,<br>실행 전 다시 한 번 확인해 주세요.
					</p>
				</div>
				<div class="modal-footer border-0 justify-content-center">
					<button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary px-4" id="edit-interview-confirm-btn">수정</button>
				</div>
			</div>
		</div>
	</div>

</body>