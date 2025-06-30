<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<body>
	<!--  스프링의 form 태그 활용. -->
	<!--  비밀번호는 암호화해 저장함. -->
	<!--  가입완료후 웰컴페이지로 이동. -->
<div class="card my-5">
        <div class="card-body">
		<form:form modelAttribute="member">
			<div class="form-group">
				<label class="form-label" for="memId">회원번호</label>
				<form:input type="text" path="memId" cssClass="form-control"
					placeholder="회원번호" />
				<form:errors cssClass="text-danger" path="memId" />
			</div>
			<div class="form-group">
				<label class="form-label" for="memPassword">비밀번호</label>
				<form:input type="text" path="memPassword" cssClass="form-control"
					placeholder="비밀번호" />
				<form:errors cssClass="text-danger" path="memPassword" />
			</div>
			<div class="form-group">
				<label class="form-label" for="memName">회원이름</label>
				<form:input type="text" path="memName" cssClass="form-control"
					placeholder="회원이름" />
				<form:errors cssClass="text-danger" path="memName" />
			</div>
			<div class="form-group">
				<label class="form-label" for="memRegno1">주민등록번호 앞자리</label>
				<form:input type="text" path="memRegno1" cssClass="form-control"
					placeholder="주민등록번호 앞자리" />
				<form:errors cssClass="text-danger" path="memRegno1" />
			</div>
			<div class="form-group">
				<label class="form-label" for="memRegno2">주민등록번호 뒤자리</label>
				<form:input type="text" path="memRegno2" cssClass="form-control"
					placeholder="주민등록번호 뒤자리" />
				<form:errors cssClass="text-danger" path="memRegno2" />
			</div>
			<div class="form-group">
				<label class="form-label" for="memBir">생년월일</label>
				<form:input type="datetime-local" path="memBir" cssClass="form-control"
					placeholder="생년월일" />
				<form:errors cssClass="text-danger" path="memBir" />
			</div>
			<div class="form-group">
				<label class="form-label" for="memZip">우편번호</label>
				<form:input type="text" path="memZip" cssClass="form-control"
					placeholder="우편번호" />
				<form:errors cssClass="text-danger" path="memZip" />
			</div>
			<div class="form-group">
				<label class="form-label" for="memAdd1">기본주소</label>
				<form:input type="text" path="memAdd1" cssClass="form-control"
					placeholder="기본주소" />
				<form:errors cssClass="text-danger" path="memAdd1" />
			</div>
			<div class="form-group">
				<label class="form-label" for="memAdd2">상세주소</label>
				<form:input type="text" path="memAdd2" cssClass="form-control"
					placeholder="상세주소" />
				<form:errors cssClass="text-danger" path="memAdd2" />
			</div>
			<div class="form-group">
				<label class="form-label" for="memHometel">집전화번호</label>
				<form:input type="text" path="memHometel" cssClass="form-control"
					placeholder="집전화번호" />
				<form:errors cssClass="text-danger" path="memHometel" />
			</div>
			<div class="form-group">
				<label class="form-label" for="memComtel">회사전화번호</label>
				<form:input type="text" path="memComtel" cssClass="form-control"
					placeholder="회사전화번호" />
				<form:errors cssClass="text-danger" path="memComtel" />
			</div>
			<div class="form-group">
				<label class="form-label" for="memHp">핸드폰번호</label>
				<form:input type="text" path="memHp" cssClass="form-control"
					placeholder="핸드폰번호" />
				<form:errors cssClass="text-danger" path="memHp" />
			</div>
			<div class="form-group">
				<label class="form-label" for="memMail">메일주소</label>
				<form:input type="email" path="memMail" cssClass="form-control"
					placeholder="메일주소" />
				<form:errors cssClass="text-danger" path="memMail" />
			</div>
			<div class="form-group">
				<label class="form-label" for="memJob">직업</label>
				<form:input type="text" path="memJob" cssClass="form-control"
					placeholder="직업" />
				<form:errors cssClass="text-danger" path="memJob" />
			</div>
			<div class="form-group">
				<label class="form-label" for="memHobby">취미</label>
				<form:input type="text" path="memHobby" cssClass="form-control"
					placeholder="취미" />
				<form:errors cssClass="text-danger" path="memHobby" />
			</div>
			<div class="form-group">
				<label class="form-label" for="memMemorial">기념일종류</label>
				<form:input type="text" path="memMemorial" cssClass="form-control"
					placeholder="기념일종류" />
				<form:errors cssClass="text-danger" path="memMemorial" />
			</div>
			<div class="form-group">
				<label class="form-label" for="memMemorialday">해당기념일자</label>
				<form:input type="date" path="memMemorialday" cssClass="form-control"
					placeholder="해당기념일자" />
				<form:errors cssClass="text-danger" path="memMemorialday" />
			</div>
			<div>
				<button type="submit">저장</button>
			</div>
		</form:form>
	</div>
</div>
	
</body>

</html>