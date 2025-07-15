<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>채용 공고 등록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/css/member/recruiment/recruitmentNotices.css">
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
    <script defer src="/js/company/recruitment/recruitForm.js"></script>
</head>
<body>
<body>
<div class="container my-5">
    <h2 class="mb-4 fw-bold">채용 공고 등록</h2>

    <form method="post" id="recruitForm">

        <!-- 🔹 공고 제목 -->
        <div class="mb-4">
            <label for="recruitmentTitle" class="form-label fw-semibold">공고 제목</label>
            <input type="text" name="recruitmentTitle" id="recruitmentTitle" class="form-control"/>
        </div>

        <hr>

        <!-- 🔹 2단 레이아웃 -->
        <div class="row">
            <!-- 좌측 -->
            <div class="col-md-6">

                <div class="mb-3">
                    <label class="form-label text-primary fw-semibold">경력</label>
                    <select id="yearCode" name="yearCode" class="form-select">
                        <option value="">선택</option>
                        
                    </select>
                </div>

					<div class="mb-3">
                    <label class="form-label text-primary fw-semibold">직무</label>
                    <select id="upperJobCode" name="upperJobCode"
						class="form-select">
						<option value="">상위 직무 선택</option>
						<!-- 여기에 상위 직무 옵션들 추가 예정 -->
					</select>
                    <select id="jobCode" name="jobCode" class="form-select">
                        <option value="">선택</option>
                        <!-- jobCode 목록 동적 바인딩 -->
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label text-primary fw-semibold">직급/직책</label>
                    <div id="positionWrapper">
                        <select name="positionList[0].codeDetailNo" class="form-select mb-2 rank"></select>
                    </div>
                    <button type="button" class="btn btn-sm btn-outline-secondary" onclick="addPosition()">+ 추가</button>
                </div>
                
                <div class="mb-3">
                    <label class="form-label text-primary fw-semibold">담당자연락처</label>
                    <input type="text" name="recruitmentChargerTel" class="form-control"/>
                </div>

                <div class="mb-3">
                    <label class="form-label text-primary fw-semibold">근무지역</label>
                    <div class="d-flex gap-2">
                        <select id="cityCode" name="cityCode" class="form-select"></select>
                        <select id="districtCode" name="districtCode" class="form-select"></select>
                    </div>
                </div>
                
                <div class="mb-3">
                    <label class="form-label text-primary fw-semibold">접수방법</label>
                    <input type="text" name="recruitmentDesk" class="form-control"/>
                </div>
            </div>

            <!-- 우측 -->
            <div class="col-md-6">

                <div class="mb-3">
                    <label class="form-label text-primary fw-semibold">급여</label>
                    <input type="text" name="recruitmentSalary" class="form-control"/>
                </div>

                <div class="mb-5">
                    <label class="form-label fw-semibold">학력</label>
                    <select id="eduCode" name="education.codeDetailNo" class="form-select"></select>
                </div>
                
                
                <div class="mb-3">
                    <label class="form-label text-primary fw-semibold">기술</label>
                    <div id="skillWrapper">
                        <input type="text" name="skillList[0].recruitSkillName" class="form-control mb-2 skill"/>
                    </div>
                    <button type="button" class="btn btn-sm btn-outline-secondary" onclick="addSkill()">+ 추가</button>
                </div>
                
                <div class="mb-3">
                    <label class="form-label text-primary fw-semibold">접수시작일</label>
                    <input type="date" name="recruitmentReceiptStart" class="form-control"/>
                </div>
                
                <div class="mb-3">
                    <label class="form-label text-primary fw-semibold">접수마감일</label>
                    <input type="date" name="recruitmentFinishDate" class="form-control"/>
                </div>

            </div>
        </div>

        <hr>

        <!-- 🔹 공고 내용 -->
      	<div id="editor"></div>
      	<textarea id="recContent" name="recContent" style="display:none;"></textarea>

		<div class="mb-4">
			<label class="form-label fw-semibold"></label>
			<div id="processSection" class="d-flex flex-wrap gap-4">
				<!-- 전형 폼이 여기에 동적으로 들어감 -->
			</div>
			<button type="button" class="btn btn-outline-primary mt-2"
				onclick="addProcess()">+ 전형 추가</button>
		</div>

		<!-- 🔹 제출 버튼 -->
        <div class="text-end">
            <button type="submit" class="btn btn_violet">등록</button>
        </div>

    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
</body>
</html>