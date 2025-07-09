<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<head>
	<meta charset="UTF-8">
	<title>면접 목록</title>
	<link rel="stylesheet" href="/dist/assets/css/board/postList.css">
	<link rel="stylesheet" href="/dist/assets/css/board/profile.css">
	<link rel="stylesheet" href="/dist/assets/css/board/board_ui.css">
	<script defer src="/js/company/recruitment/interview/interviewDetail.js"></script>
</head>


<body>

  <div class="container my-5" style="max-width: 860px;">
    <div class="bg-white rounded-4 shadow-sm p-5">
      <div class="mb-4 pb-2 border-bottom">
        <h2 class="fw-bold h3 mb-2" id="interviewTitle">채용 공고 명</h2>
        <div class="text-secondary small" id="jobName">채용 직무 명</div>
      </div>
      <div class="row gy-3">
        <div class="col-md-6">
          <label class="text-secondary mb-1">채용 인원수</label>
          <div class="fs-5 fw-semibold" id="hireCount">-</div>
        </div>
        <div class="col-md-6">
          <label class="text-secondary mb-1">면접 방식</label>
          <div class="fs-5 fw-semibold" id="interviewType">-</div>
        </div>
        <div class="col-md-6">
          <label class="text-secondary mb-1">면접 일시</label>
          <div class="fs-5" id="interviewDate">-</div>
        </div>
        <div class="col-md-6">
          <label class="text-secondary mb-1">면접 장소</label>
          <div class="fs-5" id="interviewLocation">-</div>
        </div>
      </div>

      <hr class="my-4">

      <div class="mb-4">
        <label class="text-secondary mb-1">면접 평가항목</label>
        <ul class="list-group list-group-flush" id="scoreItemList">
          <!-- 평가항목 리스트 예시 -->
          <!-- <li class="list-group-item ps-0">인성</li> -->
        </ul>
      </div>

      <!-- 화상면접 정보가 있을 때만 노출 -->
      <div id="videoInterviewArea" style="display:none;">
        <h5 class="fw-bold mb-3 mt-4">화상면접 정보</h5>
        <div class="row gy-2">
          <div class="col-md-6">
            <label class="text-secondary mb-1">방 제목</label>
            <div class="fs-6" id="roomTitle">-</div>
          </div>
          <div class="col-md-6">
            <label class="text-secondary mb-1">최대 입장 인원</label>
            <div class="fs-6" id="maxJoinCount">-</div>
          </div>
          <div class="col-md-6">
            <label class="text-secondary mb-1">시작 시간</label>
            <div class="fs-6" id="videoStartTime">-</div>
          </div>
          <div class="col-md-6">
            <label class="text-secondary mb-1">종료 시간</label>
            <div class="fs-6" id="videoEndTime">-</div>
          </div>
        </div>
      </div>

      <hr class="my-4">

      <div>
        <h5 class="fw-bold mb-3">지원자 리스트</h5>
        <div class="table-responsive">
          <table class="table align-middle table-bordered table-hover">
            <thead class="table-light">
              <tr>
                <th>지원자 이름</th>
                <th>이력서</th>
                <th>면접 시간</th>
                <th>면접 점수</th>
              </tr>
            </thead>
            <tbody id="applicantList">
              <!-- JS로 데이터 렌더 -->
              <!-- <tr>
                <td>홍길동</td>
                <td><a href="#">이력서 보기</a></td>
                <td>2025-07-14 14:10</td>
                <td>92점</td>
              </tr> -->
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>

</body>