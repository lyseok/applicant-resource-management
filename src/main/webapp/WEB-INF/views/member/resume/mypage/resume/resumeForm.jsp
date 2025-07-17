<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
<meta charset="UTF-8">
<title>이력서 등록</title>
<link rel="stylesheet" href="/css/member/resume/resume.css">
<script type="text/javascript" src="/js/member/resume/resumeForm.js" defer></script>
<c:if test="${not empty hasIntrod }">
		<script>
			alert("${hasIntrod}");
			location.href="/mypage/introduction/create";
		</script>
</c:if>
</head>
<body>
	<div class="d-flex align-items-start">
		<div id="quick_menu">
			<a href="#resumeInfoView" class="on"><i class='bx  bx-hashtag'></i> 기본정보</a>
			<a href="#section-educationList"><i class='bx  bx-hashtag'></i> 학력</a>
			<a href="#section-introduction"><i class='bx  bx-hashtag'></i> 자기소개서</a>
			<a href="#section-portfolioList"><i class='bx  bx-hashtag'></i> 포트폴리오</a>
			<a href="#section-careerList"><i class='bx  bx-hashtag'></i> 경력</a>
			<a href="#section-mySkillList"><i class='bx  bx-hashtag'></i> 보유기술</a>
			<a href="#section-myExperienceList"><i class='bx  bx-hashtag'></i> 보유경험</a>
			<a href="#section-supportList"><i class='bx  bx-hashtag'></i> 고용지원 정보</a>
			<a href="#section-myLicenseList"><i class='bx  bx-hashtag'></i> 보유자격</a>
			<a href="#section-languageSkillList"><i class='bx  bx-hashtag'></i> 어학</a>
			<a href="#section-awardList"><i class='bx  bx-hashtag'></i> 수상</a>
			<a href="#section-militaryList"><i class='bx  bx-hashtag'></i> 병역</a>
		</div>
		<div class="resume-wrap resume_form_wrap">
	
			<!-- Header -->
			<div class="resume-header-wrap" id="resumeInfoView">
				<div class="resume-header">
					<div class="profile-img">${memberInfo.memImg }</div>
					<div class="header-info">
						<div class="d-flex align-items-end">
							<span class="name lh1" id="viewName">${memberInfo.memName }</span>
							<span class="age lh1" id="viewBirth">${memberInfo.memBir }</span>
						</div>
						<div class="contact d-flex gap-4">
							<span class="d-flex align-items-center gap-1">
								<i class='bx  bx-envelope-alt'></i>
								<span id="viewEmail">${memberInfo.memEmail }</span>
							</span>
							<span class="d-flex align-items-center gap-1">
								<i class='bx  bx-mobile'></i> 
								<span id="viewTel">${memberInfo.memTel }</span>
							</span>
						</div>
						<div class="address d-flex align-items-center gap-1" >
							<i class='bx  bx-home-alt'></i> 
							<span id="viewAddress">${memberInfo.memAdd1 }	${memberInfo.memAdd2 }</span>
						</div>
						<button type="button" id="resumeEditBtn" class="btn btn_violet_line mt-2">수정</button>
					</div>
				</div>
			</div>
	
			<!-- 수정 영역 -->
			<div id="resumebasicInfoWrap" class="d-none section">
				<div id="form-resumeInfo" class="section-form-wrap">
				  <div class="section-form-row">
				    <label class="form-label required">이름</label>
				    <input type="text" class="form-control" id="inputUserName" disabled>
				  </div>
				
				  <div class="section-form-row">
				    <label class="form-label required">생년월일</label>
				    <input type="date" class="form-control" id="inputBirth">
				  </div>
				
				  <div class="section-form-row">
				    <label class="form-label required">이메일</label>
				    <input type="email" class="form-control" id="inputEmail">
				  </div>
				
				  <div class="section-form-row">
				    <label class="form-label required">연락처</label>
				    <input type="text" class="form-control" id="inputTel" placeholder="예: 010-1234-5678">
				  </div>
				
				  <div class="section-form-row">
				    <label class="form-label required">주소</label>
				    <input type="text" class="form-control mb-2" id="inputAddress" placeholder="기본주소">
				  </div>
				
				  <div class="section-form-btns">
				    <button type="button" class="btn btn_red_line" id="cancelBasicBtn">취소</button>
				    <button type="button" class="btn btn_violet_line" id="saveBasicBtn">확인</button>
				  </div>
				</div>
			</div>
	
		<div id="resumeData"
		  data-user-id="${memberInfo.userId}"
		  data-user-name="${memberInfo.memName}"
		  data-birth="${memberInfo.memBir}"
		  data-email="${memberInfo.memEmail}"
		  data-tel="${memberInfo.memTel}"
		  data-address="${memberInfo.memAdd1}||${memberInfo.memAdd2}">
		</div>
			<form action="post" id="resume_form">
	
				<!-- 학력 -->
				<!-- 학력 (EDUCATION) 입력폼 -->
				<div class="section" id="section-educationList">
					<div class="section-title">
						<h6>
							학력<span class="must">필수</span>
						</h6>
						<button class="add-btn" type="button" id="btn-educationList">+
							추가</button>
					</div>
					<div class="section-content" style="color: #b8bfc9;">졸업학교 정보를
						입력해 주세요</div>
					<div class="formContainer"></div>
					<div class="listContainer"></div>
				</div>
				
	
				<!-- 자기소개서 (INTRODUCTION) 입력폼 -->
				<div class="section" id="section-introduction">
					<div class="section-title">
						<h6>
							자기소개서<span class="must">필수</span>
						</h6>
						<button class="btn-introduction" type="button"
							id="btn-introduction" data-bs-toggle="modal"
							data-bs-target="#introductionModal">+ 자소서 리스트 불러오기</button>
					</div>
					<div class="section-content" style="color: #b8bfc9;">자기소개서를
						작성해주세요.</div>
					<div class="formContainer">
	
						<div class="modal fade modal-dialog modal-dialog-centered"
							id="introductionModal" tabindex="-1" style="display: none;">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title">자기소개서 선택</h5>
										<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
									</div>
									<div class="modal-body">
										<div class="section-form-row w-100">
											<label class="required">자기소개서</label> <select
												id="introductionSelect" class="form-select">
												<option value="">자기소개서를 선택하세요</option>
											</select>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn_red_line"
											data-bs-dismiss="modal">취소</button>
										<button type="button" class="btn btn_violet"
											id="confirmIntroduction">확인</button>
									</div>
								</div>
							</div>
						</div>
	
					</div>
					<div class="listContainer"></div>
				</div>
	
				<!-- 경력 (CAREER) 입력폼 -->
				<div class="section" id="section-careerList">
					<div class="section-title">
						<h6>경력</h6>
						<button class="add-btn" type="button" id="btn-careerList">+
							추가</button>
					</div>
					<div class="section-content" style="color: #b8bfc9;">경력사항 또는
						인턴, 현장실습 등 실제로 해본 경험이 있다면 자세히 작성해주세요!</div>
					<div class="formContainer"></div>
					<div class="listContainer"></div>
				</div>
	
	
				<!-- 스킬 -->
				<!-- 보유기술 (MY_SKILL) 입력폼 -->
				<div class="section" id="section-mySkillList">
					<div class="section-title">
						<h6>보유기술</h6>
						<button class="add-btn" type="button" id="btn-mySkillList">+
							추가</button>
					</div>
					<div class="section-content" style="color: #b8bfc9;">주요 보유기술,
						툴, 언어 등을 입력해주세요.</div>
					<div class="formContainer"></div>
					<div class="listContainer"></div>
				</div>
	
	
				<!-- 보유경험 (MY_EXPERIENCE) 입력폼 -->
				<div class="section" id="section-myExperienceList">
					<div class="section-title">
						<h6>보유경험</h6>
						<button class="add-btn" type="button" id="btn-myExperienceList">+추가</button>
					</div>
					<div class="section-content" style="color: #b8bfc9;">대외활동, 인턴,교육 등 주요 경험을 입력해주세요.</div>
					<div class="formContainer"></div>
					<div class="listContainer"></div>
				</div>
	
	
				<!-- 활동 - 고용지원 (SUPPORT) 입력폼 -->
				<div class="section" id="section-supportList">
					<div class="section-title">
						<h6>고용지원 정보</h6>
						<button class="add-btn" type="button" id="btn-supportList">+
							추가</button>
					</div>
					<div class="section-content" style="color: #b8bfc9;">장애 등 고용지원
						대상 정보를 입력해주세요.</div>
					<div class="formContainer"></div>
					<div class="listContainer"></div>
				</div>
	
	
	
				<!-- 보유자격 (MY_LICENSE) 입력폼 -->
				<div class="section" id="section-myLicenseList">
					<div class="section-title">
						<h6>보유자격</h6>
						<button class="add-btn" type="button" id="btn-myLicenseList">+
							추가</button>
					</div>
					<div class="section-content" style="color: #b8bfc9;">보유한 자격증
						정보를 입력해주세요.</div>
					<div class="formContainer"></div>
					<div class="listContainer"></div>
				</div>
	
	
				<!-- 어학 (LANGUAGE_SKILL) 입력폼 -->
				<div class="section" id="section-languageSkillList">
					<div class="section-title">
						<h6>어학</h6>
						<button class="add-btn" type="button" id="btn-languageSkillList">+
							추가</button>
					</div>
					<div class="section-content" style="color: #b8bfc9;">어학 자격증,
						시험 정보 등을 입력해주세요.</div>
					<div class="formContainer"></div>
					<div class="listContainer"></div>
				</div>
	
	
				<!-- 수상 (AWARD) 입력폼 -->
				<div class="section" id="section-awardList">
					<div class="section-title">
						<h6>수상</h6>
						<button class="add-btn" type="button" id="btn-awardList">+
							추가</button>
					</div>
					<div class="section-content" style="color: #b8bfc9;">주요 수상 이력을
						입력해주세요.</div>
					<div class="formContainer"></div>
					<div class="listContainer"></div>
				</div>
	
	
				<!-- 포트폴리오 (PORTFOLIO) 입력폼 -->
				<div class="section" id="section-portfolioList">
					<div class="section-title">
						<h6>포트폴리오</h6>
						<button class="add-btn" type="button" id="btn-portfolioList">+
							추가</button>
					</div>
					<div class="section-content" style="color: #b8bfc9;">주요 프로젝트,
						작업 포트폴리오 정보를 입력해주세요.</div>
					<div class="formContainer"></div>
					<div class="listContainer"></div>
				</div>
	
				<!-- 병역 (MILITARY) 입력폼 -->
				<div class="section" id="section-militaryList">
					<div class="section-title">
						<h6>병역</h6>
						<button class="add-btn" type="button" id="btn-militaryList">+
							추가</button>
					</div>
					<div class="section-content" style="color: #b8bfc9;">병역 정보를 입력해주세요.</div>
					<div class="formContainer"></div>
					<div class="listContainer"></div>
				</div>
	
				<div class="" id="form-resume">
					<input type="hidden" name="resumeName" id="hiddenResumeName" value="${memberInfo.memName }님의 이력서 ${resumeCnt + 1}">
					<input type="hidden" name="userName" id="hiddenUserName">
					<input type="hidden" name="birth" id="hiddenBirth">
					<input type="hidden" name="email" id="hiddenEmail">
					<input type="hidden" name="tel" id="hiddenTel">
					<input type="hidden" name="address" id="hiddenAddress">
					<input type="hidden" name="userId" value="${memberInfo.userId}">
					<input type="hidden" name="resumeSubmitYn" value="N">
					<div class="resume_ft">
						<div class="inner">
							<div class="section-form-row">
								<label class="required h3 m-0">이력서 제목</label>
								<input type="text" name="resumeName" value="" placeholder="이력서 제목을 입력해주세요.(미입력 시 기본 제목으로 등록.)" class="h50">
							</div>
	
							<div class="section-form-btns">
								<a href="/mypage/resume/list" class="btn btn_red_line" onclick="showLoading()">취소</a>
								<button type="submit" class="btn btn_violet">등록</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- 삭제 확인 모달 -->
	<div class="modal fade" id="deleteResumeList" tabindex="-1"
		aria-labelledby="deleteResumeModal" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header border-0">
					<h1 class="modal-title fs-5 fw-bold text-danger"
						id="deleteModalLabel">
						<i class="bi bi-exclamation-triangle-fill me-1"></i> 이력서 입력 정보 삭제
					</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body text-center py-4">
					<div
						style="font-size: 2.4rem; color: #dc3545; margin-bottom: 10px;">❗</div>
					<p class="fs-5 mb-2 fw-semibold" style="color: #c82333;">
						해당 입력 정보를 <span style="color: #dc3545;">정말 삭제</span>하시겠습니까?
					</p>
					<p class="text-secondary mb-0" style="font-size: 1.08rem;">
						삭제된 데이터는 복구할 수 없습니다.<br> 실행 전 반드시 다시 한 번 확인해 주세요.
					</p>
				</div>
				<div class="modal-footer border-0 justify-content-center">
					<button type="button" class="btn btn-outline-secondary px-4"
						data-type="introduction" data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger px-4">삭제하기</button>
				</div>
			</div>
		</div>
	</div>
</body>