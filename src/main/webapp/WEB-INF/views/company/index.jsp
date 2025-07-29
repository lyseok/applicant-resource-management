<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/main.min.css" rel="stylesheet">
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.18/index.global.min.js'></script>
<script defer src="/js/company/companyPage.js"></script>
</head>
<body>
	<div id="calendar"></div>

<!-- 모달 -->
<div class="modal fade" id="eventViewModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalTitle"></h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <p id="modalDateInfo"></p>
        <div id="recruitDateBlock">
            <p><strong>공고 시작일:</strong> <span id="modalRecruitStart"></span></p>
            <p><strong>마감일:</strong> <span id="modalRecruitFinish"></span></p>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>