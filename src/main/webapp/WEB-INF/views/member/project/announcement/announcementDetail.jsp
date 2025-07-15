<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
  <meta charset="UTF-8">
  <title>프로젝트 공고 상세</title>
  <style>
    .dot {
      width: 8px;
      height: 8px;
      background: #bbb;
      border-radius: 50%;
      display: inline-block;
      margin-right: 12px;
    }

    .section-title {
      font-size: 1.17rem;
      font-weight: 600;
      margin-bottom: 1rem;
    }

    .badge-recruit {
      background: #22c55e;
    }

    .shadow-card {
      box-shadow: 0 4px 32px 0 rgba(0, 0, 0, 0.09);
    }

    .list-dot li {
      display: flex;
      align-items: center;
      gap: 10px;
    }

    .list-dot .dot {
      margin: 0;
    }

    .btn-purple {
      background: #9333ea;
      color: #fff;
    }

    .btn-purple:hover {
      background: #7c2dd6;
      color: #fff;
    }

    .text-purple {
      color: #ae8be1 !important;
    }

    .bg-purple {
      background: #ae8be1 !important;
    }

    .selected-card {
      border: 2px solid #ae8be1 !important;
      background: #f6f2ff !important;
    }

    .resume-card {
      cursor: pointer;
      border: 1.5px solid #eee;
    }

    .resume-card:hover {
      border-color: #ae8be1 !important;
    }
  </style>
  <script defer src="/js/member/project/announcement/announcementDetail.js"></script>
</head>

<body style="background:#fafafc;">
  <div class="container py-5" style="max-width:800px;">
    <div class="card shadow-card border-0 mb-5" style="border-radius: 22px;">
      <div class="card-body px-5 py-4">

      </div>
    </div>
  </div>

  <!-- 프로젝트 지원 모달 -->
  <div class="modal fade" id="applicationModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered" style="max-width: 450px;">
      <div class="modal-content">
        <!-- 헤더 -->
        <div class="modal-header bg-white">
          <h5 class="modal-title text-purple fw-semibold" id="modalProjectTitle">프로젝트 제목</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>

        <!-- 본문 -->
        <div class="modal-body bg-light py-4 px-4">
          <!-- 지원부문 -->
          <div class="mb-4">
            <label class="form-label fw-medium text-secondary mb-1">지원부문</label>
            <select class="form-select" id="selectField">
              <option value="">지원 부문을 선택해주세요</option>
            </select>
          </div>

          <!-- 이력서 선택 -->
          <div class="mb-4">
            <div class="d-flex justify-content-between align-items-center mb-2">
              <label class="form-label fw-medium text-secondary">선택된 이력서</label>
              <button type="button" class="btn btn-link p-0 text-secondary" id="btnShowResumeList">이력서 변경
                &gt;</button>
            </div>
            <!-- 선택된 이력서 카드 -->
            <div id="selectedResumeCard"></div>
            <!-- 이력서 목록 (토글) -->
            <div id="resumeList" class="mt-2 overflow-auto" style="display: none; max-height:220px;"></div>
          </div>

          <!-- 첨부파일 -->
          <div class="mb-3">
            <div class="d-flex justify-content-between align-items-center mb-2">
              <label class="form-label fw-medium text-secondary">첨부파일 0건</label>
              <button class="btn btn-outline-secondary btn-sm" type="button">
                <i class="bi bi-plus-lg"></i> 파일추가
              </button>
            </div>
            <div class="text-center py-4 text-secondary bg-white rounded border-2 border-dashed"
              style="font-size: .97em;">
              첨부된 파일이 없습니다.
            </div>
          </div>
        </div>

        <!-- 하단 버튼 -->
        <div class="modal-footer bg-white">
          <button id="btnSaveApplication" class="btn w-100 py-2 text-white"
            style="background: #ae8be1;">지원</button>
        </div>
      </div>
    </div>
  </div>

</body>