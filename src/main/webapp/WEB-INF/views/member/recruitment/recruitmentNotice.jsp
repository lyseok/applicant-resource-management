<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>채용 상세 정보</title>
<link rel="stylesheet" href="/css/member/recruiment/recruitmentNotices.css">
<script defer src="/js/member/recruitment/recruitmentNotice.js"></script>
<!-- Bootstrap CSS CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<style>
.dot {
	width: 8px;
	height: 8px;
	background: #bbb;
	border-radius: 50%;
	display: inline-block;
	margin-right: 12px;
}

.section-title {
	font-size: 1.17rem;
	font-weight: 600;
	margin-bottom: 1rem;
}

.badge-recruit {
	background: #22c55e;
}

.shadow-card {
	box-shadow: 0 4px 32px 0 rgba(0, 0, 0, 0.09);
}

.list-dot li {
	display: flex;
	align-items: center;
	gap: 10px;
}

.list-dot .dot {
	margin: 0;
}

.btn-purple {
	background: #9333ea;
	color: #fff;
}

.btn-purple:hover {
	background: #7c2dd6;
	color: #fff;
}

.text-purple {
	color: #ae8be1 !important;
}

.bg-purple {
	background: #ae8be1 !important;
}

.selected-card {
	border: 2px solid #ae8be1 !important;
	background: #f6f2ff !important;
}

.resume-card {
	cursor: pointer;
	border: 1.5px solid #eee;
}

.resume-card:hover {
	border-color: #ae8be1 !important;
}
</style>
</head>
<body>

	<div class="container my-4">

    <!-- 🔹 상단: 회사명 + 제목 + 버튼 -->
    <div class="d-flex justify-content-between align-items-start flex-wrap">
    	<input type="hidden" id="recruitNo" data-no="${recruitmentNotice.recruitmentNo}"> 
        <div>
            <h3 class="fw-normal mb-1" id="title">${recruitmentNotice.company.comName}
                <button class="btn btn-outline-secondary btn-sm ms-2">♡ 관심기업</button>
            </h3>
            <h1 class="fw-bold">${recruitmentNotice.recruitmentTitle}</h1>
        </div>
        <div class="d-flex align-items-center gap-2 mt-2">
            <c:if test="${userInfo.userRole eq 'ROLE_USER' }">
            <button class="btn border text-center d-flex flex-column align-items-center justify-content-center"
                    style="width:60px; height:60px;">
                <i class="bi bi-star" style="font-size: 18px;"></i>
                <span style="font-size: 14px;"></span>
            </button>
            <button class="btn btn_violet" style="height:60px; width:200px;" id="applyBtn"
            	data-finish="${recruitmentNotice.recruitmentFinishDate}"
            	data-title="${recruitmentNotice.recruitmentTitle}">
                <span id="dDayCounter">Loading...</span><br>입사지원
            </button>
            </c:if>
            
            <c:if test="${userInfo.userId eq recruitmentNotice.userId }">
            <button class="btn btn_violet" style="height:48px;" id="applyBtn"
            	data-finish="${recruitmentNotice.recruitmentFinishDate}"
            	data-title="${recruitmentNotice.recruitmentTitle}">
                <span id="dDayCounter">Loading...</span><br>마감
            </button>
            </c:if>
            
        </div>
    </div>

    <hr>

    <!-- 🔹 요약 정보 (한 줄 2컬럼: label + value 수평정렬) -->
    <div class="row row-cols-1 row-cols-md-2 g-3 mt-3">
        <div><span class="text-primary fw-semibold">경력:</span> ${recruitmentNotice.yearCodeName}</div>
        <div><span class="text-primary fw-semibold">급여:</span> ${recruitmentNotice.recruitmentSalary}</div>
        <div><span class="text-primary fw-semibold">직무:</span> ${recruitmentNotice.jobCodeName}</div>

        <div><span class="text-primary fw-semibold">학력:</span> ${recruitmentNotice.education.codeDetailName}</div>
        <div>
        	<span class="text-primary fw-semibold">직급/직책:</span>
        	<c:forEach var="position" items="${recruitmentNotice.positionList}">
        	 	${position.codeDetailName}
        	</c:forEach>
        </div>

        <div>
        	<span class="text-primary fw-semibold">기술 :</span>
        	<c:forEach var="skill" items="${recruitmentNotice.skillList}">
        	 	${skill.recruitSkillName}
        	</c:forEach>
        </div>

        <div class="col-12">
            <span class="text-primary fw-semibold">근무지역:</span>
            ${recruitmentNotice.cityCodeName} ${recruitmentNotice.districtCodeName}
        </div>
    </div>

    <hr class="my-4">

    <!-- 🔹 공고 내용 -->
    <div class="mb-4">
        <h4 class="fw-bold mb-2">공고 내용</h4>
        <p>${recruitmentNotice.recContent}</p>
    </div>

    <!-- 🔹 전형 절차 -->
    <div class="mb-4">
        <h4 class="fw-bold mb-2">전형 절차</h4>
        <ul class="list-group list-group-flush">
            <c:forEach var="process" items="${recruitmentNotice.processList}">
                <li class="list-group-item">${process.recruitProcessTypeName}</li>
            </c:forEach>
        </ul>
    </div>

    <!-- 🔹 복리후생 -->
    <div class="mb-4">
        <h4 class="fw-bold mb-2">복리후생</h4>
        <p>${recruitmentNotice.welfare}</p>
    </div>

    <!-- 🔹 문의 -->
    <div class="mb-5">
        <h4 class="fw-bold mb-2">문의</h4>
        <p><strong>${recruitmentNotice.recruitmentChargerTel}</strong><br>${recruitmentNotice.recruitmentDesk}</p>
    </div>
</div>

<!-- 프로젝트 지원 모달 -->
  <div class="modal fade" id="applicationModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered" style="max-width: 450px;">
      <div class="modal-content">
        <!-- 헤더 -->
        <div class="modal-header bg-white">
          <h5 class="modal-title text-purple fw-semibold" id="recruitTitle">프로젝트 제목</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>

        <!-- 본문 -->
        <div class="modal-body bg-light py-4 px-4">

          <!-- 이력서 선택 -->
          <div class="mb-4">
            <div class="d-flex justify-content-between align-items-center mb-2">
              <label class="form-label fw-medium text-secondary">선택된 이력서</label>
              <button type="button" class="btn btn-link p-0 text-secondary" id="btnShowResumeList">이력서 변경
                &gt;</button>
            </div>
            <!-- 선택된 이력서 카드 -->
            <div id="selectedResumeCard"></div>
            <!-- 이력서 목록 (토글) -->
            <div id="resumeList" class="mt-2 overflow-auto" style="display: none; max-height:220px;"></div>
          </div>

          <!-- 첨부파일 -->
          <div class="mb-3">
            <div class="d-flex justify-content-between align-items-center mb-2">
              <label class="form-label fw-medium text-secondary">첨부파일 0건</label>
              <button class="btn btn-outline-secondary btn-sm" type="button">
                <i class="bi bi-plus-lg"></i> 파일추가
              </button>
            </div>
            <div class="text-center py-4 text-secondary bg-white rounded border-2 border-dashed"
              style="font-size: .97em;">
              첨부된 파일이 없습니다.
            </div>
          </div>
        </div>

        <!-- 하단 버튼 -->
        <div class="modal-footer bg-white">
          <button id="btnSaveApplication" class="btn w-100 py-2 text-white"
            style="background: #ae8be1;">지원</button>
        </div>
      </div>
    </div>
  </div>
</body>
	<!-- Bootstrap JS CDN -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</html>