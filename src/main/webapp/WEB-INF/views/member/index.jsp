<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/main.min.css" rel="stylesheet">
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.18/index.global.min.js'></script>
<script defer src="/js/member/mypage.js"></script>
</head>
<body>
	<div id="calendar"></div>

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
</body>
