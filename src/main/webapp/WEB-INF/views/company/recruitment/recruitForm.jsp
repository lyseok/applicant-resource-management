<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채용 공고 등록</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="/css/member/recruiment/recruitmentNotices.css">
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script defer src="/js/company/recruitment/recruitForm.js"></script>
<style type="text/css">
#skillWrapper {
	display: flex;
	flex-wrap: wrap;
	gap: 8px;
	margin-bottom: 10px;
}

.skill-tag {
	background-color: #8a2be2; /* violet */
	color: white;
	padding: 6px 12px;
	border-radius: 50px;
	display: inline-flex;
	align-items: center;
	font-size: 14px;
	max-width: fit-content;
}

.skill-tag button {
	background: none;
	border: none;
	color: white;
	font-size: 14px;
	margin-left: 6px;
	cursor: pointer;
	padding: 0;
	line-height: 1;
}

#skillInput {
	height: 38px;
	font-size: 14px;
}

#addSkillBtn {
	height: 38px;
	padding: 0 12px;
	font-size: 14px;
	background: #8a2be2;
	color: white;
	border: none;
	border-radius: 6px;
	white-space: nowrap;
}

#addSkillBtn:hover {
	background: #7326c2;
}

input[type="date"].move-down {
  margin-top: 6px; /* 👈 필요한 만큼 조정 (4~8px 사이 추천) */
}

#jobSuggestions {
  max-height: 200px;
  overflow-y: auto;
  display: none;
}

#jobSuggestions li {
  cursor: pointer;
}

.position-tag {
  background-color: #8a2be2;
  color: white;
  padding: 6px 12px;
  border-radius: 50px;
  display: inline-flex;
  align-items: center;
  font-size: 14px;
}

.position-tag button {
  background: none;
  border: none;
  color: white;
  font-size: 14px;
  margin-left: 6px;
  cursor: pointer;
  padding: 0;
  line-height: 1;
}

</style>
</head>
<body>
<body>
	<div class="container my-5">
		<h2 class="mb-4 fw-bold">채용 공고 등록</h2>

		<form method="post" id="recruitForm">

			<!-- 🔹 공고 제목 -->
			<div class="mb-4">
				<label for="recruitmentTitle" class="form-label fw-semibold">공고
					제목</label> <input type="text" name="recruitmentTitle" id="recruitmentTitle"
					class="form-control" />
			</div>

			<hr>

			<!-- 🔹 2단 레이아웃 -->
			<div class="row">
				<!-- 좌측 -->
				<div class="col-md-6">

					<div class="mb-3">
						<label class="form-label text-primary fw-semibold">경력</label> <select
							id="yearCode" name="yearCode" class="form-select">
							<option value="">선택</option>

						</select>
					</div>

					<div class="mb-3">
						<label class="form-label fw-semibold text-primary">직무</label>

						<!-- 상위 직무 select -->
						<select id="upperJobCode" name="upperJobCode" class="form-select">
							<option value="">상위 직무 선택</option>
						</select>

						<!-- 하위 직무 자동완성 입력 -->
						<input type="text" id="jobSearchInput" class="form-control mt-2"
							placeholder="하위 직무 입력" autocomplete="off" />

						<!-- 하위 직무 코드 숨김 제출용 -->
						<input type="hidden" id="hiddenJobCode" name="jobCode" />

						<!-- 자동완성 결과 리스트 -->
						<ul id="jobSuggestions"
							class="list-group position-absolute w-100 mt-1"
							style="z-index: 999;"></ul>
					</div>

					<div class="mb-3">
						<label class="form-label text-primary fw-semibold">직급/직책</label>

						<!-- 선택된 태그들 렌더링 영역 -->
						<div id="positionTagWrapper" class="d-flex flex-wrap gap-2 mb-2"></div>

						<!-- select + 버튼 -->
						<div class="d-flex gap-2">
							<select id="positionSelect" class="form-select"
								style="max-width: 200px;"></select>
							<button type="button" class="btn btn-outline-secondary btn-sm"
								onclick="addPositionTag()">+ 추가</button>
						</div>
					</div>

					<div class="mb-3">
						<label class="form-label text-primary fw-semibold">담당자연락처</label>
						<input type="text" name="recruitmentChargerTel"
							class="form-control" />
					</div>

					<div class="mb-3">
						<label class="form-label text-primary fw-semibold">근무지역</label>
						<div class="d-flex gap-2">
							<select id="cityCode" name="cityCode" class="form-select">							
								<option value="">지역선택
							</select>
							<select id="districtCode" name="districtCode" class="form-select"></select>
						</div>
					</div>

					<div class="mb-3">
						<label class="form-label text-primary fw-semibold">접수방법</label> <input
							type="text" name="recruitmentDesk" class="form-control" />
					</div>
				</div>

				<!-- 우측 -->
				<div class="col-md-6">

					<div class="mb-3">
						<label class="form-label text-primary fw-semibold">급여</label> <input
							type="text" name="recruitmentSalary" class="form-control" />
					</div>

					<div class="mb-5">
						<label class="form-label fw-semibold">학력</label> <select
							id="eduCode" name="education.codeDetailNo" class="form-select"></select>
					</div>


					<div class="mb-3">
						<label class="form-label text-primary fw-semibold">기술</label>
						<div id="skillWrapper"></div>

						<div class="d-flex gap-2 mb-3">
							<input type="text" id="skillInput" class="form-control"
								placeholder="기술 입력" />
							<button type="button" id="addSkillBtn" onclick="addSkill()">추가</button>
						</div>
					</div>

					<div class="mb-3">
						<label class="form-label text-primary fw-semibold">접수마감일</label> 
						<input type="date" name="recruitmentFinishDate" class="form-control move-down" />
					</div>

					<div class="mb-3">
						<label class="form-label text-primary fw-semibold">채용인원수</label> <input
							type="text" name="recPositionNumber" class="form-control" />
					</div>

				</div>
			</div>

			<hr>

			<!-- 🔹 공고 내용 -->
			<div id="editor"></div>
			<textarea id="recContent" name="recContent" style="display: none;"></textarea>

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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
</body>
</html>