<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>직책별 연봉 등록/수정</title>
  <script src = "/js/company/common/salaryManagement/salaryManagementForm.js"></script>
   <style>
   
  </style>
</head>
<body class="bg-light">
  <div class="container mt-5">
    
   <p id = "formTitle" class = "h2">연봉등록</p>

    <form id="salaryForm" class="card p-4 shadow-sm bg-white">
      <table class="table table-hover table-bordered align-middle text-center">
        <thead class="table-light">
          <tr>
            <th>직책</th>
            <th>최소 연봉 (원)</th>
            <th>최고 연봉 (원)</th>
          </tr>
        </thead>
        <tbody id="salaryTableBody"></tbody>
      </table>
      <div class="d-flex justify-content-end gap-2 mt-4">
        <button type="submit" class="btn btn_violet px-4"> 저장</button>
        <button type="reset" class="btn btn_violet_line px-4"> 초기화</button>
      </div>
    </form>
  </div>

 
</body>
</html>
