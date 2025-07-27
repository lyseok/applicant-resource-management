<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>응시 시험 관리</title>

<script src = "/js/member/recruitment/recruitmentExam/recruitmentExamList.js"></script>
</head>
<body class="bg-light">

	<div class="container py-5">
		<p class="h1 mb-3 fw-bold">응시 시험 관리</p>
		<!-- 헤더 / 배너 -->
		<div class="border-bottom d-flex justify-content-between align-items-end pb-2">
			<p class="fs-14">총 <span id="exam-count" class="fw-bold"></span>건</p>
			<div class="TypoBox searchBar">
				<div class="searchBarWrap">
					<label class="searchBarLabel" for="listKeyword">검색어</label>
					<input type="text" id="examSearch" class="searchBarInput" placeholder="시험명·공고명·기업명으로 검색" autocomplete="off" value="">
				</div>
				<button type="button" class="searchBarBtn" id="searchBtn">
					<span class="material-symbols-outlined">search</span>
				</button>
			</div>
		</div>

		<!-- 총 개수 -->

		<!-- 시험 리스트 -->
		<div id="exam-list" class="list-group">
			<div class="list-group-item text-center text-muted">로딩 중...</div>
		</div>
	</div>
	

	<!-- 결과 표시 전용 모달 -->
<div class="modal fade" id="resultModal" tabindex="-1"
     aria-labelledby="resultModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header border-0">
        <h5 class="modal-title fw-bold text-primary" id="resultModalLabel">
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
        <button type="button" class="btn btn-primary px-4"
                data-bs-dismiss="modal"
                onclick="window.close()"
                >확인</button>
      </div>
    </div>
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
						style="font-size: 2.4rem; color: var(--violet80); margin-bottom: 10px;">🟪</div>
					<p class="fs-5 mb-2 fw-semibold" style="color: var(--violet80) ;">
						시험에 <span style="color: var(--violet110)">응시 </span>하시겠습니까?
					</p>
					<p class="text-secondary mb-0" style="font-size: 1.08rem;">
						입력한 정보가 등록됩니다.<br> 실행 전 내용을 다시 한 번 확인해 주세요.
					</p>
				</div>
				<div class="modal-footer border-0 justify-content-center">
					<button type="button" class="btn btn-outline-secondary px-4"
						data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn_violet px-4"
						id="confirmExamBtn" data-exam-no="" data-applicant-id=""
						data-exam-time="">응시</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 시험 기간 아닐 때 경고 모달 -->
	<div class="modal fade" id="notAvailableModal" tabindex="-1"aria-labelledby="notAvailableLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content text-center">
				<div class="modal-header border-0">
					<h5 class="modal-title fw-bold text-warning fs-5" id="notAvailableLabel">⚠️ 응시 불가</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
				</div>
				<div class="modal-body">
					<p class="fs-5 mb-0 fw-bold">시험 응시 기간이 아닙니다.</p>
				</div>
				<div class="modal-footer border-0 justify-content-center">
					<button type="button" class="btn btn_gray_line"
						data-bs-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>




</body>
</html>