<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${detail.userName}님의이력서</title>
<style>
body {
	font-family: 'Noto Sans KR';
	margin: 20px;
}

.resume-box {
	border: 1px solid #ccc;
	padding: 20px;
	border-radius: 10px;
	max-width: 800px;
	margin: auto;
	background: #fff;
}

h2 {
	border-bottom: 1px solid #ddd;
	padding-bottom: 10px;
}

.info {
	margin-top: 10px;
	line-height: 1.7;
}
</style>
<script src="/js/company/recruitment/talentpool/joboffer.js"></script>
</head>
<body>
	<c:if test="${not empty detail}">
		<div class="resume-box">
			<h2>${detail.userName}님의이력서</h2>

			<button onclick="">저장</button>

			<div class="user-card" data-user-email="${detail.email }">채*전</div>


			<div class="info">
				<p>
					<strong>이름 : ${detail.userName }</strong>
				</p>
				<p>
					<strong>생년월일 : ${detail.birth }</strong>
				</p>
				<p>
					<strong id="emailInput">이메일 : ${detail.email }</strong>
				</p>
				<p>
					<strong>전화번호 : ${detail.tel }</strong>
				</p>
				<p>
					<strong> 보유기술 : <c:forEach var="skill"
							items="${detail.mySkillList }" varStatus="status">
							<span> <c:if test="${!status.first }"> / </c:if>
								${skill.mySkillName }
							</span>
						</c:forEach>
					</strong>
				</p>

				<strong>직무 : <c:forEach var="job"
						items="${detail.careerList}" varStatus="status">
						<span><c:if test="${!status.first }"></c:if> ${job.jobCode }</span>
						<br />
						<span>재직기간 : ${job.startWorkDate } ~ ${job.retireDate }
							(${job.careerYear} 년)</span>
					</c:forEach>
				</strong>
				<p>
					<strong>주소 : ${detail.address}</strong>
				</p>


				<strong> 보유 자격증 : <c:forEach var="licence"
						items="${detail.myLicenseList}" varStatus="status">
						<span><c:if test="${!status.first}">/</c:if>${licence.resumeNo }</span>
					</c:forEach></strong>
				<%-- <p><strong>${detail. }</p>
     <p><strong>${detail. }</p> --%>
			</div>
			<hr />
			<button onclick="history.back()">목록</button>
			<button class="reject-button" data-user-id="${detail.userId}"
				data-user-email="${detail.email}" data-role="clickable-card">
				이직제안 하기</button>
		</div>
	</c:if>
	<c:if test="${empty detail}">
	값이 없다.
</c:if>
	<!-- 이직 제안 모달 -->
	<div class="modal fade" id="jobOfferModal" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content p-4">
				<div class="modal-header border-0">
					<h5 class="modal-title fw-bold text-primary">이직제안 하기</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="닫기"></button>
				</div>

				<div class="modal-body">
					<!-- 제안 포지션 -->
					<div class="mb-3">
						<label>직무</label> <select id="jobSelect" class="form-select">
							<option value="">선택</option>
							<c:forEach var="job" items="${jobList}">
								<option value="${job.jobCode}"
									data-top-job-code="${job.topJobCode}">${job.jobName}</option>
							</c:forEach>
						</select>
					</div>

					<!-- 직무 -->
					<div class="mb-3">
						<label>직군</label> <select name="topJob" id="positionSelect">
							<c:forEach var="tjob" items="${tjobList}">
								<option value="">선택</option>
								<option value="${tjob.topJobCode}">${tjob.topJobName}</option>
							</c:forEach>
						</select>
					</div>

					<!-- 제안 내용 -->
					<div class="mb-3">
						<label for="offerContent" class="form-label fw-semibold">제안
							내용</label>
						<textarea id="offerContent" class="form-control" rows="4"
							placeholder="😥 제안 내용이 고민이군..."></textarea>
					</div>


					<!-- 전송 버튼 -->
					<button type="submit" id="submitOffer"
						class="btn btn-primary w-100">전송하기</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>