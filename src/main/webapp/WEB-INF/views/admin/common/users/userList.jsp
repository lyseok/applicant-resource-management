<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<title>띹잡 회원관리 | 회원 목록</title>

<body>

	<!-- 안 들어가지는 거 같아서 일단 시큐리티 넣음 -->
	<sec:authentication property="principal.realUser.userId" var="userId"/>
	<p class="h4">회원 목록</p>
	<div id="auserList"></div>
	<!-- 여기서 상태 변경도 클릭으로 조정, 상세보기 들어가도 조정 가능 -->
	<!-- 회원 검색 가능 -->
	
<script>
fetch(`/ajax/admin/common/users`)
.then(resp => {resp.json()
	.then(rslt => {
		console.log("회원 나오니? :", rslt);
		const auserList = document.querySelector("#auserList");

		console.log("rslt 구조", rslt);
		console.log("첫 번째 user", rslt[0]);


		rslt.forEach(user =>{

			console.log("유저 거기 있지? : ", user);

			// 사용자 정보 하나당 div 컨테이너 생성
			let userContainer = document.createElement("div");

			const labels = [
				"userId",
				"userPassword",
				"userRole",
				"userWithdrawDate",
				"userStatus",
				"userEnabled"
			];

			user.forEach((value, index) => {
				let p = document.createElement("p");
				p.textContent = `${labels[index]}: ${value ?? '값 없음'}`;
				userContainer.appendChild(p);
			});

			// 완성된 userContainer를 auserList에 추가
			auserList.appendChild(userContainer);
		});
	});
});
</script>	
</body>
