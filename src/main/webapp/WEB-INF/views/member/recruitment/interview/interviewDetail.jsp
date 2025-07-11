<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<meta charset="UTF-8">
	<title>면접 관리</title>
	<script defer src="/js/member/recruitment/interview/interviewList.js" ></script>
	<style>
		li[data-notice]:hover {
			cursor: pointer;
			transition: background 0.2s, box-shadow 0.2s;
		}
	</style>
</head>

<body>
	
<div class="container my-5">
  <div class="card shadow p-4">
    <!-- 채용공고 기본정보 -->
    <h3 class="fw-bold mb-4" style="color:#8d4dff;">채용공고 정보</h3>
    <div class="row mb-4">
      <div class="col-md-6">
        <p class="mb-1"><strong>회사명</strong> : 네이버</p>
        <p class="mb-1"><strong>공고명</strong> : 백엔드 개발자 모집</p>
        <p class="mb-1"><strong>직무</strong> : 백엔드/서버개발</p>
      </div>
      <div class="col-md-6">
        <p class="mb-1"><strong>지원자수</strong> : 5명</p>
        <p class="mb-1"><strong>담당자</strong> : 김채용 (010-1234-5678)</p>
        <p class="mb-1"><strong>채용 마감일</strong> : 2025-07-31</p>
      </div>
    </div>
    <hr>
    <!-- 면접 정보 -->
    <h4 class="fw-bold mb-3" style="color:#8d4dff;">면접 정보</h4>
    <div class="row mb-4">
      <div class="col-md-4">
        <p><strong>면접 일시</strong> : 2025-07-14 14:00:00</p>
        <p><strong>면접 장소</strong> : 서울 본사 3층</p>
        <p><strong>면접 유형</strong> : <span class="badge bg-violet text-white">화상면접</span></p>
      </div>
      <div class="col-md-4">
        <p><strong>합격점수</strong> : 15점</p>
        <p><strong>최대 입장자수</strong> : 8명</p>
      </div>
      <div class="col-md-4">
        <p><strong>진행단계</strong> : 1차면접</p>
        <p><strong>기타</strong> : 온라인 평가 병행</p>
      </div>
    </div>
    <!-- 화상면접 정보 (화상면접일 경우에만) -->
    <div class="border rounded-3 p-3 mb-4" style="background:#f6f2ff;">
      <h5 class="fw-bold mb-3" style="color:#8d4dff;">화상면접 방 정보</h5>
      <p class="mb-1"><strong>방 제목</strong> : 백엔드 1차 화상면접</p>
      <p class="mb-1"><strong>화상면접 URL</strong> : <a href="https://video-url.com/room/1234" target="_blank" style="color:#8d4dff; text-decoration:underline;">입장하기</a></p>
      <p class="mb-1"><strong>방 시작시간</strong> : 2025-07-14 14:00</p>
      <p class="mb-1"><strong>방 종료시간</strong> : 2025-07-14 16:00</p>
    </div>
    <!-- 면접 질문(항목) 리스트 -->
    <div class="mb-4">
      <h5 class="fw-bold mb-3" style="color:#8d4dff;">면접 질문 리스트</h5>
      <ol class="list-group list-group-numbered">
        <li class="list-group-item">자기소개를 해주세요.</li>
        <li class="list-group-item">프로젝트 경험을 설명해주세요.</li>
        <li class="list-group-item">해당 직무에 지원한 이유는?</li>
      </ol>
    </div>
    <div class="d-flex justify-content-end gap-2">
      <a href="/member/recruitment/interview/list" class="btn btn-secondary">목록으로</a>
      <a href="/member/recruitment/interview/edit/INTV000001" class="btn" style="background:#8d4dff; color:white;">수정하기</a>
    </div>
  </div>
</div>

</body>