<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
<title>띹잡 관리자 페이지 | 관리자게시판</title>
</head>
<body>

	<ul id="aboardList"></ul>
	<input type="hidden" value="boardTypeCode" name="boardTypeCode">
	
	<%--구상을 위한 예시 --%>
	<div id="faqno">
		<h3>자주 묻는 질문 탭 선택</h3>
		<button type="button" id="ufaq">일반회원</button>
		<button type="button" id="cfaq">기업회원</button>
	</div>
	

<script>
document.addEventListener("DOMContentLoaded", () => {
const boardTypeCode = "${type}";
fetch(`/ajax/admin/board/admin_board/\${boardTypeCode}`)
	/*
	동적으로 ufaq, cfaq가 달라져야 하는데 이렇게 만드는 게 이상해서 일단 보류!
	
	BRDD-002에서 BRDD가 아니라 CFAQ, UFAQ를 뽑으려면 어떻게 해야 하지?
	const codeNo = "${no}";
	fetch(`/ajax/admin/board/admin_board/cmncodegroup/\${codeNo.codeGroupNo}`)  //CFAQ-U5에서 CFAQ를 통해 리스트 뽑게

	// 자주 묻는 질문이면
	if(boardTypeCode === 'BRDD-002'){
		//기본값 일반회원 자주묻는 질문 먼저 보이게
		const ufaq = document.querySelector("#ufaq");
		let no = 'UFAQ';
		fetch(`/ajax/admin/board/admin_board/cmncodegroup/\${no}`)
		.then(resp => {resp.json()
			.then(rslt=> {
				const faqno = document.querySelector("#faqno");
				let html = "";
				rslt.forEach(utem => {
					console.log("유저 아이템 나오니? :", utem);
					html += `
						<p class="h4">게시글 목록</p>
						<li>작성자 : \${utem.userId}</li>
						<li>게시판 유형 코드: \${utem.boardTypeCode}</li>
						<li>제목: 
							<a href="/admin/board/admin_board/detail?no=\${utem.boardNo}">
							\${utem.boardTitle}
							</a>
						</li>
						<li>등록일시: \${utem.boardWriteDate}</li>
						<li>내용: \${utem.boardContent}</li>
						<li>삭제일시: \${utem.boardDeleteDate}</li>
						<li>조회수: \${utem.boardPostHit}</li>
						<li>게시글 상태: \${utem.boardStatus}</li><hr/>`;
				});
				faqno.innerHTML = html;
		});
		ufaq.addEventListner("onclick", function(){
			
		})

	//탭 누르면 기업회원 자주묻는 질문도 보이게
	const cfaq = document.querySelector("#cfaq");
	let no = 'CFAQ';
	if(cfaq.addEventListner("onclick", function(){

		fetch(`/ajax/admin/board/admin_board/cmncodegroup/\${no}`)
		.then(resp => {resp.json()
			.then(rslt=> {
				let html = "";
				rslt.forEach(ctem => {
					console.log("기업 아이템 나오니? :", ctem);
					html += `
						<p class="h4">게시글 목록</p>
						<li>작성자 : \${ctem.userId}</li>
						<li>게시판 유형 코드: \${ctem.boardTypeCode}</li>
						<li>제목: 
							<a href="/admin/board/admin_board/detail?no=\${ctem.boardNo}">
							\${ctem.boardTitle}
							</a>
						</li>
						<li>등록일시: \${ctem.boardWriteDate}</li>
						<li>내용: \${ctem.boardContent}</li>
						<li>삭제일시: \${ctem.boardDeleteDate}</li>
						<li>조회수: \${ctem.boardPostHit}</li>
						<li>게시글 상태: \${ctem.boardStatus}</li><hr/>`;
				});
				faqno.innerHTML = html;
			});
		});	
		
		});
	});
	*/
	.then(resp => {resp.json()
		.then(rslt=> {
			const aboardList = document.querySelector("#aboardList");
			let html = "";
			rslt.forEach(item => {
				console.log("아이템 나오니? :", item);
				html += `
					<p class="h4">게시글 목록</p>
					<li>작성자 : \${item.userId}</li>
					<li>게시판 유형 코드: \${item.boardTypeCode}</li>
					<li>제목: 
						<a href="/admin/board/admin_board/detail?no=\${item.boardNo}">
						\${item.boardTitle}
						</a>
					</li>
					<li>등록일시: \${item.boardWriteDate}</li>
					<li>내용: \${item.boardContent}</li>
					<li>삭제일시: \${item.boardDeleteDate}</li>
					<li>조회수: \${item.boardPostHit}</li>
					<li>게시글 상태: \${item.boardStatus}</li><hr/>`;
			});
			aboardList.innerHTML = html;
		});
	});
});
</script>
</body>
