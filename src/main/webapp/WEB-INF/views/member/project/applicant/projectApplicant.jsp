<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <head>
    <title>지원자 관리</title>
    <script defer src="/js/member/project/applicant/projectApplicant.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/nouislider@15.7.1/dist/nouislider.min.css">
    <script src="https://cdn.jsdelivr.net/npm/nouislider@15.7.1/dist/nouislider.min.js"></script>
    <style>
      body {
        font-size: 13px;
      }

      table {
        font-size: 13px;
      }

      .form-select,
      .form-control {
        font-size: 13px;
        padding: 2px 6px;
        height: 28px;
      }

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

      .btn-sm {
        padding: 2px 7px;
        font-size: 12px;
      }

      .nav-link {
        padding: 4px 10px;
        font-size: 13px;
      }

      /* 경력 슬라이더 noUiSlider 커스텀 */
      #careerSlider .noUi-base {
        height: 6px;
        /* 더 얇게 */
        background: #e9ecef;
      }

      #careerSlider .noUi-connect {
        background: #8bb4ff;
      }

      #careerSlider .noUi-handle {
        width: 18px;
        height: 18px;
        border-radius: 50%;
        background: #fff;
        border: 2px solid #0d6efd;
        box-shadow: 0 1px 6px rgba(0, 0, 0, 0.08);
        top: -7px;
        /* 바 가운데 맞춤 */
        cursor: grab;
        transition: border 0.2s;
      }

      #careerSlider .noUi-handle:hover {
        border: 2.5px solid #296ed7;
      }

      #careerSlider .noUi-tooltip {
        font-size: 12px;
        background: #0d6efd;
        color: #fff;
        border-radius: 4px;
        padding: 1px 7px;
      }

      #careerSlider .noUi-value {
        font-size: 12px;
        color: #296ed7;
        font-weight: 600;
        top: -22px;
        /* 필요시 조절 */
      }

    </style>
  </head>

  <body>
    <div class="container mt-3">
      <p class="h2 mb-4 fw-bold"> 지원자 관리</p>

      <!-- 필터 영역 -->
      <!-- 1번째 줄: 보유기술 -->
      <div class="row mb-2 g-2">
        <div class="col">
          <p class="h5">보유기술</p>
          <div id="skillTagFilter" class="d-flex flex-wrap gap-1">
            <!-- JS에서 태그형 생성 -->
          </div>
        </div>
      </div>

      <!-- 2번째 줄: 이름검색(왼) / 버튼(오른) -->
      <div class="row mb-3 g-2">
        <div class="col d-flex align-items-center">
          <input type="text" class="form-control" id="searchInput" placeholder="지원자 이름 검색" style="width:200px;">
        </div>
        <div class="col-auto ms-auto d-flex align-items-center gap-2">
          <button class="btn btn-primary" id="searchBtn" type="button">검색</button>
          <button class="btn btn-secondary" id="resetFilters" type="button">초기화</button>
        </div>
      </div>


      <!-- 단계별 탭 & 버튼 한 줄에 배치 -->
      <div class="row mb-2 align-items-center">
        <div class="col">
          <!-- 탭 네비게이션 -->
          <ul class="nav nav-tabs mb-2" id="recruitTabNav"></ul>
        </div>
        <div class="col-auto ms-auto d-flex gap-2">
          <button class="btn btn-outline-primary" id="btnRequestParticipation">참여 요청</button>
        </div>
      </div>

    </div>

    <!-- (상단 필터/검색/정렬/슬라이더 등은 그대로 둠) -->

    <!-- 탭 내용(지원자 테이블) -->
    <div class="tab-content" id="recruitTabContent"></div>
  </body>