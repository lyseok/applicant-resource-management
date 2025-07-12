<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <style>
    
    .banner { background: #ebe8ff; }
    .btn-purple { background: #6f49fd; color: #fff; }
    .exam-item { cursor: pointer; transition: background .2s; }
    .exam-item:hover { background: #f8f9fa; }
  </style>
  
  <script src = "/js/member/recruitment/recruitmentExam/recruitmentExamList.js"></script>
</head>
<body class="bg-light">

  <div class="container py-5">
    <!-- 헤더 / 배너 -->
    <div class="p-4 mb-4 banner rounded">
      <div class="d-flex justify-content-between align-items-center">
        <div class="fs-5">📋 응시 시험 관리</div>
        <button class="btn btn-purple">지원한 공고 보러가기 </button>
      </div>
    </div>

    <!-- 검색바 -->
    <div class="mb-3 d-flex justify-content-end">
      <input id="search-input" type="search" class="form-control w-50" placeholder="시험명, 내용으로 검색해보세요.">
    </div>

    <!-- 총 개수 -->
    <div id="exam-count" class="mb-2 text-muted">총 0건</div>

    <!-- 시험 리스트 -->
    <div id="exam-list" class="list-group">
      <div class="list-group-item text-center text-muted">로딩 중...</div>
    </div>
  </div>


</body>
</html>