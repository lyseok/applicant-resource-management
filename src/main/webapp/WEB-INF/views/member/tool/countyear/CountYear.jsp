<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>연차휴가일수 계산기</title>
</head>
<body>
    <div>
        <h2>연차휴가일수 계산기</h2>
        <form id="container">
        <label for="startDate">입사일</label>
        <input type="date" id="startDate" required>
        <label for="workMonths">근무 개월 수 : </label>
        <input type="number" id="workMonths" required>
        <label for="attendanceRate">출근율 (%) : </label>
        <input type="number" id="attendanceRate" required>
        <button type="button" onclick="calculateAnnuaILeave()">계산하기</button>
        </form>
        <div id="result"></div>
    </div>
    <script src="/js/member/tool/countYear.js"></script>
</body>
</html>

