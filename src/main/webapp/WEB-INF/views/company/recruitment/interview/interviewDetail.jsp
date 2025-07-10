<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<head>
	<meta charset="UTF-8">
	<title>면접 상세</title>
	<link rel="stylesheet" href="/dist/assets/css/board/postList.css">
	<link rel="stylesheet" href="/dist/assets/css/board/profile.css">
	<link rel="stylesheet" href="/dist/assets/css/board/board_ui.css">
	<script defer src="/js/company/recruitment/interview/interviewDetail.js"></script>
</head>


<body>

  <div class="container my-5 ">
    <div class="bg-white rounded-4 mb-5">
      <div class="mb-4 pb-2 border-bottom">
        <h2 class="fw-bold h1 mb-4" id="interviewTitle">채용 공고 명</h2>
        <h2 class="h3 mb-2" id="jobName">채용 직무 명</h2>
      </div>
      <div class="row gy-3 mb-5">
        <span class="fw-bold fs-4 text-secondary mb-1">면접 정보</span>
        <div class="col-md-6">
          <label class="fw-bold fs-5 text-secondary mb-1">채용 인원수</label>
          <div class="fs-7" id="hireCount">-</div>
        </div>
        <div class="col-md-6">
          <label class="fw-bold fs-5 text-secondary mb-1">면접 방식</label>
          <div class="fs-7" id="interviewType">-</div>
        </div>
        <div class="col-md-6">
          <label class="fw-bold fs-5 text-secondary mb-1">면접 일시</label>
          <div class="fs-7" id="interviewDate">-</div>
        </div>
        <div class="col-md-6">
          <label class="fw-bold fs-5 text-secondary mb-1">면접 장소</label>
          <div class="fs-7" id="interviewLocation">-</div>
        </div>
      </div>

      <div class="mb-5">
        <span class="fw-bold fs-4 text-secondary mb-1">면접 평가항목</span>
        <ul class="list-group list-group-flush" id="scoreItemList">
          <!-- 평가항목 리스트 예시 -->
          <!-- <li class="list-group-item ps-0">인성</li> -->
        </ul>
      </div>
      
      <!-- 화상면접 정보가 있을 때만 노출 -->
      <div id="videoInterviewArea" style="display:none;" class="mb-5 ">
        <div class="row gy-2">
          <span class="fw-bold fs-4 text-secondary mb-1">화상면접 정보</span>
          <div class="col-md-6">
            <label class="fw-bold fs-5 text-secondary mb-1">방 제목</label>
            <div class="fs-6" id="roomTitle">-</div>
          </div>
          <div class="col-md-6">
            <label class="fw-bold fs-5 text-secondary mb-1">최대 입장 인원</label>
            <div class="fs-6" id="maxJoinCount">-</div>
          </div>
          <div class="col-md-6">
            <label class="fw-bold fs-5 text-secondary mb-1">시작 시간</label>
            <div class="fs-6" id="videoStartTime">-</div>
          </div>
          <div class="col-md-6">
            <label class="fw-bold fs-5 text-secondary mb-1">종료 시간</label>
            <div class="fs-6" id="videoEndTime">-</div>
          </div>
        </div>
        <div class=" d-flex justify-content-end w-100 gap-2">
          <button class="btn btn_gray_line" id="joinVideoInterviewBtn" data-bs-toggle="modal" data-bs-target="#joinInterviewModal">화상면접 접속</button>
          <button class="btn btn-primary" id="editVideoInterviewBtn">화상면접 수정</button>
        </div>
      </div>
      
      <div>
        <span class="fw-bold fs-4 text-secondary mb-1">지원자 리스트</span>
        <div class="table-responsive gy-2 mt-2">
          <table class="table align-middle table-bordered table-hover">
            <thead class="table-light">
              <tr>
                <th class="fw-bold fs-6 text-secondary mb-1">지원자 이름</th>
                <th class="fw-bold fs-6 text-secondary mb-1">이력서</th>
                <th class="fw-bold fs-6 text-secondary mb-1">면접 시간</th>
                <th class="fw-bold fs-6 text-secondary mb-1">면접 점수</th>
              </tr>
            </thead>
            <tbody id="applicantList">
              <!-- JS로 데이터 렌더 -->
            </tbody>
          </table>
        </div>
      </div>
      <div class="d-flex align-items-end justify-content-between">
        <a href="/company/interview" class='btn btn_gray_line'>목록</a>
        <div class="d-flex gap-2 justify-content-end mt-3">
          <div class='btn btn_gray_line saveInterview' data-bs-toggle="modal" data-bs-target="#editInterviewModal">수정</div>
          <div class='btn btn_red_line saveInterview' data-bs-toggle="modal" data-bs-target="#deleteInterviewModal">삭제</div>
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

	<!-- 삭제 확인 모달 -->
	<div class="modal fade" id="deleteInterviewModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header border-0">
					<h1 class="modal-title fs-5 fw-bold text-danger" id="deleteModalLabel">
						<i class="bi bi-exclamation-triangle-fill me-1"></i> 면접 정보 삭제
					</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body text-center py-4">
					<div style="font-size:2.4rem; color:#dc3545; margin-bottom:10px;">❗</div>
					<p class="fs-5 mb-2 fw-semibold" style="color:#c82333;">
						해당 면접 정보를 <span style="color:#dc3545;">정말 삭제</span>하시겠습니까?
					</p>
					<p class="text-secondary mb-0" style="font-size:1.08rem;">
						삭제된 데이터는 복구할 수 없습니다.<br>
						실행 전 반드시 다시 한 번 확인해 주세요.
					</p>
				</div>
				<div class="modal-footer border-0 justify-content-center">
					<button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger px-4">삭제</button>
				</div>
			</div>
		</div>
	</div>

  <!-- 화상채팅 접속 확인 모달 -->
<div class="modal fade" id="joinInterviewModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header border-0">
                <h1 class="modal-title fs-5 fw-bold text-success" id="addModalLabel">
                    <i class="bi bi-plus-circle-fill me-1"></i> 화상 면접 접속
                </h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-center py-4">
                <div style="font-size:2.4rem; color:#28a745; margin-bottom:10px;">🟢</div>
                <p class="fs-5 mb-2 fw-semibold" style="color:#218838;">
                    화상 면접 방에 <span style="color:#28a745;">입장</span>하시겠습니까?
                </p>
                <p class="text-secondary mb-0" style="font-size:1.08rem;">
                    생성된 화상면접 방으로 이동합니다.<br>
                    실행 전 정보를 확인해 주세요.
                </p>
            </div>
            <div class="modal-footer border-0 justify-content-center">
                <button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn-success px-4" id="startInterviewBtn">접속</button>
            </div>
        </div>
    </div>
</div>

</body>