<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<head>
	<meta charset="UTF-8">
	<title>화상 면접 추가</title>
	<link rel="stylesheet" href="/dist/assets/css/board/postList.css">
	<link rel="stylesheet" href="/dist/assets/css/board/profile.css">
	<link rel="stylesheet" href="/dist/assets/css/board/board_ui.css">
	<script defer src="/js/company/recruitment/interview/interviewCreate.js"></script>
</head>


<body>

<div class="container my-5 ">
  <div class="bg-white rounded-4 mb-5 p-5">
    <div class="mb-4 pb-2 border-bottom">
      <h2 class="fw-bold h1 mb-4">화상면접 정보 등록</h2>
      <!-- 채용 공고 정보 -->
      <div class="mb-3">
        <div class="row gy-2">
          <div class="col-lg-6">
            <span class="fw-bold fs-5 text-secondary me-2">채용 공고명 :</span>
            <span id="noticeTitle" class="fs-5 text-dark"></span>
          </div>
          <div class="col-lg-6">
            <span class="fw-bold fs-5 text-secondary me-2">채용 직무명 :</span>
            <span id="jobName" class="fs-5 text-dark"></span>
          </div>
          <div class="col-lg-6">
            <span class="fw-bold fs-5 text-secondary me-2">채용 인원수 :</span>
            <span id="hireCount" class="fs-5 text-dark"></span>
          </div>
          <div class="col-lg-6">
            <span class="fw-bold fs-5 text-secondary me-2">면접 일시 :</span>
            <span id="interviewDate" class="fs-5 text-dark"></span>
          </div>
          <div class="col-lg-12">
            <span class="fw-bold fs-5 text-secondary me-2">면접 정보 :</span>
            <span id="interviewInfo" class="fs-5 text-dark"></span>
          </div>
        </div>
      </div>
      <!-- 채용 공고 정보 -->
    </div>
    <form id="videoInterviewForm">
      <div class="row gy-4">
        <input type="text" class="form-control" id="videoInterviewNo" name="videoInterviewNo" hidden>
        <input type="text" class="form-control" id="interviewNo" name="interviewNo" hidden>
        <input type="text" class="form-control" id="companyInterviewUrl" name="companyInterviewUrl" maxlength="255" hidden>

        <div class="col-md-6">
          <label class="fw-bold fs-5 text-secondary mb-1" for="roomTitle">화상 면접 방 제목</label>
          <input type="text" class="form-control" id="roomTitle" name="roomTitle" maxlength="240">
        </div>
        <div class="col-md-6">
          <label class="fw-bold fs-5 text-secondary mb-1" for="maxJoinCount">최대 입장자 수</label>
          <input type="number" class="form-control" id="maxJoinCount" name="maxJoinCount" min="1">
        </div>
        <div class="col-md-6">
          <label class="fw-bold fs-5 text-secondary mb-1" for="startDate">화상면접 시작시간</label>
          <input type="datetime-local" class="form-control" id="startDate" name="startDate" >
        </div>
        <div class="col-md-6">
          <label class="fw-bold fs-5 text-secondary mb-1" for="endDate">화상면접 종료시간</label>
          <input type="datetime-local" class="form-control" id="endDate" name="endDate" >
        </div>
      </div>

      <!-- 지원자별 면접시간 지정 -->
      <div class="mt-5">
        <div class="fw-bold fs-5 text-secondary mb-3">지원자별 면접 시간 지정</div>
        <div class="table-responsive">
          <table class="table align-middle table-bordered">
            <thead class="table-light">
              <tr>
                <th class="fw-bold fs-6 text-secondary mb-1">지원자 이름</th>
                <th class="fw-bold fs-6 text-secondary mb-1">이력서</th>
                <th class="fw-bold fs-6 text-secondary mb-1">면접 시간 지정</th>
              </tr>
            </thead>
            <tbody id="applicantTimeList">
              <!-- JS로 한줄씩 렌더링 예시 -->
            </tbody>
          </table>
        </div>
      </div>

      <div class="d-flex justify-content-end gap-2 mt-5">
        <button type="button" class="btn btn_gray_line px-4">취소</button>
        <button type="submit" class="btn btn_violet px-4">등록하기</button>
      </div>
    </form>
  </div>
</div>

</body>