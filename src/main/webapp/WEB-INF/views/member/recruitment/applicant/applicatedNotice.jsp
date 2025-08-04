<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/company/recruitment/list.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>지원 현황</title>
   <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script defer src="/js/member/recruitment/applicant/applicatedNotice.js"></script>
<style>
/* 반응형 대응 */
@media (max-width: 768px) {
  #search-title,
  #sort-date,
  #filter-period {
    width: 100% !important;
	}
}
.label{
display:inline-block;
	padding:2px 5px;
	background:var(--violet30);
	font-size:12px;
	border-radius:3px;
	line-height:1;
}

#btn_wrap{
	width:150px;
}

</style>
</head>
<body>
  <!-- 왼쪽: 제목 -->
  <h2 class="h1 mb-3 fw-bold">지원 현황</h2>

  <!-- 오른쪽: 필터 컨트롤 -->
  <div class="border-bottom d-flex justify-content-between align-items-end pb-2">
		<p class="fs-14">총 <span id="exam-count" class="fw-bold">0</span>건</p>

		<div class="d-flex gap-2">
	    <select id="sort-date" class="form-select w140 fs-14">
	      <option value="latest">최신순</option>
	      <option value="oldest">오래된순</option>
	    </select>
	
	    <select id="filter-period" class="form-select w140 fs-14">
	      <option value="all">전체</option>
	      <option value="1">1개월 이내</option>
	      <option value="3">3개월 이내</option>
	      <option value="6">6개월 이내</option>
	    </select>
	    
			<div class="TypoBox searchBar">
				<div class="searchBarWrap">
					<label class="searchBarLabel" for="search-title">검색어</label>
					<input type="text" id="search-title" class="searchBarInput" placeholder="공고명으로 검색" />
				</div>
				<button type="button" class="searchBarBtn" id="searchBtn">
					<span class="material-symbols-outlined">search</span>
				</button>
			</div>
		</div>
</div>

  <div id="recruitment-list">
  	<div class="p-6 d-flex flex-column align-items-center gap-2">
      <span>입사지원한 채용 공고가 없어요 !</span>
      <a class="fw-bold d-flex" href="/write/new">
        채용공고 보러가기
        <span class="material-symbols-outlined">chevron_right</span>
      </a>
    </div>
   </div>
  <div id="pagination" class="PageBox"></div>
</body>
</html>