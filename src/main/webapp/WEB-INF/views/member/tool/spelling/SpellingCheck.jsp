<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>맞춤법 검사기</title>
<style>
/* 레이아웃 */
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

.tool-remote ul li.active, .tool-remote ul li:hover {
	color: var(--violet100);
	font-weight: bold;
}

/* 콘텐츠 */
.tool-content {
	flex: 1;
	display: flex;
	flex-direction: column;
	padding: 20px;
	box-sizing: border-box;
}

.form-display-row {
	display: flex;
	gap: 15px;
	height: 100%;
	width: 100%;
	align-items: flex-start;
}

/* 입력창 */
#container {
	width: 455.16px;
	height: 298px;
	display: flex;
	flex-direction: column;
	gap: 12px;
	font-size: 14px;
	border: 1px solid #ddd;
	padding: 16px;
	border-radius: 10px;
	background: #fff;
	box-sizing: border-box;
}

#container label {
	font-weight: bold;
}

#textInput {
	width: 100%;
	height: 100%;
	resize: none;
	padding: 10px;
	font-size: 14px;
	border-radius: 8px;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

/* 결과창 */
.result-box {
	width: 440px;
	height: 298px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	border: 1px solid #ccc;
	border-radius: 10px;
	padding: 16px;
	background-color: #f9f9f9;
	font-size: 14px;
	box-sizing: border-box;
}

.result-box p {
	margin: 0;
	padding-bottom: 10px;
}

#spellResult {
	flex: 1;
	font-size: 16px;
	display: flex;
	justify-content: center;
	align-items: center;
	text-align: left;
	min-height: 100px;
	white-space: pre-wrap;
	word-break: break-word;
}

/* 버튼 */
.actions {
	margin: 8px auto;
	text-align: right;
}

.actions button {
	padding: 6px 12px;
	margin-right: 8px;
	font-size: 13px;
	border-radius: 3px;
	cursor: pointer;
}

/* 추가된 스타일 */
.red_text {
	color: red;
	text-decoration: underline wavy red;
	font-weight: bold;
}

.green_text {
	color: green;
	font-weight: bold;
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
				<li class="active" onclick="location.href='/Spelling'">2. 맞춤법
					검사기</li>
				<li onclick="location.href='/countyear'">3. 연차계산기</li>
				<li onclick="location.href='/salarycalc'">4. 실수령액 계산기</li>
			</ul>
		</div>

		<!-- 우측 콘텐츠 -->
		<div class="tool-content">
			<div class="form-display-row">
				<form id="container" action="/spell/check" method="post">
					<label for="textInput">문장을 입력하세요</label>
					<textarea id="textInput" name="sentence"
						placeholder="여기에 문장을 입력하세요.">${original}</textarea>
					<button type="submit">검사 시작</button>
				</form>

				<div class="result-box">
					<p>
						<strong>맞춤법 검사 결과:</strong>
					</p>
					<div id="spellResult">
						<c:if test="${not empty corrected}">
    ${corrected}  <%-- escapeXml 없이 EL 출력만 사용 --%>
						</c:if>
						<c:if test="${not empty error}">
							<span style="color: red;">${error}</span>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 버튼 영역 -->
	<div class="actions">
		<button class="btn btn_red_line"
			onclick="document.getElementById('textInput').value=''; document.getElementById('spellResult').innerHTML=''">초기화</button>
		<button class="btn btn_violet_line" onclick="copySpell()">전체
			복사</button>
	</div>

	<script>
		function copySpell() {
			const textarea = document.getElementById('textInput');
			if (!textarea.value.trim()) {
				alert("복사할 내용이 없습니다.");
				return;
			}
			textarea.select();
			document.execCommand("copy");
			alert("복사 완료");
		}
	</script>

</body>
</html>
