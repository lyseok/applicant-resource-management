<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<head>
<meta charset="UTF-8">
<title>이력서 상세페이지</title>
<link rel="stylesheet" href="/css/member/resume/resume.css">
</head>
<body>
	<div class="resume-main-wrap resume_detail_wrap">
		<!-- Header -->
		<div class="resume-header">
			<div class="profile-img">🙍‍♂️</div>
			<div class="header-info">
				<div class="d-flex align-items-center">
					<span class="name lh1">${resumeList.userName }</span> <span
						class="age lh1">${resumeList.birth }</span>
				</div>
				<div class="contact d-flex gap-4">
					<span><i class='bx  bx-envelope-alt'></i>
						${resumeList.email }</span> <span><i class='bx  bx-mobile'></i>
						${resumeList.tel }</span>
				</div>
				<div class="address d-flex">
					<i class='bx  bx-home-alt'></i> ${resumeList.address }
				</div>
				<c:if test="${not empty resumeList.resumeSubmitYn}">
					<div class="info-badge">대표 이력서 번호 ${resumeList.resumeNo }</div>
				</c:if>
			</div>
		</div>


		<!-- 나의 스킬 - 기술 -->
		<c:if test="${not empty resumeList.mySkillList}">
			<div class="section">
				<div class="section-title">나의 스킬</div>
				<div class="myskill-list">
					<c:forEach items="${resumeList.mySkillList }" var="skill">
						<div class="myskill-item">${skill.mySkillName }</div>
					</c:forEach>
				</div>
			</div>
		</c:if>


		<!-- 학력 -->
		<c:if test="${not empty resumeList.educationList}">
			<div class="section">
				<div class="section-title">학력</div>
				<ul class="section-list card_wrap">
					<c:forEach items="${resumeList.educationList }" var="education">
						<li>
							<div class="list-content">
								<b>${education.schoolName }</b> <span>(${education.graduateYnName })</span>
								<c:if test="${not empty education.specialtyList}">
									<c:forEach items="${education.specialtyList }" var="specialty">
										<div class="list-content">${specialty.mainMajor }/${specialty.subMajorCode }</div>
									</c:forEach>
								</c:if>
								<div class="text-secondary fs-14">${education.entranceDate}	~ ${education.graduateDate}</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>



		<!-- 경력 -->
		<c:if test="${not empty resumeList.careerList}">
			<div class="section">
				<div class="section-title">경력</div>
				<ul class="section-list card_wrap">
					<c:forEach items="${resumeList.careerList }" var="career">
						<li>
							<div class="list-content">
								<div class="">
									<b>${career.company.comName }</b>
									<!-- 회사명 -->
									<span class="text-secondary fs-14">(${career.startWorkDate }
										~ ${career.retireDate })</span>
									<!-- 기간 -->
									<div>${career.department }/ ${career.responsibility }</div>
									<!-- 부서/직무 -->
								</div>
								<div>경력: ${career.careerYearName }</div>
								<!-- 경력기간 -->
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>


		<!-- 포트폴리오 및 기타문서 -->
		<c:if test="${not empty resumeList.portfolioList}">
			<div class="section">
				<div class="section-title">포트폴리오</div>
				<ul class="section-list">
					<c:forEach items="${resumeList.portfolioList }" var="portfolio">
						<li>
							<div class="list-content">
								<b class="h5 fw-bold text-dark">${portfolio.porName }</b>
								<div class="text-secondary fs-14">${portfolio.porStartDate }
									~ ${portfolio.porEndDate }</div>
								<a herf="${portfolio.porUrl }"
									class="my-1 text-decoration-underline">${portfolio.porUrl }</a>
								<div>${portfolio.porInformation }</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>



		<!-- 자기소개서 -->
		<c:if test="${not empty resumeList.introduction}">
			<div class="section">
				<div class="section-title">자기소개서</div>
				<ul class="section-list">
					<li>
						<div class="list-content">
							<b class="h5 fw-bold text-dark">${resumeList.introduction.introductionName }</b>
							<!-- 제목 -->
							<span class="text-secondary fs-14 ms-2">
								${resumeList.introduction.introductionCreateDate}</span>
							<div class="my-3">${resumeList.introduction.introductionQuestion }</div>
							<!-- 문항 -->
							<div>${resumeList.introduction.introductionContent }</div>
							<!-- 내용 -->
						</div>
					</li>
				</ul>
			</div>
		</c:if>



		<!-- 경험 -->
		<c:if test="${not empty resumeList.myExperienceList}">
			<div class="section">
				<div class="section-title">경험</div>
				<ul class="section-list">
					<c:forEach items="${resumeList.myExperienceList }" var="exp">
						<li>
							<div class="list-content">
								<b>${exp.expName }</b>
								<!-- 활동명 -->
								<span>(${exp.expCodeName })</span>
								<!-- 활동 구분 -->
								<div class="text-secondary fs-14">${exp.expStartDate }~
									${exp.expEndDate }</div>
								<!-- 활동기간 -->
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>


		<!-- 자격 -->
		<c:if test="${not empty resumeList.myLicenseList}">
			<div class="section">
				<div class="section-title">자격증</div>
				<ul class="section-list">
					<c:forEach items="${resumeList.myLicenseList }" var="license">
						<li>
							<div class="list-content">
								<b>${license.licenseCode }</b>
								<!-- 활동명 -->
								<span class="text-secondary fs-14">${license.licensePassDate }</span>
								<!-- 활동 구분 -->
								<div>산업인력공단</div>
								<!-- 활동기간 -->
								<div></div>
							</div>
						</li>
					</c:forEach>
				</ul>
				<!-- <li>
		        <span class="list-label">발급기관</span>
		        <span class="list-content">대전남부경찰서(도로교통공단)</span>
		      </li> -->
			</div>
		</c:if>

		<!-- 어학자격증 -->
		<c:if test="${not empty resumeList.languageSkillList}">
			<div class="section">
				<div class="section-title">어학 자격증</div>
				<ul class="section-list">
					<c:forEach items="${resumeList.languageSkillList }" var="lang">
						<li>
							<div class="list-content">
								<b>${lang.languageExamName }</b>
								<!-- 어학 자격명 -->
								<span>(${lang.languageExamLevelCode }) <-- - 바인딩 잘 되는지
									확인필요</span>
								<!--  -->
								<div class="text-secondary fs-14">${lang.passDate }</div>
								<!-- 어학 자격증 취득기간 -->
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>

		<!-- 수상내역 -->
		<c:if test="${not empty resumeList.awardList}">
			<div class="section">
				<div class="section-title">수상</div>
				<ul class="section-list">
					<c:forEach items="${resumeList.awardList }" var="award">
						<li>
							<div class="list-content">
								<b>${award.awardName }</b>
								<!-- 어학 자격명 -->
								<span class="text-secondary fs-14">(${award.awardDate })</span>
								<!--  -->
								<div>${award.hosting }</div>
								<!-- 어학 자격증 취득기간 -->
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>



		<!-- 취업우대사항 -->
		<c:if test="${not empty resumeList.supportList}">
			<div class="section">
				<div class="section-title">취업우대사항</div>
				<ul class="section-list col50">
					<li class="border-0 lh1"><b class="list-content">고용지원대상</b></li>
					<li class="border-0 lh1 "><b class="list-content">장애 등급</b></li>
					<c:forEach items="${resumeList.supportList }" var="sup">
						<li class="py-1">
							<div class="list-content">${sup.disabilityCodeName }</div>
						</li>
						<li class="py-1">
							<div class="list-content">${sup.disabilityLevelCodeName }</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>

		<!-- 병역 -->
		<c:if test="${not empty resumeList.militaryList}">
			<div class="section">
				<div class="section-title">병역</div>
				<ul class="section-list">
					<c:forEach items="${resumeList.militaryList }" var="mil">
						<li>
							<div class="list-content">
								<b>${mil.serviceCategoryCodeName }</b>
								<!-- 복무 -->
								<div>${mil.militaryTypeCodeName }/
									${mil.militaryRankCodeName }</div>
								<!-- 군별 / 계급 -->
								<div>${mil.dischargeCodeName }
									<span class="text-secondary fs-14">(${mil.militaryStartDate }
										~ ${mil.militaryEndDate })</span>
								</div>
								<!-- 전역사유 / 복무기간 -->
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
	</div>
	<div class="d-flex justify-content-between">
		<a href="/mypage/resume/list" class="btn btn_gray_line">목록</a>
		<div class="d-flex gap-1">
			<a href="" class="btn btn_red_line">삭제</a> <a href=""
				class="btn btn_violet_line">수정</a>
		</div>
	</div>
</body>