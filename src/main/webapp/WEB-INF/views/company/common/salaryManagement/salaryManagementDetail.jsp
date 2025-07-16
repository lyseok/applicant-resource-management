<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="UTF-8">
  <title>연봉 관리 </title>
  <script src="/js/company/common/salaryManagement/salaryManagementDetail.js"></script>
</head>


<body class="bg-light">
  <div class="container mt-5">
    <h2 class="text-center mb-4">연봉 관리</h2>
    

    <div class="card p-4 shadow-sm bg-white" id="contentBox">
      <p class="text-center text-muted" id="noDataText">데이터를 불러오는 중...</p>

      <table class="table table-bordered text-center align-middle d-none" id="salaryTable">
        <thead class="table-light">
          <tr>
            <th>직책</th>
            <th>최소 연봉</th>
            <th>최대 연봉</th>
          </tr>
        </thead>
        <tbody id="salaryTableBody"></tbody>
      </table>
    </div>

    <div class="text-center mt-4" id="actionBtnBox"></div>
  </div>
</body>
</html>
