<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="UTF-8">
    <title>채용공고 리스트</title>
   <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script defer src="/js/company/recruitment/recruitList.js"></script>
<style>
@media (max-width: 768px) {
  #search-title,
  #sort-date,
  #filter-period {
    width: 100% !important;
  }
}
</style>
</head>
<body>
<div class="">
  <!-- 왼쪽: 제목 -->
  <h2 class="h2 mb-1 fw-bold">채용공고 목록</h2>
  <p class="mb-5 fs-14 text-muted fw-500">마감된 공고 건에 한해 지원자 보기가 가능합니다. 지원자 보기 버튼이 활성화 되지 않을 경우 공고 마감 후 재시도해주세요.

</p>
</div>	

	<div class="d-flex justify-content-between align-items-end w100p pb-2 border-bottom">
		<p class="fs-14">
			총 <span id="list-count" class="fw-bold">10</span>건
		</p>
		<div class="d-flex justify-content-end align-items-end  gap-2 ">
			<div class="d-flex gap-2">
		    <select id="sort-date" class="form-select h48 fs-14">
		      <option value="">전체</option>
		      <option value="latest">최신순</option>
		      <option value="oldest">오래된순</option>
		    </select>	
				
		    <select id="filter-period" class="form-select h48 fs-14">
		      <option value="all">전체</option>
		      <option value="1">1개월 이내</option>
		      <option value="3">3개월 이내</option>
		      <option value="6">6개월 이내</option>
		    </select>
			</div>
			
			<div class="d-flex gap-2">				
				<!-- 검색창 -->
				<div class="TypoBox searchBar">
					<div class="searchBarWrap">
						<label class="searchBarLabel" for="listKeyword">검색어</label>
						<input type="text" id="search-title" class="searchBarInput" placeholder="공고명으로 검색" maxlength="24" autocomplete="off" value="">
					</div>
					
					<a href="javascript:void(0)" class="searchBarBtn">
						<span class="material-symbols-outlined">search</span>
					</a>
				</div>
				
				<!-- 글쓰기 버튼 -->
		    <a href="/company/recruit_notice/notice_form" class="btn btn_violet">등록</a>
			</div>
		</div>
	</div>


	<div id="recruitment-list"></div>
	
	<div class="PageBox" id="pagination"></div>
</body>