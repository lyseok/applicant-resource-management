<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form method = "post" action="/member/memberDelete.do">
      <div class="modal-body">
      	<security:csrfInput/>
       	 비밀번호:	<input type = "password" name = "password">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-danger">탈퇴</button>
      </div>
      </form>
    </div>
  </div>
</div>

	<table class="table table-bordered">
	
		<tr>
			<td  data-bs-toggle="modal" data-bs-target="#exampleModal">
				<button>탈퇴</button>
			</td>
			
			<td>
				<a href="${pageContext.request.contextPath}/member/memberUpdate.do">수정</a>
			</td>
		</tr>
	
		<tr>
			<th>회원번호</th>
			<td>${member.memId}</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>${member.memPassword}</td>
		</tr>
		<tr>
			<th>회원이름</th>
			<td>${member.memName}</td>
		</tr>
		<tr>
			<th>주민등록번호 앞자리</th>
			<td>${member.memRegno1}</td>
		</tr>
		<tr>
			<th>주민등록번호 뒤자리</th>
			<td>${member.memRegno2}</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>${member.memBir}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${member.memZip}</td>
		</tr>
		<tr>
			<th>기본주소</th>
			<td>${member.memAdd1}</td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td>${member.memAdd2}</td>
		</tr>
		<tr>
			<th>집전화번호</th>
			<td>${member.memHometel}</td>
		</tr>
		<tr>
			<th>회사전화번호</th>
			<td>${member.memComtel}</td>
		</tr>
		<tr>
			<th>핸드폰번호</th>
			<td>${member.memHp}</td>
		</tr>
		<tr>
			<th>메일주소</th>
			<td>${member.memMail}</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>${member.memJob}</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>${member.memHobby}</td>
		</tr>
		<tr>
			<th>기념일종류</th>
			<td>${member.memMemorial}</td>
		</tr>
		<tr>
			<th>해당기념일자</th>
			<td>${member.memMemorialday}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${member.memMileage}</td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td>${member.memDelete}</td>
		</tr>
	</table>
</body>
</html>