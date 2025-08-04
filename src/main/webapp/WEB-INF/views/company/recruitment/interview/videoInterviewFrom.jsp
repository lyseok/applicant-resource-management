<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="jakarta.tags.core" prefix="c" %>

    <head>
      <meta charset="UTF-8">
      <title>화상 면접 추가</title>
      <link rel="stylesheet" href="/dist/assets/css/board/postList.css">
      <link rel="stylesheet" href="/dist/assets/css/board/profile.css">
      <link rel="stylesheet" href="/dist/assets/css/board/board_ui.css">
      <script defer src="/js/company/recruitment/interview/interviewCreate.js"></script>
    </head>


    <body>

      <h2 class="fw-bold h2 mb-5">화상면접 정보 등록</h2>

      <!-- 채용 공고 정보 -->
      <div class=" mb-4">
        <div class="d-flex gap-3 align-items-end">
          <h2 class="fw-bold h4 mb-0" id="noticeTitle">공고 명</h2>
          <div class="d-flex align-items-end gap-2">
            <h2 class="h5 text-muted mb-0" id="jobName"></h2>
            <div class="text-muted" id="hireCount">채용 인원수</div>
          </div>
        </div>

        <!-- 면접일시 -->
        <div class="text-muted w100p" id="interviewDate"></div>
      </div>

      <!-- 채용 공고 정보 -->
      <form id="videoInterviewForm">
        <div class="d-flex flex-wrap gap-3">
          <input type="text" class="form-control" id="videoInterviewNo" name="videoInterviewNo" hidden>
          <input type="text" class="form-control" id="interviewNo" name="interviewNo" hidden>
          <input type="text" class="form-control" id="companyInterviewUrl" name="companyInterviewUrl" maxlength="255"
            hidden>

          <div class="w-calc100-16-2">
            <label class="form-label fs-16 fw-bold text-dark required" for="roomTitle">화상 면접 방 제목</label>
            <input type="text" class="form-control" id="roomTitle" name="roomTitle" maxlength="240">
          </div>
          <div class="w-calc100-16-2">
            <label class="form-label fs-16 fw-bold text-dark required" for="maxJoinCount">최대 입장자 수</label>
            <input type="number" class="form-control" id="maxJoinCount" name="maxJoinCount" min="1">
          </div>
          <div class="w-calc100-48-4">
            <label class="form-label fs-16 fw-bold text-dark required" for="startDate">화상면접 시작시간</label>
            <input type="datetime-local" class="form-control" id="startDate" name="startDate">
          </div>
          <div class="w-calc100-48-4">
            <label class="form-label fs-16 fw-bold text-dark required" for="endDate">화상면접 종료시간</label>
            <input type="datetime-local" class="form-control" id="endDate" name="endDate">
          </div>
        </div>

        <!-- 지원자별 면접시간 지정 -->
        <div class="mt-5">
          <div class="fw-bold h4 mb-3">지원자별 면접 시간 지정</div>
          <div class="table-responsive">
            <table class="table align-middle text-center border-top border-top-dark">
              <thead class="text-center">
                <tr>
                  <th class="text-center fw-semibold lh1 fs-14 py-3 align-middle text-dark">지원자 이름</th>
                  <th class="text-center fw-semibold lh1 fs-14 py-3 align-middle text-dark">이력서</th>
                  <th class="text-center fw-semibold lh1 fs-14 py-3 align-middle text-dark">면접 시간 지정</th>
                </tr>
              </thead>
              <tbody id="applicantTimeList" class="border-top-0 ">
                <!-- JS로 한줄씩 렌더링 예시 -->
              </tbody>
            </table>
          </div>
        </div>

        <div class="d-flex justify-content-center gap-2 mt-5">
          <button type="button" class="btn btn_gray_line px-4" data-bs-toggle="modal"
            data-bs-target="#cancelInterviewModal">취소</button>
          <button type="submit" class="btn btn_violet w140 justify-content-center">등록</button>
        </div>
      </form>

      <!-- 취소 확인 모달 -->
      <div class="modal fade" id="cancelInterviewModal" tabindex="-1" aria-labelledby="deleteModalLabel"
        aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
            <div class="modal-header border-0">
              <h1 class="modal-title fs-5 fw-bold text-danger" id="deleteModalLabel">
                <i class="bi bi-exclamation-triangle-fill me-1"></i> 화상 면접 등록 취소
              </h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-center py-4">
              <div style="font-size:2.4rem; color:#dc3545; margin-bottom:10px;">❗</div>
              <p class="fs-5 mb-2 fw-semibold" style="color:#c82333;">
                해당 면접 정보 등록을 <span style="color:#dc3545;">정말 취소</span>하시겠습니까?
              </p>
              <p class="text-secondary mb-0" style="font-size:1.08rem;">
                취소된 데이터는 복구할 수 없습니다.<br>
                실행 전 반드시 다시 한 번 확인해 주세요.
              </p>
            </div>
            <div class="modal-footer border-0 justify-content-center">
              <button type="button" class="btn btn-outline-secondary px-4" data-bs-dismiss="modal">등록 계속</button>
              <button type="button" class="btn btn-danger px-4" id="cancelBtn">등록 취소</button>
            </div>
          </div>
        </div>
      </div>

      <!-- 로딩 스피너 (기본은 숨김) -->
      <!-- <div id="loadingSpinner" class="position-fixed top-0 start-0 w-100 h-100 d-flex justify-content-center align-items-center"
    style="background:rgba(255,255,255,0.5); z-index:1055; display:none !important; ">
  <div class="text-center">
    <div class="spinner-border text-success mb-3" style="width: 4rem; height: 4rem;" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
    <div class="fw-bold fs-4 text-success">로딩중입니다...</div>
  </div>
</div> -->
      <script type="module">
        import { videoInterviewData } from '/js/dataConf.js';
        // 제목 클릭 시 기본 값 채우기
        document.getElementById('noticeTitle').addEventListener('click', () => {
          const now = new Date();
          const pad = (n) => (n < 10 ? '0' + n : n);
          const formatDate = (d) => {
            return (
              d.getFullYear() +
              '-' +
              pad(d.getMonth() + 1) +
              '-' +
              pad(d.getDate()) +
              'T' +
              pad(d.getHours()) +
              ':' +
              pad(d.getMinutes())
            );
          };

          const startDate = formatDate(now);
          const endDate = formatDate(new Date(now.getTime() + 24 * 60 * 60 * 1000));

          document.getElementById('roomTitle').value = videoInterviewData.roomTitle;
          document.getElementById('maxJoinCount').value = videoInterviewData.maxJoinCount;
          document.getElementById('startDate').value = startDate;
          document.getElementById('endDate').value = endDate;

          // applicantTimeList 내부 모든 면접 시간 input을 현재 시간으로 설정
          document.querySelectorAll('#applicantTimeList input.form-control[name^="applicantTime_"]').forEach((input) => {
            input.value = startDate;
          });
        });


      </script>
    </body>