<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<title>띹잡 회원관리 | 회원정보 상세</title>

<body>

	<p class="h4">회원정보 상세보기</p>
    <div id="auserDetail"></div>

<script>
const userId = "${users.userId}";
console.log("유저 아이디 나오나? : ", userId);

const auserDetail = document.querySelector("#auserDetail");

fetch(`/ajax/admin/common/users/\${userId}`)
.then(resp => {resp.json()
	.then(rslt => {

    });
});
</script>
</body>