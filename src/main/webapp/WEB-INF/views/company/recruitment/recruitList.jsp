<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/company/recruitment/list.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>채용공고 리스트</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
   <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script defer src="/js/company/recruitment/recruitList.js"></script>
</head>
<body>
<div class="container py-5">
     <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="fw-bold mb-0">채용공고 목록</h2>
        <a href="/company/recruit_notice/notice_form" class="btn btn_violet">+ 공고 등록</a>
    </div>
    <div id="recruitment-count" class="mb-3 text-muted"></div>

    <div id="recruitment-list" class="row g-4">
        <!-- 채용공고 카드가 여기에 비동기로 들어감 -->
    </div>
</div>
</body>
</html>