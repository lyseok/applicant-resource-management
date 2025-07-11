<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>채용 공고 등록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<body>
<div class="container my-5">
    <h2 class="mb-4 fw-bold">채용 공고 등록</h2>

    <form action="/company/recruitment/insert" method="post">

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
                    <select name="yearCode" class="form-select">
                        <option value="">선택</option>
                        <!-- 예시 option -->
                        <option value="01">신입</option>
                        <option value="02">경력</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label text-primary fw-semibold">직무</label>
                    <select name="jobCode" class="form-select">
                        <option value="">선택</option>
                        <!-- jobCode 목록 동적 바인딩 -->
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label text-primary fw-semibold">직급/직책</label>
                    <div id="positionWrapper">
                        <select name="positionList[0].codeDetailName" class="form-select mb-2"></select>
                    </div>
                    <button type="button" class="btn btn-sm btn-outline-secondary" onclick="addPosition()">+ 추가</button>
                </div>

                <div class="mb-3">
                    <label class="form-label text-primary fw-semibold">근무지역</label>
                    <div class="d-flex gap-2">
                        <select name="cityCode" class="form-select"></select>
                        <select name="districtCode" class="form-select"></select>
                    </div>
                </div>
            </div>

            <!-- 우측 -->
            <div class="col-md-6">

                <div class="mb-3">
                    <label class="form-label text-primary fw-semibold">급여</label>
                    <input type="text" name="recruitmentSalary" class="form-control"/>
                </div>

                <div class="mb-3">
                    <label class="form-label text-primary fw-semibold">학력</label>
                    <select name="education.codeDetailName" class="form-select"></select>
                </div>

                <div class="mb-3">
                    <label class="form-label text-primary fw-semibold">기술</label>
                    <div id="skillWrapper">
                        <input type="text" name="skillList[0].recruitSkillName" class="form-control mb-2"/>
                    </div>
                    <button type="button" class="btn btn-sm btn-outline-secondary" onclick="addSkill()">+ 추가</button>
                </div>

            </div>
        </div>

        <hr>

        <!-- 🔹 공고 내용 -->
        <div class="mb-4">
            <label class="form-label fw-semibold">공고 내용</label>
            <textarea name="recContent" rows="6" class="form-control"></textarea>
        </div>

        <!-- 🔹 제출 버튼 -->
        <div class="text-end">
            <button type="submit" class="btn btn-primary">등록</button>
        </div>

    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>