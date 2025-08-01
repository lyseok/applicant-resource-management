<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <head>
    <title>지원자 관리</title>
    <script src="/js/config.js"></script>
    <script defer src="/js/member/project/announcement/applicant/projectApplicant.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/nouislider@15.7.1/dist/nouislider.min.css">
    <script src="https://cdn.jsdelivr.net/npm/nouislider@15.7.1/dist/nouislider.min.js"></script>
    <style>
      .form-select {
        width: 10em;
        min-width: 90px;
        max-width: 180px;
        display: inline-block;
      }

      th,
      td {
        padding: 4px 6px !important;
      }

      .nav-link {
        padding: 4px 10px;
        font-size: 13px;
      }
    </style>
  </head>

  <body>
    <p class="h2 mb-4 fw-bold"> 지원자 관리</p>

    <!-- 필터 영역 -->
    <div class="">

      <!-- 1번째 줄: 보유기술 -->
      <div class="mb-4">
        <p class="h6 fw-bold">보유기술</p>
        <div id="skillTagFilter" class="badge-tag-wrap">
          <!-- JS에서 태그형 생성 -->
        </div>
      </div>

      <!-- 2번째 줄: 지원 부문 -->
      <div class="mb-4">
        <p class="h6 fw-bold">지원 부문</p>
        <!-- 탭 네비게이션 -->
        <ul class="nav mb-2 badge-tag-wrap" id="recruitTabNav"></ul>
      </div>
    </div>

    <!-- 2번째 줄: 이름검색(왼) / 버튼(오른) -->
    <div class="d-flex justify-content-between align-items-center">
      <div class="d-flex flex-column align-items-start gap-1">
        <button class="btn btn_violet_line btn-sm fs-12" id="btnRequestParticipation">참여 요청</button>
        <span class="text-muted fw-500 fs-13">지원완료 상태인 지원자에게 참여요청을 보낼 수 있습니다.</span>
      </div>

      <div class="TypoBox searchBar2">
        <div class="searchBarWrap">
          <label class="searchBarLabel" for="listKeyword">검색어</label>
          <input type="text" class="searchBarInput" id="searchInput" placeholder="지원자 이름 검색">
        </div>

        <div class="btn_box">
          <a class="searchBarBtn" id="searchBtn" type="button">
            <span class="material-symbols-outlined">search</span>
          </a>
          <button class="" id="resetFilters" type="button">
            <span class="material-symbols-outlined fw-300">close</span>
          </button>
        </div>
      </div>
    </div>


    <!-- 단계별 탭 & 버튼 한 줄에 배치 -->
    <div class="row mb-2 align-items-center">
      <div class="col-auto ms-auto d-flex gap-2">
      </div>
    </div>


    <!-- 탭 내용(지원자 테이블) -->
    <div class="tab-content p-0" id="recruitTabContent"></div>

    <!-- 단계별 탭 & 버튼 한 줄에 배치 -->
    <div class="col-auto ms-auto gap-2 mt-3 container">
      <button class="btn btn_violet w140 justify-content-center fs-14" id="createBtn">프로젝트 생성</button>
    </div>


    <!-- 프로젝트 지원 모달 -->
    <div class="modal fade" id="applicationModal" tabindex="-1">
      <div class="modal-dialog modal-dialog-centered" style="max-width: 450px;">
        <div class="modal-content">
          <!-- 헤더 -->
          <div class="modal-header bg-white">
            <h5 class="modal-title fs-5 fw-bold text-success" id="modalProjectTitle">프로젝트 제목</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>
          <!-- 프로젝트명 입력 -->
          <div class="px-4 pb-2 pt-3">
            <label for="inputProjectName" class="form-label mb-1 fs-14 fw-500">프로젝트명</label>
            <input type="text" id="inputProjectName" class="form-control fs-13" placeholder="프로젝트명을 입력하세요"
              maxlength="30" autocomplete="off">
          </div>
          <!-- 하단 버튼 -->
          <div class="modal-footer bg-white">
            <button id="createProjectBtn" class="btn w-100 btn_violet justify-content-center">생성</button>
          </div>
        </div>
      </div>
    </div>
  </body>