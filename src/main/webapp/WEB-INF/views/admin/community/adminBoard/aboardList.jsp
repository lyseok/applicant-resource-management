<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
<title>띹잡 관리자 페이지 | 관리자게시판</title>
</head>
<body>

	<ul id="aboardList">
		<p class="h4">게시글 목록</p>
	</ul>
	<input type="hidden" value="boardTypeCode" name="boardTypeCode">
	
	<%--구상을 위한 예시 --%>
	<div id="faqno">
		<h3>자주 묻는 질문 탭 선택</h3>
		<button type="button" id="ufaq">일반회원</button>
		<button type="button" id="cfaq">기업회원</button>
	</div>
	

<script>
document.addEventListener("DOMContentLoaded", () => {

if("${type}" === 'BRDD-001' || "${type}" === 'BRDD-003'){
	const boardTypeCode = "${type}";

fetch(`/ajax/admin/board/admin_board/\${boardTypeCode}`)
	.then(resp => {resp.json()
		.then(rslt=> {
			const aboardList = document.querySelector("#aboardList");
			let html = "";
			rslt.forEach(item => {
				console.log("아이템 나오니? :", item);

				let li = document.createElement("li");  //이거로 리팩토링 안되나?
				//appenChild

				html += `
					
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
}else{
	//faq일 때는...
	const codeDetailNo = "${type}";  //에서 codeGroupNo(기업/회원)나 upperCodeNo(BRDD-002)를 불러와야
	//링크만 BRDD-002로 하고 데이터는 UFAQ-U6이나 UFAQ로 할 건지,
	//링크는 UFAQ-U6으로 하고 데이터는 UFAQ로 할 건지
	//adminSideMenu도 통일해버리기
	console.log("codeDetailNo를 보여줘! : ", codeDetailNo);
	console.log("upperCodeNo를 보여줘! : ", codeDetailNo.cmnCodeList.upperCodeNo);  //cmnCodeVO와 innerJoin 해야 하나?
	fetch(`/ajax/admin/board/admin_board/\${codeDetailNo.upperCodeNo}`)
	
}
});

</script>
</body>
