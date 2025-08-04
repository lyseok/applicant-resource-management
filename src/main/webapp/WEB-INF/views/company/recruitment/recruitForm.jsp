<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="jakarta.tags.core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>-

			<head>
				<meta charset="UTF-8">
				<title>채용 공고 등록</title>
				<link rel="stylesheet" href="/css/member/recruiment/recruitmentNotices.css">
				<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
				<script defer src="/js/company/recruitment/recruitForm.js"></script>

				<style type="text/css">
					.notice_form_wrap>div {
						width: calc((100% - 24px) / 2)
					}

					.badge-tag {
						background: var(--violet70);
						color: #fff;
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

					#jobSuggestions {
						max-height: 200px;
						overflow-y: auto;
						display: none;
					}

					#jobSuggestions li {
						cursor: pointer;
					}

					.position-tag button {
						color: #fff;
						font-size: 14px;
						margin-left: 6px;
					}

					#processSection .process-item {
						width: calc((100% - 3rem) / 3);
						border-radius: 10px;
						border: 1px solid var(--gray60);
					}

					.btn-close {
						position: absolute;
						top: 15px;
						right: 15px;
					}
				</style>
			</head>

			<body>
				<h2 class="h2 mb-4 fw-bold" id="data-btn">채용 공고 등록</h2>

				<form method="post" id="recruitForm" enctype="multipart/form-data">


					<div class="d-flex flex-wrap gap-4 notice_form_wrap">
						<!-- 🔹 공고 제목 -->
						<div class="notice_form  w100p">
							<label for="recruitmentTitle" class="form-label fs-16 fw-bold text-dark required">공고 제목</label>
							<input type="text" name="recruitmentTitle" id="recruitmentTitle" class="form-control" />
						</div>

						<!-- 한 컬럼에 2개씩 넣기 -->
						<div class="d-flex notice_form gap-4">
							<div class="flex-fill">
								<label class="form-label fs-16 fw-bold text-dark required">접수마감일</label> <input type="date"
									name="recruitmentFinishDate" class="form-control move-down" />
							</div>

							<div class="flex-fill">
								<label class="form-label fs-16 fw-bold text-dark required">경력</label> <select id="yearCode"
									name="yearCode" class="form-select">
									<option value="">선택</option>

								</select>
							</div>
						</div>

						<!-- 한 컬럼에 2개씩 넣기 -->
						<div class="d-flex notice_form gap-4">
							<div class="flex-fill">
								<label class="form-label fs-16 fw-bold text-dark required">학력</label> <select id="eduCode"
									name="education.codeDetailNo" class="form-select"></select>
							</div>

							<div class="flex-fill">
								<label class="form-label fs-16 fw-bold text-dark required">채용인원수</label> <input type="text"
									name="recPositionNumber" class="form-control" />
							</div>
						</div>

						<div class="notice_form">
							<label class="form-label fs-16 fw-bold text-dark required">직무</label>

							<!-- 상위 직무 select -->
							<select id="upperJobCode" name="upperJobCode" class="form-select">
								<option value="">상위 직무 선택</option>
							</select>

							<!-- 하위 직무 자동완성 입력 -->
							<input type="text" id="jobSearchInput" class="form-control mt-2" placeholder="하위 직무 입력"
								autocomplete="off" />

							<!-- 하위 직무 코드 숨김 제출용 -->
							<input type="hidden" id="hiddenJobCode" name="jobCode" />

							<!-- 자동완성 결과 리스트 -->
							<ul id="jobSuggestions" class="list-group position-absolute w-100 mt-1" style="z-index: 999;"></ul>
						</div>

						<div class="notice_form">
							<label class="form-label fs-16 fw-bold text-dark required">직급/직책</label>

							<!-- select + 버튼 -->
							<div class="d-flex gap-2">
								<select id="positionSelect" class="form-select"></select>
								<button type="button" class="btn btn_violet_line text-nowrap w140 justify-content-center"
									onclick="addPositionTag()">추가</button>
							</div>

							<!-- 선택된 태그들 렌더링 영역 -->
							<div id="positionTagWrapper" class="d-flex flex-wrap gap-2 mt-2"></div>

						</div>

						<div class="notice_form">
							<label class="form-label fs-16 fw-bold text-dark required">급여</label>
							<div class="d-flex gap-2 align-items-center">
								<input type="text" name="recruitmentSalary" class="form-control" placeholder="숫자로 입력해주세요." />
								<span class="text-nowrap fs-14 fw-500">만원</span>
							</div>
							<p class="fs-14 opacity-50 mt-2 fw-semibold">만원 단위로 입력됩니다. 예) 13000 -> 1억 3000만원</p>
						</div>

						<div class="notice_form">
							<label class="form-label fs-16 fw-bold text-dark required">기술</label>
							<div class="d-flex flex-column gap-2">
								<div class="d-flex gap-2">
									<input type="text" id="skillInput" class="form-control" placeholder="기술 입력" />
									<button type="button" id="addSkillBtn"
										class="btn btn_violet_line text-nowrap w140 justify-content-center" onclick="addSkill()">추가</button>
								</div>

								<div id="skillWrapper" class="d-flex gap-2 flex-wrap"></div>
							</div>
						</div>


						<div class="notice_form">
							<label class="form-label fs-16 fw-bold text-dark required">담당자연락처</label>
							<input type="text" name="recruitmentChargerTel" class="form-control" />
						</div>

						<div class="notice_form">
							<label class="form-label fs-16 fw-bold text-dark required">근무지역</label>
							<div class="d-flex gap-2">
								<select id="cityCode" name="cityCode" class="form-select">
									<option value="">지역선택
								</select> <select id="districtCode" name="districtCode" class="form-select"></select>
							</div>
						</div>

						<div class="notice_form">
							<label class="form-label fs-16 fw-bold text-dark required">접수방법</label> <input type="text"
								name="recruitmentDesk" class="form-control" />
						</div>

						<div class="notice_form">
							<label class="form-label fs-16 fw-bold text-dark required">공고 썸네일</label>
							<input type="file" name="recruitThumbnail" class="form-control" id="recruitThumbnail" />
						</div>

						<div class="notice_form w100p mb-4">
							<div class="d-flex align-items-start gap-1 flex-column">
								<div class="d-flex gap-4 align-items-center">
									<label class="form-label fs-16 fw-bold text-dark required mb-0">채용 절차 선택</label>
									<button type="button" class="btn btn_violet_line btn-sm" onclick="addProcess()">전형 추가</button>
								</div>
								<p class="fs-14 opacity-50 fw-semibold mb-2 ">전형추가 버튼을 클릭해 채용 순서를 직접 설정하고, 채용 절차 과정을 직접 설정할 수 있습니다.</p>
							</div>

							<div id="processSection" class="d-flex flex-wrap gap-4">
								<!-- 전형 폼이 여기에 동적으로 들어감 -->
							</div>
						</div>
					</div>


					<!-- 🔹 공고 내용 -->
					<div id="editor"></div>
					<textarea id="recContent" name="recContent" style="display: none;"></textarea>

					<!-- 🔹 제출 버튼 -->
					<div class="text-end mt-4">
						<button type="submit" class="btn btn_violet w140 justify-content-center">공고 등록</button>
					</div>

				</form>


				<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
				<script type="module">
					import { formValues } from '/js/dataConf.js';
					document.getElementById('data-btn').addEventListener('click', () => {
						console.log('click');
						for (const selector in formValues) {
							const el = document.querySelector(selector);
							if (el) el.value = formValues[selector];
						}

						// 직급/직책 추가 버튼 클릭 처리
						const positionSelect = document.querySelector('#positionSelect');
						if (positionSelect && formValues['#positionSelect']) {
							positionSelect.value = formValues['#positionSelect'];
							document.querySelector('.btn.btn_violet_line').click();
						}

						// 기술 카드 추가 처리
						const skills = formValues['#skillInput'];
						if (Array.isArray(skills)) {
							const skillInput = document.querySelector('#skillInput');
							const addSkillBtn = document.getElementById('addSkillBtn');
							for (const skill of skills) {
								skillInput.value = skill;
								addSkillBtn.click();
							}
						}

						// 시/군/구 값 설정 (약간의 지연 필요)
						const cityCode = document.querySelector('#cityCode');
						const districtCode = document.querySelector('#districtCode');
						if (cityCode && formValues['#cityCode']) {
							cityCode.value = formValues['#cityCode'];
							cityCode.dispatchEvent(new Event('change'));
							setTimeout(() => {
								if (districtCode && formValues['#districtCode']) {
									districtCode.value = formValues['#districtCode'];
								}
							}, 500);
						}
					});
				</script>
			</body>