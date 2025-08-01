<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	background-color: var(--violet80);
}

.section-title {
	font-weight: bold;
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
.q_item{
	flex-basis:calc((100%  - 30px)/ 2);
}
</style>
<script
	src="/js/member/community/interviewReview/interviewReviewList.js"></script>
</head>
<body>

   
    <div class="header-bar">
      <h2 class="h2 mb-0 fw-bold">면접 후기</h2>
      
      <div class="TypoBox searchBar">
				<div class="searchBarWrap">
					<label class="searchBarLabel" for="listKeyword">검색어</label>
					<input type="text" id="searchInput" class="searchBarInput" placeholder="회사명으로 리뷰 검색" maxlength="24" autocomplete="off" value="">
				</div>
				
				<a href="javascript:void(0)" class="searchBarBtn" id="searchBtn">
					<span class="material-symbols-outlined">search</span>
				</a>
			</div>
    </div>

    <div class="accordion" id="interviewReviewList"></div>
</body>
</html>