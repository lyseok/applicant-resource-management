<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>

    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
    <!-- FullCalendar CSS -->
    <link href='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.css' rel='stylesheet' />
    <script defer src="/js/member/mypage.js"></script>

    <style>
      .mypage-body {
        font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
        background-color: #f8f9fa;
      }

      .mypage-body .layout-demo-wrapper {
        background-color: #f8f9fa;
        box-shadow: none;
      }

      * 프로필 섹션 */ .profile-section {
        background: white;
        border-radius: 12px;
        padding: 24px;
        box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
      }

      .profile-avatar i {
        font-size: 48px;
        color: #e9ecef;
      }

      .user-name {
        font-size: 24px;
        font-weight: 600;
        color: #333;
        margin: 0;
      }

      .user-job {
        font-size: 14px;
        color: #6c757d;
      }

      .resume-progress-card {
        border-radius: 8px;
        padding: 16px;
        margin-top: 16px;
      }

      .resume-label {
        color: #333;
        font-weight: 500;
      }

      .completion-rate {
        color: #28a745;
        font-weight: 600;
      }

      .update-link {
        color: #28a745;
        text-decoration: none;
        font-size: 14px;
        font-weight: 500;
      }

      .update-link:hover {
        color: #218838;
      }

      /* 상태 카드 */
      .status-card {
        background: white;
        border-radius: 12px;
        padding: 24px;
        /* box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1); */
        height: 100%;
        box-shadow: 1px 8px 50px 0px rgba(0, 0, 0, 0.06);
      }

      .card-title {
        font-size: 18px;
        font-weight: 600;
        color: #333;
        margin-bottom: 24px;
      }

      .status-number {
        font-size: 32px;
        font-weight: 700;
        color: #333;
        margin-bottom: 8px;
      }

      .status-label {
        font-size: 12px;
        color: #6c757d;
        line-height: 1.2;
      }

      /* 추천 섹션 */
      .recommendations-section {
        margin-bottom: 32px;
      }

      .section-title {
        font-size: 18px;
        font-weight: 600;
        color: #333;
        margin: 0;
      }

      .more-link {
        color: #28a745;
        text-decoration: none;
        font-size: 14px;
        font-weight: 500;
      }

      .more-link:hover {
        color: #218838;
      }

      /* 추천 공고 카드 */
      .job-card {
        background: white;
        border-radius: 12px;
        overflow: hidden;
        box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        transition: transform 0.2s, box-shadow 0.2s;
        cursor: pointer;
        height: 100%;
      }

      .job-card:hover {
        transform: translateY(-2px);
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
      }

      .job-image {
        height: 120px;
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        position: relative;
        display: flex;
        align-items: center;
        justify-content: center;
      }

      .job-image.yellow {
        background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
      }

      .job-image.purple {
        background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
      }

      .job-image.blue {
        background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%);
      }

      .job-badge {
        position: absolute;
        top: 8px;
        left: 8px;
        background: rgba(0, 0, 0, 0.7);
        color: white;
        padding: 4px 8px;
        border-radius: 12px;
        font-size: 11px;
        font-weight: 500;
      }

      .company-logo {
        width: 40px;
        height: 40px;
        background: white;
        border-radius: 8px;
        display: flex;
        align-items: center;
        justify-content: center;
        font-weight: bold;
        color: #333;
      }

      .job-content {
        padding: 16px;
      }

      .job-title {
        font-size: 14px;
        font-weight: 600;
        color: #333;
        margin-bottom: 4px;
        line-height: 1.3;
      }

      .company-name {
        font-size: 12px;
        color: #6c757d;
        margin: 0;
      }

      .bookmark-btn {
        position: absolute;
        top: 8px;
        right: 8px;
        background: rgba(255, 255, 255, 0.9);
        border: none;
        width: 28px;
        height: 28px;
        border-radius: 6px;
        display: flex;
        align-items: center;
        justify-content: center;
        color: #6c757d;
        cursor: pointer;
        transition: all 0.2s;
      }

      .bookmark-btn:hover {
        background: white;
        color: #28a745;
      }

      /* 액션 카드 */
      .action-card {
        background: white;
        border-radius: 12px;
        box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        height: 100%;
      }

      .action-card .card-header {
        background: none;
        border-bottom: 1px solid #e9ecef;
        padding: 16px 20px;
      }

      .action-card .card-title {
        font-size: 14px;
        font-weight: 600;
        color: #333;
        margin: 0;
      }

      .action-card .card-body {
        padding: 40px 20px;
      }

      .empty-state {
        text-align: center;
      }

      .empty-icon {
        font-size: 48px;
        color: #e9ecef;
        margin-bottom: 16px;
      }

      .empty-text {
        color: #6c757d;
        font-size: 14px;
        margin-bottom: 20px;
      }

      .action-btn {
        background-color: #28a745;
        border-color: #28a745;
        font-size: 14px;
        font-weight: 500;
        padding: 8px 20px;
        border-radius: 6px;
      }

      .action-btn:hover {
        background-color: #218838;
        border-color: #1e7e34;
      }

      /* 캘린더 스타일 */
      #calendar {
        max-width: 100%;
      }

      .fc-theme-standard td,
      .fc-theme-standard th {
        border-color: #e9ecef;
      }

      .fc-button-primary {
        background-color: #28a745;
        border-color: #28a745;
      }

      .fc-button-primary:hover {
        background-color: #218838;
        border-color: #1e7e34;
      }

      .fc-event {
        background-color: #28a745;
        border-color: #28a745;
      }

      /* 반응형 */
      @media (max-width: 768px) {
        .container {
          padding: 16px;
        }

        .profile-section {
          padding: 16px;
        }

        .status-card {
          padding: 16px;
          margin-bottom: 16px;
        }

        .status-number {
          font-size: 24px;
        }

        .user-name {
          font-size: 20px;
        }

        .action-card .card-body {
          padding: 24px 16px;
        }
      }

      @media (max-width: 576px) {
        .status-label {
          font-size: 11px;
        }

        .job-content {
          padding: 12px;
        }

        .job-title {
          font-size: 13px;
        }
      }
    </style>
  </head>

  <body class="mypage-body">
    <div class="container-fluid bg-light min-vh-100">
      <div class="container py-4">
        <!-- 사용자 프로필 섹션 -->
        <div class="profile-section mb-4 status-card">
          <div class="d-flex align-items-center mb-3">
            <div class="profile-avatar me-3">
              <i class="bi bi-person-circle"></i>
            </div>
            <div>
              <h2 class="user-name mb-1">이윤석 <i class="bi bi-chevron-right text-muted"></i></h2>
              <p class="user-job text-muted mb-0">신입 · 프론트엔드 개발</p>
            </div>
          </div>

          <!-- 이력서 완성률 -->
          <div class="resume-progress-card">
            <div class="d-flex justify-content-between align-items-center">
              <div>
                <span class="resume-label">대표 이력서 </span>
                <!-- <span class="completion-rate">완성률 70%</span> -->
              </div>
              <a href="#" class="update-link">이력서 업데이트 <i class="bi bi-chevron-right"></i></a>
            </div>
          </div>
        </div>

        <!-- 지원 현황 및 받은 제안 -->
        <div class="row mb-4">
          <div class="col-md-6 mb-3">
            <div class="status-card">
              <h5 class="card-title mb-4">지원 현황</h5>
              <div class="row text-center">
                <div class="col-3">
                  <div class="status-number">0</div>
                  <div class="status-label">지원 완료</div>
                </div>
                <div class="col-3">
                  <div class="status-number">0</div>
                  <div class="status-label">서류 합격</div>
                </div>
                <div class="col-3">
                  <div class="status-number">0</div>
                  <div class="status-label">최종 합격</div>
                </div>
                <div class="col-3">
                  <div class="status-number">0</div>
                  <div class="status-label">불합격</div>
                </div>
              </div>
            </div>
          </div>

          <div class="col-md-6 mb-3">
            <div class="status-card">
              <h5 class="card-title mb-4">받은 제안</h5>
              <div class="row text-center">
                <div class="col-3">
                  <div class="status-number">0</div>
                  <div class="status-label">포지션 제안</div>
                </div>
                <div class="col-3">
                  <div class="status-number">0</div>
                  <div class="status-label">나에 대한 관심</div>
                </div>
                <div class="col-3">
                  <div class="status-number">0</div>
                  <div class="status-label">이력서 열람</div>
                </div>
                
              </div>
            </div>
          </div>
        </div>

        <!-- 프로젝트 정보 -->
        <div class="row mb-4">
          <div class="col-12">
            <div class="status-card">
              <h5 class="card-title mb-4">프로젝트 정보</h5>
              <div class="row text-center">
                <div class="col-3">
                  <div class="status-number" id="totalProjects">0</div>
                  <div class="status-label">총 프로젝트 수</div>
                </div>
                <div class="col-3">
                  <div class="status-number" id="ongoingProjects">0</div>
                  <div class="status-label">진행중인 프로젝트</div>
                </div>
                <div class="col-3">
                  <div class="status-number" id="completedProjects">0</div>
                  <div class="status-label">종료된 프로젝트</div>
                </div>
                <div class="col-3">
                  <div class="status-number" id="myPostings">0</div>
                  <div class="status-label">내가 올린 공고</div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 캘린더 -->
        <div class="row mb-4">
          <div class="col-12">
            <div class="status-card">
              <h5 class="card-title mb-4">내 일정</h5>
              <div id="calendar"></div>
            </div>
          </div>
        </div>

        <!-- 추천 공고 -->
        <div class="recommendations-section mb-4">
          <div class="d-flex justify-content-between align-items-center mb-3">
            <h5 class="section-title">프론트엔드 개발에서 많이 봤어요</h5>
            <a href="#" class="more-link">더 보기 <i class="bi bi-chevron-right"></i></a>
          </div>

          <div class="row" id="recommendedJobs">
            <!-- 추천 공고들이 여기에 동적으로 추가됩니다 -->
          </div>
        </div>

        <!-- 하단 섹션 -->
        <div class="row">
          <div class="col-md-4 mb-3">
            <div class="action-card">
              <div class="card-header">
                <h6 class="card-title">저장한 공고 <i class="bi bi-chevron-right"></i></h6>
              </div>
              <div class="card-body text-center">
                <div class="empty-state">
                  <i class="bi bi-bookmark empty-icon"></i>
                  <p class="empty-text">저장한 공고가 없어요</p>
                  <button class="btn btn-success action-btn">채용 공고 탐색</button>
                </div>
              </div>
            </div>
          </div>

          <div class="col-md-4 mb-3">
            <div class="action-card">
              <div class="card-header">
                <h6 class="card-title">최근 본 공고 <i class="bi bi-chevron-right"></i></h6>
              </div>
              <div class="card-body text-center">
                <div class="empty-state">
                  <i class="bi bi-clock-history empty-icon"></i>
                  <p class="empty-text">최근 본 공고가 없어요</p>
                  <button class="btn btn-success action-btn">채용 공고 탐색</button>
                </div>
              </div>
            </div>
          </div>

          <div class="col-md-4 mb-3">
            <div class="action-card">
              <div class="card-header">
                <h6 class="card-title">팔로잉 기업 <i class="bi bi-chevron-right"></i></h6>
              </div>
              <div class="card-body text-center">
                <div class="empty-state">
                  <i class="bi bi-heart empty-icon"></i>
                  <p class="empty-text">팔로우한 기업이 없어요</p>
                  <button class="btn btn-success action-btn">기업 둘러보기</button>
                </div>
              </div>
            </div>
          </div>


        </div>
      </div>
    </div>
    
    <!-- 일정 보기 Modal -->
<div class="modal fade" id="eventViewModal" tabindex="-1" aria-labelledby="eventViewModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="eventViewModalLabel">일정 상세 보기</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
      </div>
      <div class="modal-body">
        <div class="mb-3">
          <label for="modalTitleInput" class="form-label">제목</label>
          <input type="text" class="form-control" id="modalTitleInput">
        </div>
        <div class="mb-3">
          <label for="modalStartInput" class="form-label">시작 시간</label>
          <input type="datetime-local" class="form-control" id="modalStartInput">
        </div>
        <div class="mb-3">
          <label for="modalEndInput" class="form-label">종료 시간</label>
          <input type="datetime-local" class="form-control" id="modalEndInput">
        </div>
      </div>
      <div class="modal-footer" id="customControls">
        <button type="button" class="btn btn-danger" id="deleteBtn">삭제</button>
        <button type="button" class="btn btn-primary" id="editBtn">수정</button>
      </div>
    </div>
  </div>
</div>

    <!-- FullCalendar JS -->
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js'></script>
  </body>

  </html>