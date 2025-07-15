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
</head>
<body>

	<div class="container my-4">

    <!-- 🔹 상단: 회사명 + 제목 + 버튼 -->
    <div class="d-flex justify-content-between align-items-start flex-wrap">
        <div>
            <h3 class="fw-normal mb-1">${recruitmentNotice.company.comName}
                <button class="btn btn-outline-secondary btn-sm ms-2">♡ 관심기업</button>
            </h3>
            <h1 class="fw-bold">${recruitmentNotice.recruitmentTitle}</h1>
        </div>
        <div class="d-flex align-items-center gap-2 mt-2">
            <button class="btn border text-center d-flex flex-column align-items-center justify-content-center"
                    style="width:60px; height:48px;">
                <i class="bi bi-star" style="font-size: 18px;"></i>
                <span style="font-size: 14px;"></span>
            </button>
            <button class="btn btn_violet" style="height:48px;" id="applyBtn"
            	data-finish="${recruitmentNotice.recruitmentFinishDate}">
                <span id="dDayCounter"></span><br>입사지원
            </button>
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
	<!-- Bootstrap JS CDN -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>