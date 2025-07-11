<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script defer src="/js/company/recruitment/talentpool/talentpool.js"></script>
<script src="/js/company/recruitment/talentpool/joboffer.js"></script>
<div>
	<c:if test="${not empty talentpoolList}">
		<c:forEach items="${talentpoolList}" var="talent">
			<div class="user-card" data-user-id="${talent.userId}">
				<div class="user-avatar"></div>
				<div class="user-info">
					<div class="user-meta">
						<span class="user-name">${talent.userName}</span>
						<c:if test="${not empty talent.careerList}">
							<c:forEach var="career" items="${talent.careerList}">
								<span class="user-experience">경력 ${career.careerYearName}</span>
							</c:forEach>
						</c:if>
					</div>
					<div class="user-position-salary">
						<c:forEach var="joblist" items="${talent.joblist}">
							<c:if test="${not empty joblist.jobName}">
								<span class="position-name">${joblist.jobName}</span>
							</c:if>
							</c:forEach>
							
							<c:forEach var="career" items="${talent.careerList}">
							<span class="working-period"> (${career.startWorkDate} <c:choose>
									<c:when test="${not empty career.retireDate}"> - ${career.retireDate}</c:when>
									<c:otherwise> - 재직중</c:otherwise>
								</c:choose>)
							</span>
							<c:if test="${not empty career.salary}">, 연봉 ${career.salary}</c:if>
						</c:forEach>
					</div>
				</div>
				<div class="action-buttons">
					<button class="save-button">후보자 저장</button>
					
				</div>
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${empty talentpoolList}">
		<p style="text-align: center; margin-top: 50px; color: #777;">검색
			결과가 없습니다.</p>
	</c:if>
</div>

