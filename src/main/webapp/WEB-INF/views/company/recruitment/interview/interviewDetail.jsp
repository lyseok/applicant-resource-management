<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<head>
  <meta charset="UTF-8">
  <title>면접 상세</title>
  <link rel="stylesheet" href="/dist/assets/css/board/postList.css">
  <link rel="stylesheet" href="/dist/assets/css/board/profile.css">
  <link rel="stylesheet" href="/dist/assets/css/board/board_ui.css">
  <script defer src="/js/company/recruitment/interview/interviewDetail.js"></script>
  <style>
    .w_calc100p_24_4 {
      width: calc((100% - 62px) / 4);
    }
  </style>
</head>

<body>
  <div class="bg-white rounded-4 mb-5">
    <div class="d-flex gap-3 align-items-end mb-5">
      <h2 class="fw-bold h2 mb-0" id="interviewTitle">채용 공고 명</h2>
      <div class="d-flex align-items-end gap-2">
        <h2 class="h5 text-muted mb-0" id="jobName">채용 직무 명</h2>
        <div class="text-muted" id="hireCount">-</div>
      </div>
    </div>

    <div class="gy-3 mb-5">
    
	    <span class="d-block fw-bold fs-4 mb-2">면접 정보</span>
	    <div class="d-flex gap-3 border rounded p-3 mb-4">
	      <div class="d-flex flex-column gap-1 fs-16 w_calc100p_24_4">
	        <label class="fw-semibold lh1">면접 방식</label>
	        <div class="text-muted" id="interviewType">-</div>
	      </div>
	      <div class="d-flex flex-column gap-1 fs-16 w_calc100p_24_4">
	        <label class="fw-semibold lh1">면접 일시</label>
	        <div class="text-muted" id="interviewDate">-</div>
	      </div>
	      <div class="d-flex flex-column gap-1 fs-16 w_calc100p_24_4">
	        <label class="fw-semibold lh1">면접 장소</label>
	        <div class="text-muted" id="interviewLocation">-</div>
	      </div>
	    </div>
          
          
        <!-- 화상면접 정보가 있을 때만 노출 -->
        <div id="videoInterviewArea" style="display:none;" class="mt-4">
          <span class="d-block fw-bold fs-4 mb-2">화상면접 정보</span>
          <div class="d-flex gap-3 mb-4 border rounded p-4">
            <div class="d-flex flex-column gap-1 fs-16 w_calc100p_24_4">
              <label class="fw-semibold lh1">방 제목</label>
              <div class="text-muted" id="roomTitle">-</div>
            </div>
            <div class="d-flex flex-column gap-1 fs-16 w_calc100p_24_4">
              <label class="fw-semibold lh1">최대 입장 인원</label>
              <div class="text-muted" id="maxJoinCount">-</div>
            </div>
            <div class="d-flex flex-column gap-1 fs-16 w-50">
              <label class="fw-semibold lh1">시작 시간</label>
              <div class="d-flex gap-2">
                <div class="text-muted" id="videoStartTime">-</div>
                <span>~</span>
                <div class="text-muted" id="videoEndTime">-</div>
              </div>
            </div>
          </div>
        </div>

      <div class="mb-4">
        <span class="d-block fw-bold fs-4 mb-2">면접 평가항목</span>
        <ul class="list-group list-group-flush gap-1 border rounded p-3" id="scoreItemList">
          <!-- JS로 평가항목 렌더링 -->
        </ul>
      </div>

      <div class="d-flex justify-content-center w-100 gap-2 mt-4">
        <button class="btn btn_violet_line" id="editVideoInterviewBtn">화상면접 수정</button>
        <button class="btn btn_violet w140 justify-content-center" id="joinVideoInterviewBtn" data-bs-toggle="modal"
          data-bs-target="#joinInterviewModal">화상면접 접속</button>
      </div>
    </div>

    <div>
      <span class="fw-bold fs-4 mb-1">지원자 리스트</span>
      <div class="table-responsive gy-2 mt-2">
        <table class="table align-middle text-center fs-14">
          <thead class="border-top">
            <tr>
              <th class="text-center fw-semibold lh1 fs-14 py-3 align-middle text-dark">지원자 이름</th>
              <th class="text-center fw-semibold lh1 fs-14 py-3 align-middle text-dark">이력서</th>
              <th class="text-center fw-semibold lh1 fs-14 py-3 align-middle text-dark">면접 시간</th>
              <th class="text-center fw-semibold lh1 fs-14 py-3 align-middle text-dark">면접 점수</th>
            </tr>
          </thead>
          <tbody id="applicantList" class="border-top-0">
            <!-- JS로 데이터 렌더 -->
          </tbody>
        </table>
      </div>
    </div>

    <div class="d-flex align-items-end justify-content-between">
      <a href="/company/interview" class='btn btn_gray_line'>목록</a>
      <div class="d-flex gap-2 justify-content-end mt-3">
        <div class='btn btn_violet_line' data-bs-toggle="modal" data-bs-target="#editInterviewModal"
          id="edit-interview-confirm-btn">수정</div>
        <!-- <div class='btn btn_red_line saveInterview' data-bs-toggle="modal" data-bs-target="#deleteInterviewModal">삭제</div> -->
      </div>
    </div>
  </div>

  <!-- 삭제 확인 모달 -->
  <div class="modal fade" id="deleteInterviewModal" tabindex="-1" aria-labelledby="deleteModalLabel"
    aria-hidden="true">
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
          <p class="fs-5 mb-2 fw-semibold lh1" style="color:#c82333;">
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
          <div style="font-size: 2.4rem; color: var(--violet80); margin-bottom: 10px;">🟪</div>
          <p class="fs-5 mb-2 fw-semibold lh1" style="color: var(--violet80) ;">
            화상 면접 방에 <span style="color: var(--violet110)">입장 </span>하시겠습니까?
          </p>
          <p class="text-secondary mb-0" style="font-size:1.08rem;">
            생성된 화상면접 방으로 이동합니다.<br>
            실행 전 정보를 확인해 주세요.
          </p>
        </div>
        <div class="modal-footer border-0 justify-content-center">
          <button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">취소</button>
          <button type="button" class="btn btn_violet px-4" id="startInterviewBtn">접속</button>
        </div>
      </div>
    </div>
  </div>
</body>
