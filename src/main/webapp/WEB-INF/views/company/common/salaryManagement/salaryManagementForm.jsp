<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>직책별 연봉 등록/수정</title>
  <script src = "/js/company/common/salaryManagement/salaryManagementForm.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">
  <div class="container mt-5">
    <h2 id="formTitle" class="text-center mb-4">직책별 연봉 등록</h2>

    <form id="salaryForm" class="card p-4 shadow-sm bg-white">
      <table class="table table-bordered align-middle text-center">
        <thead class="table-light">
          <tr>
            <th>직책</th>
            <th>최소 연봉 (원)</th>
            <th>최대 연봉 (원)</th>
          </tr>
        </thead>
        <tbody id="salaryTableBody"></tbody>
      </table>

      <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <button type="submit" class="btn btn_violet">저장</button>
        <button type="button" class="btn btn_violet_line">초기화</button>
      </div>
    </form>
  </div>

 
</body>
</html>
