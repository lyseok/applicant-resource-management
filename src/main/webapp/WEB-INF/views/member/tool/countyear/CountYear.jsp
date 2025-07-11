<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연차휴가일수 계산기</title>
<style>
/* 기본 레이아웃 */
.tool-wrapper {
	display: flex;
	margin: 0 auto;
	background: #fff;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
	height: 300px;
}

/* 좌측 리모콘 */
.tool-remote {
	display: flex;
	flex-direction: column;
	width: 180px;
	border-top: 2px solid #333;
	border-bottom: 1px solid #333;
	box-sizing: border-box;
	margin-right: 25px;
	padding: 0;
}

.tool-remote h3 {
	margin: 0;
	padding: 0;
	font-size: 16px;
	text-align: center;
	flex: 1;
	display: flex;
	align-items: center;
	justify-content: center;
    border-bottom: 1px solid #333;
}

.tool-remote ul {
	list-style: none;
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: column;
	flex: 4;
}

.tool-remote ul li {
	flex: 1;
	padding: 0 15px;
	border-bottom: 1px solid #333;
	display: flex;
	align-items: center;
	cursor: pointer;
	font-size: 14px;
}

.tool-remote ul li:last-child {
	border-bottom: none;
}

.tool-remote ul li.active,
.tool-remote ul li:hover {
	color: var(--violet100);
	font-weight:bold;
}

/* 우측 콘텐츠 */
.tool-content {
	flex: 1;
	display: flex;
	flex-direction: column;
	padding: 20px;
	box-sizing: border-box;
}

/* 폼 + 결과 가로 정렬 */
.form-display-row {
	display: flex;
	gap: 15px;
	height: 100%;
}

/* 폼 스타일 */
#container {
	flex: 1;
	display: flex;
	flex-direction: column;
	gap: 12px;
	font-size: 14px;
	border: 1px solid #ddd;
	padding: 16px;
	border-radius: 10px;
	box-sizing: border-box;
	background: #fff;
}

#container label {
	font-weight: bold;
}

#container input {
	width: 100%;
	padding: 6px 8px;
	font-size: 14px;
	border: 1px solid #ccc;
	border-radius: 6px;
}

#container button {
	margin-top: 10px;
	align-self: flex-start;
	padding: 6px 12px;
	font-size: 13px;
	border-radius: 6px;
	background-color: var(--violet120, #6a1b9a);
	color: #fff;
	border: none;
	cursor: pointer;
}

#container button:hover {
	background-color: var(--violet100, #8e24aa);
}

.form-display-row {
  display: flex;
  gap: 15px;
  width: 100%;
  align-items: flex-start;
}

#container {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 12px;
  font-size: 14px;
  border: 1px solid #ddd;
  padding: 16px;
  border-radius: 10px;
  background: #fff;
}

.result-box {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center; /* 중앙 정렬 */
  border: 1px solid #ccc;
  border-radius: 10px;
  padding: 16px;
  background-color: #f9f9f9;
  font-size: 14px;
  height: 100%;
  box-sizing: border-box;
}

.result-box p {
  margin: 0;
  padding-bottom: 10px;
}

#result {
  flex: 1;
  font-size: 32px;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  height: 100%;
}

</style>
</head>
<body>

	<div class="tool-wrapper">
		<!-- 좌측 리모콘 -->
		<div class="tool-remote">
			<h3>취업 Tool 리모콘</h3>
			<ul>
				<li onclick="location.href='/countText'">1. 글자수 세기</li>
				<li onclick="location.href='/Spelling'">2. 맞춤법 검사기</li>
				<li class="active" onclick="location.href='/countyear'">3. 연차계산기</li>
				<li onclick="location.href='/salarycalc'">4. 실수령액 계산기</li>
			</ul>
		</div>

		<!-- 우측 콘텐츠 -->
		<div class="tool-content" id="content-3">
			<div class="form-display-row">
				<form id="container">
					<label for="startDate">입사일</label>
					<input type="date" id="startDate" required>

					<label for="workMonths">근무 개월 수 :</label>
					<input type="number" id="workMonths" required>

					<label for="attendanceRate">출근율 (%) :</label>
					<input type="number" id="attendanceRate" required>

					<button type="button" onclick="calculateAnnuaILeave()">계산하기</button>
				</form>

				
					<p><strong>총 연차 휴가일수:</strong></p>
					<div id="result">결과가 여기에 표시됩니다.</div> 
				</div>
			</div>
		</div>
	</div>

	<script src="/js/member/tool/countYear.js"></script>

</body>
</html>
