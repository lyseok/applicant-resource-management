<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <head>
    <title>채용 단계별 지원자 관리</title>
    <script>
  		const recruitmentNo = "${recruitmentNo}";
	</script>
    <script defer src="/js/company/recruitment/applicantRecord/applicantRecordList.js"></script>
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
      <p class="h1 mb-4 fw-bold">채용 단계별 지원자 관리</p>

      <!-- 필터 영역 -->
      <!-- 필터 1줄차: 경력/어학/전공/자격증 -->
      <div class="row mb-2 align-items-end flex-nowrap" style="width:100%;">
        <div class="col-auto d-flex align-items-center" style="gap:18px;">
          <!-- 경력(년) + 슬라이더 한 줄 -->
          <div style="min-width:220px; display:flex; align-items:center; gap:8px;">
            <label class="form-label mb-0 me-2" style="white-space:nowrap;">경력(년)</label>
            <div id="careerSlider" style="width:160px;"></div>
            <div id="careerRangeText" style="font-weight:500; color:#296ed7; font-size:12px; margin-bottom:0; margin-left:6px;"></div>
          </div>
          <!-- 어학 -->
          <div style="min-width:150px; display:flex; align-items:center; gap:6px;">
            <label for="languageFilter" class="form-label mb-0 me-2">어학</label>
            <select class="form-select" id="languageFilter"></select>
          </div>
          <!-- 전공 -->
          <div style="min-width:150px; display:flex; align-items:center; gap:6px;">
            <label for="majorFilter" class="form-label mb-0 me-2">전공</label>
            <select class="form-select" id="majorFilter"></select>
          </div>
          <!-- 자격증 -->
          <div style="min-width:150px; display:flex; align-items:center; gap:6px;">
            <label for="certFilter" class="form-label mb-0 me-2">자격증</label>
            <select class="form-select" id="certFilter"></select>
          </div>
        </div>
      </div>

      <!-- 2번째 줄: 보유기술 -->
      <div class="row mb-2 g-2">
        <div class="col">
          <label class="form-label">보유기술</label>
          <div id="skillTagFilter" class="d-flex flex-wrap gap-1">
            <!-- JS에서 태그형 생성 -->
          </div>
        </div>
      </div>

      <!-- 3번째 줄: 이름검색(왼) / 버튼(오른) -->
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
          <ul class="nav nav-tabs" style="margin-bottom:0;">
			<!--  1차 2차 3차... 붙이기 -->
			 
          </ul>
        </div>
        <div class="col-auto ms-auto d-flex gap-2">
          <button class="btn btn-outline-secondary btn-sm" onclick="sortTable('career')">경력순</button>
          <button class="btn btn-outline-secondary btn-sm" onclick="sortTable('score')">점수순</button>
          <button class="btn btn-outline-success btn-sm" onclick="savePassStatus()">저장</button>
          <button class="btn btn-outline-primary btn-sm" id="stepActionBtn" onclick="closeStep()">단계 마감</button>
        </div>
      </div>


      <!-- 테이블 -->
      <div class="tab-pane fade show active" id="step1" role="tabpanel">
        <table class="table table-sm table-bordered align-middle text-center" id="mainTable">
          <thead>
            <tr></tr> <!-- JS에서 컬럼 삽입 -->
          </thead>
          <tbody>
            <!-- JS에서 데이터 렌더링 -->
          </tbody>
        </table>
      </div>
    </div>
   
  </body>