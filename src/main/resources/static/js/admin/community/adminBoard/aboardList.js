/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {

const type = document.querySelector("#typeHidden").value;

console.log("넘어온 type 확인:", type);

	if (type === 'BRDD-001' || type === 'BRDD-003') {

		fetch(`/ajax/admin/board/admin_board/${type}`)
			.then(resp => {
				resp.json()
				.then(rslt => {
					const aboardList = document.querySelector("#aboardList");
					let html = "";
					rslt.forEach(item => {
						console.log("아이템 나오니? :", item);

						html += `
					
						<li>작성자 : ${item.userId}</li>
						<li>게시판 유형 코드: ${item.boardTypeCode}</li>
						<li>제목: 
							<a href="/admin/board/admin_board/detail?no=${item.boardNo}">
							${item.boardTitle}
							</a>
						</li>
						<li>등록일시: ${item.boardWriteDate}</li>
						<li>내용: ${item.boardContent}</li>
						<li>삭제일시: ${item.boardDeleteDate}</li>
						<li>조회수: ${item.boardPostHit}</li>
						<li>게시글 상태: ${item.boardStatus}</li><hr/>`;
					});
					aboardList.innerHTML = html;
				});
			});
	/*		
	} else {
		
		//faq일 때는...
		const codeDetailNo = "${type}";  //에서 codeGroupNo(기업/회원)나 upperCodeNo(BRDD-002)를 불러와야
		//링크만 BRDD-002로 하고 데이터는 UFAQ-U6이나 UFAQ로 할 건지,
		//링크는 UFAQ-U6으로 하고 데이터는 UFAQ로 할 건지
		//adminSideMenu도 통일해버리기
		console.log("codeDetailNo를 보여줘! : ", codeDetailNo);
		console.log("upperCodeNo를 보여줘! : ", codeDetailNo.cmnCodeList.upperCodeNo);  //cmnCodeVO와 innerJoin 해야 하나?
		fetch(`/ajax/admin/board/admin_board/${codeDetailNo.upperCodeNo}`)
	*/	
	}
});
