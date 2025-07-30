<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>면접 후기</title>

<style>
.badge-fail {
	background-color: #ff6b6b;
}

.badge-pass {
	background-color: #4caf50;
}

.badge-neutral {
	background-color: #ffc107;
}

.section-title {
	font-weight: bold;
	margin-top: 1.5rem;
}

.accordion-button:not(.collapsed) {
	background-color: var(--violet30) !important;
	color: white; /* 선택 사항: 텍스트 색상도 함께 조정 */
}

/* ─── 헤더 바: 타이틀 & 검색바 ────────────────────────────── */
.header-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
}

/* 타이틀 기본 스타일 유지 */
.header-bar .title {
  margin: 0;
  font-size: 1.5rem;
}

/* ─── 검색바 ────────────────────────────────────────────── */
.search-bar {
  display: inline-flex;
  align-items: center;
}

/* 입력창 */
.search-bar .search-input {
  height: 36px;
  padding: 0 12px;
  border: 1px solid #d1d5db;
  border-right: none;
  border-radius: 4px 0 0 4px;
  font-size: 14px;
  outline: none;
  width: 200px; /* 원하는 너비로 조절 */
  transition: border-color .2s, box-shadow .2s;
}

.search-bar .search-input:focus {
  border-color: #6366f1;
  box-shadow: 0 0 0 2px rgba(99,102,241,.3);
}

/* 버튼 */
.search-bar .search-btn {
  height: 36px;
  padding: 0 16px;
  border: 1px solid #6366f1;
  background-color: #6366f1;
  color: #fff;
  font-size: 14px;
  border-left: none;
  border-radius: 0 4px 4px 0;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  white-space: nowrap;   /* 텍스트 줄바뀜 방지 */
  transition: background-color .2s, border-color .2s;
}

.search-bar .search-btn:hover {
  background-color: #4f46e5;
  border-color: #4f46e5;
}

/* 호버 효과 */
.search-bar .search-btn:hover {
  background-color: #4f46e5;
  border-color: #4f46e5;
}
</style>
<script
	src="/js/member/community/interviewReview/interviewReviewList.js"></script>
</head>
<body class="bg-light">
  <div class="container my-5">

   
    <div class="header-bar">
      <h2 class="title">면접 후기</h2>
      <div class="search-bar">
        <input
          type="text"
          id="searchInput"
          class="search-input"
          placeholder="키워드로 후기 검색하기"
        />
        <button class="search-btn" type="button" id="searchBtn">
          검색
        </button>
      </div>
    </div>

    <div class="accordion" id="interviewReviewList"></div>
  </div>
</body>
</html>