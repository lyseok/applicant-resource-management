
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>취업 TOOL</title>
<script src="/js/member/tool/countText.js"></script>
<style>

/* wrapper 높이를 textarea 높이와 동일하게 300px 고정 */
.tool-wrapper {
	display: flex;
	/* max-width: 1000px; */
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
	padding: 0; /* padding 제거해서 높이 300px 그대로 사용 */
	box-sizing: border-box;
}

.tool-content textarea {
	width: 100%;
	flex: 1; /* wrapper 높이(300px) 그대로 차지 */
	height: auto; /* flex 높이에 맞게 조정 */
	resize: none;
	border: 1px solid #ccc;
	padding: 10px;
	font-size: 14px;
	line-height: 1.5;
	box-sizing: border-box;
	padding: 10px;
	border-radius: 10px;
}

/* 하단 통계 & 버튼 (wrapper 밖에 배치) */
.stats {
	/* max-width: 1000px; */
	margin: 10px auto 0;
	display: flex;
	font-size: 13px;
	color: #666;
	justify-content: flex-end;
}

.stats div {
	margin-right: 20px;
}

.stats .num {
	color: var(--violet80);
	font-weight: bold;
}

.actions {
	/* max-width: 1000px; */
	margin: 8px auto;
	text-align: right;
}

.actions button {
	padding: 6px 12px;
	margin-right: 8px;
	font-size: 13px;
	border-radius: 3px;
	/* border: none; */
	cursor: pointer;
}
</style>
</head>
<body>

	<div class="tool-wrapper">
		<!-- 좌측 메뉴 -->
		<div class="tool-remote">
			<h3>취업 Tool 리모콘</h3>
			<ul>
				<li class="active" onclick="location.href='/count_text'">1. 글자수 세기</li>
				<li onclick="location.href='/spelling'">2. 맞춤법 검사기</li>
				<li onclick="location.href='/countyear'">3. 연차계산기</li>
				<li onclick="location.href='/salarycalc'">4. 실수령액 계산기</li>
			</ul>
		</div>

		<!-- 우측 콘텐츠 -->
		<div class="tool-content" id="content-1">
			<textarea id="input" placeholder="내용을 입력해주세요."></textarea>
		</div>
		<div class="tool-content" id="content-2" style="display: none;">
			<p>여기에 맞춤법 검사기 UI</p>
		</div>
		<div class="tool-content" id="content-3" style="display: none;">
			<p>여기에 연차계산기 UI</p>
		</div>
		<div class="tool-content" id="content-4" style="display: none;">
			<p>여기에 실수령액 계산기 UI</p>
		</div>
	</div>

	<!-- 하단: 통계 & 버튼 -->
	<div class="stats">
		<div>
			공백 포함 <span class="num" id="count-with-space">0</span>자 | <span
				class="num" id="byte-with-space">0</span> byte
		</div>
		<div>
			공백 제외 <span class="num" id="count-without-space">0</span>자 | <span
				class="num" id="byte-without-space">0</span> byte
		</div>
	</div>
	<div class="actions">
		<button class="btn btn_red_line" onclick="resetAll()">초기화</button>
		<button class="btn btn_violet_line" onclick="copyAll()">전체 복사</button>
	</div>

	<script>
    // 메뉴 클릭 시 콘텐츠 토글
    function selectTool(n) {
      document.querySelectorAll('.tool-content')
        .forEach((d,i)=> d.style.display = (i+1===n?'flex':'none'));
      document.querySelectorAll('.tool-remote li')
        .forEach((li,i)=> li.classList.toggle('active', i===n-1));
    }
    // 글자수 세기
    (function(){
      const ta = document.getElementById('input'),
            ws = document.getElementById('count-with-space'),
            ns = document.getElementById('count-without-space'),
            bw = document.getElementById('byte-with-space'),
            bn = document.getElementById('byte-without-space');
      function update(){
        const t = ta.value;
        ws.textContent = t.length;
        ns.textContent = t.replace(/\s/g,'').length;
        const e = new TextEncoder();
        bw.textContent = e.encode(t).length;
        bn.textContent = e.encode(t.replace(/\s/g,'')).length;
      }
      ta.addEventListener('input', update);
      update();
      window.copyAll = ()=>{ ta.select(); document.execCommand('copy'); alert('복사 완료'); };
      window.resetAll = ()=>{ ta.value=''; update(); };
    })();
  </script>

</body>
</html>
