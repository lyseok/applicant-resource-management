<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/company/recruitment/list.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>지원 현황</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
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

</style>
</head>
<body>
<div class="d-flex align-items-center justify-content-between mb-3">
  <!-- 왼쪽: 제목 -->
  <h2 class="fw-bold mb-0">지원 현황</h2>

  <!-- 오른쪽: 필터 컨트롤 -->
  <div class="d-flex align-items-center gap-2 flex-nowrap ms-auto">
    <input type="text" id="search-title" class="form-control form-control-sm" placeholder="제목 검색" style="width: 160px;" />

    <select id="sort-date" class="form-select form-select-sm" style="width: 100px;">
      <option value="latest">최신순</option>
      <option value="oldest">오래된순</option>
    </select>

    <select id="filter-period" class="form-select form-select-sm" style="width: 100px;">
      <option value="all">전체</option>
      <option value="1">1개월 이내</option>
      <option value="3">3개월 이내</option>
      <option value="6">6개월 이내</option>
    </select>
  </div>
</div>

  <div id="recruitment-list"></div>
  <div id="pagination" class="mt-3 d-flex justify-content-center">s</div>
</div>
</body>
</html>