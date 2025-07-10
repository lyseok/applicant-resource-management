<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>맞춤법 검사기</title>
<style>
/* 공통 툴 레이아웃 */
.tool-wrapper {
	display: flex;
	margin: 0 auto;
	background: #fff;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
	height: 300px;
}

.tool-remote {
	display: flex;
	flex-direction: column;
	width: 180px;
	border: 1px solid #333;
	box-sizing: border-box;
	margin-right: 25px;
	padding: 0;
	border-radius: 10px;
	overflow: hidden;
}

.tool-remote h3 {
	margin: 0;
	padding: 0;
	background: #eaeaea;
	font-size: 16px;
	text-align: center;
	flex: 1;
	display: flex;
	align-items: center;
	justify-content: center;
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
	background: var(--violet120, #6a1b9a);
	color: #fff;
}

/* 우측 콘텐츠 */
.tool-content {
	flex: 1;
	display: flex;
	flex-direction: column;
	padding: 20px;
	box-sizing: border-box;
}

.tool-content textarea {
	width: 100%;
	height: 100%;
	resize: none;
	border: 1px solid #ccc;
	padding: 10px;
	font-size: 14px;
	line-height: 1.5;
	border-radius: 10px;
	box-sizing: border-box;
}
</style>
</head>
<body>

	<div class="tool-wrapper">
		<!-- 좌측 메뉴 -->
		<div class="tool-remote">
			<h3>취업 Tool 리모콘</h3>
			<ul>
				<li onclick="location.href='/countText'">1. 글자수 세기</li>
				<li class="active" onclick="location.href='/Spelling'">2. 맞춤법 검사기</li>
				<li onclick="location.href='/countyear'">3. 연차계산기</li>
				<li onclick="location.href='/salarycalc'">4. 실수령액 계산기</li>
			</ul>
		</div>

		<!-- 맞춤법 검사 콘텐츠 -->
		<div class="tool-content">
			<h2>문장을 입력하세요</h2>
			<textarea name="sentence" id="spellInput" placeholder="여기에 문장을 입력하세요."></textarea>
		</div>
	</div>

</body>
</html>
