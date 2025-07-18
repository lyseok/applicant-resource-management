<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<title>띹잡 회원관리 | 회원 목록</title>

<body>

	<p class="h4">회원 목록</p><br>
	<!-- 검색 바-->
	<div id="uSearchbar">
		<label>회원 구분</label>
		<select id="userRole">
			<option value="-1" selected>전체</option>
			<option value="ROLE_USER">일반회원</option>
			<option value="ROLE_COMPANY">기업회원</option>
		</select>
		<label>아이디 검색</label>
		<input type="text" placeholder="회원 아이디를 입력" id="userId">
		<button id="userbar">검색</button>
	</div>
	<!-- 유저 리스트(상태 변경도 클릭으로 조정, 상세보기 들어가도 조정 가능)-->
	<table id="userTable" class="table">
	  <thead>
	    <tr>
	      <th>선택</th>
	      <th>아이디</th>
	      <th>비밀번호</th>
	      <th>회원구분</th>
	      <th>탈퇴일자</th>
	      <th>상태</th>
	      <th>활성여부</th>
	    </tr>
	  </thead>
	  <tbody id="userTableBody">
	  	<tr>
	  		<input class="form-check-input" type="checkbox" value="" id="">
			  <label class="form-check-label" id="userId"></label>
			<td></td>
	  	</tr>
	  </tbody>
	</table>
		
<script src="/js/admin/common/users/userList.js"></script>	
</body>
