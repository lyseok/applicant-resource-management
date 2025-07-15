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

    .text-purple { color: #ae8be1 !important; }
    .bg-purple { background: #ae8be1 !important; }
    .selected-card { border:2px solid #ae8be1 !important; background: #f6f2ff !important;}
    .resume-card { cursor:pointer; border: 1.5px solid #eee;}
    .resume-card:hover { border-color: #ae8be1 !important; }
  </style>
  <script defer src="/js/member/project/announcement/announcementDetail.js" ></script>
</head>

<body style="background:#fafafc;">
  <div class="container py-5" style="max-width:800px;">
    <div class="card shadow-card border-0 mb-5" style="border-radius: 22px;">
      <div class="card-body px-5 py-4">
        <!-- 제목/상태 -->
        <div class="d-flex flex-wrap align-items-start justify-content-between mb-2">
          <h2 class="fw-bold mb-0 text-dark" style="font-size:1.7rem;">사이드 프로젝트 백엔드 크루 모집</h2>
          <span class="badge badge-recruit text-white fs-6 py-2 px-4">모집중</span>
        </div>

        <!-- 작성자 정보 -->
        <div class="d-flex align-items-center justify-content-between mb-4 flex-wrap">
          <div>
            <div class="fw-medium" style="font-size:1.1rem;">나나랩</div>
            <div class="text-secondary small mt-1 d-flex align-items-center gap-3 flex-wrap">
              <span>작성일 25.06.23 12:10</span>
              <span>수정일 25.06.23 13:22</span>
              <span class="d-flex align-items-center gap-1"><i class="bi bi-eye"></i>조회수 94</span>
            </div>
          </div>
          <div class="d-flex align-items-center gap-2">
            <button class="btn btn-outline-secondary btn-sm px-3 d-flex align-items-center gap-1"><i
                class="bi bi-share"></i>공유</button>
            <button class="btn btn-outline-secondary btn-sm px-3 d-flex align-items-center gap-1"><i
                class="bi bi-heart"></i>0</button>
          </div>
        </div>

        <!-- 프로젝트 소개 -->
        <div class="mb-5">
          <div class="section-title">프로젝트 소개</div>
          <div class="text-secondary" style="font-size:1.05em;">
            Z세대의 친구 중심 감정 공유를 위한 소셜 플랫폼을 만들고 있습니다.
          </div>
        </div>
        <hr />

        <!-- 팀 구성 -->
        <div class="mb-5">
          <div class="section-title">현재 팀 구성</div>
          <ul class="list-unstyled list-dot mb-2">
            <li><span class="dot"></span>기획(1)</li>
            <li><span class="dot"></span>디자인(1)</li>
            <li><span class="dot"></span>프론트엔드(2)</li>
          </ul>
          <div class="mt-3 text-secondary">→ 현재 <span class="fw-bold text-dark">백엔드 1명</span>을 새로이 모십니다!
          </div>
        </div>
        <hr />

        <!-- 기술스택 & 업무 내용 -->
        <div class="mb-5">
          <div class="section-title">기술 스택 &amp; 업무 내용</div>
          <ul class="list-unstyled list-dot">
            <li><span class="dot"></span>JavaScript 기반 (Node.js 등 자유롭게 제안 가능)</li>
            <li><span class="dot"></span>API 설계, DB 구조 설계, 인증/소셜 시스템 개발</li>
          </ul>
        </div>
        <hr />

        <!-- 진행상황 및 목표 -->
        <div class="mb-5">
          <div class="section-title">진행 상황 및 목표</div>
          <ul class="list-unstyled list-dot">
            <li><span class="dot"></span>1차 스코프 기획 완료, 디자인 진행 중</li>
            <li><span class="dot"></span>9월 내 1차 릴리즈 목표</li>
            <li><span class="dot"></span>동시에 다른 기획/디자인이 이어집니다</li>
          </ul>
        </div>
        <hr />

        <!-- PM 이력서 버튼 -->
        <div class="d-flex justify-content-center mb-3">
          <button class="btn btn-purple px-5 py-2 fw-semibold">PM 이력서 확인</button>
        </div>
      </div>
    </div>

    <!-- 하단 버튼 -->
    <div class="d-flex justify-content-center gap-3">
      <a href="/board/project" class="btn btn-outline-secondary px-5 py-2">목록</a>
      <button class="btn btn-purple px-5 py-2" onclick="openApplicationModal('프론트엔드 크루 모집')">지원</button>
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
            <button type="button" class="btn btn-link p-0 text-secondary" id="btnShowResumeList">이력서 변경 &gt;</button>
          </div>
          <!-- 선택된 이력서 카드 -->
          <div id="selectedResumeCard"></div>
          <!-- 이력서 목록 (토글) -->
          <div id="resumeList" class="mt-2" style="display: none;"></div>
        </div>

        <!-- 첨부파일 -->
        <div class="mb-3">
          <div class="d-flex justify-content-between align-items-center mb-2">
            <label class="form-label fw-medium text-secondary">첨부파일 0건</label>
            <button class="btn btn-outline-secondary btn-sm" type="button">
              <i class="bi bi-plus-lg"></i> 파일추가
            </button>
          </div>
          <div class="text-center py-4 text-secondary bg-white rounded border-2 border-dashed" style="font-size: .97em;">
            첨부된 파일이 없습니다.
          </div>
        </div>
      </div>

      <!-- 하단 버튼 -->
      <div class="modal-footer bg-white">
        <button id="btnSaveApplication" class="btn w-100 py-2 text-white" style="background: #ae8be1;">지원</button>
      </div>
    </div>
  </div>
</div>

</body>