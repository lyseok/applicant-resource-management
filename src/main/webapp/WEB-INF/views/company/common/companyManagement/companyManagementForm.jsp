<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>기업 정보 관리</title>
		<script src="/js/company/common/companyManagement/companyManagementForm.js"></script>
		<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
		<link href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" rel="stylesheet">
		<style type="text/css">
			.company-info-wrap {
				padding: 30px;
				background-color: #fff;
				border-radius: 8px;
				box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
			}

			/* 섹션 제목 */
			.company-info-wrap .section-title {
				display: flex;
				justify-content: space-between;
				font-size: 18px;
				font-weight: 700;
				color: #2d3554;
				margin-bottom: 14px;
				padding-bottom: 10px;
				border-bottom: 1px solid #333;
			}

			.company-info-wrap .section-title h6 {
				font-weight: bold;
			}

			/* 섹션 내용 */
			.company-info-wrap .section-content {
				font-size: 15px;
				color: #434b62;
				min-height: 22px;
			}

			/* 섹션 폼 */
			.company-info-wrap .section-form-wrap {
				display: flex;
				flex-wrap: wrap;
				/* 두 개씩 나란히 배치 */
				gap: 20px;
				background: #fff;
				border: 1.5px solid #dbdbdb;
				border-radius: 12px;
				padding: 34px 30px 22px 30px;
				position: relative;
			}

			/* 각 입력 필드 */
			.company-info-wrap .section-form-row {
				display: flex;
				flex-direction: column;
				gap: 8px;
				width: calc(50% - 10px);
				/* 두 개씩 나란히 배치 */
			}

			/* 기업명, 기업설명, 업종 코드, 업종 설명 필드 수직 배치 */
			.company-info-wrap .section-form-row.vertical {
				width: 100%;
				/* 100%로 너비를 확장하여 수직으로 배치 */
			}

			.company-info-wrap .section-form-row label {
				font-size: 15px;
				color: var(--violet100);
				font-weight: bold;
			}

			.company-info-wrap .section-form-row input,
			.company-info-wrap .section-form-row select,
			.company-info-wrap .section-form-row textarea {
				width: 100%;
				/* 입력 필드 전체 너비 */
				padding: 10px 15px;
				border-radius: 7px;
				border: 1px solid #dbdbdb;
				font-size: 15px;
				background: #fff;
				outline: none;
				transition: border 0.2s;
			}

			.company-info-wrap .section-form-row textarea {
				min-height: 80px;
			}

			/* 각 섹션 간의 간격을 위한 padding 추가 */
			.company-info-wrap .section {
				padding-bottom: 40px;
			}

			/* 작은 화면에서 한 줄에 하나씩 입력 필드 표시 */
			@media (max-width : 768px) {
				.company-info-wrap .section-form-row {
					width: 100%;
					/* 화면이 작을 때 한 줄로 표시 */
				}
			}


			/* 간단한 스타일 */
			#logoPreview {
				max-height: 100px;
				margin-bottom: 8px;
				display: none;
			}

			.form-row {
				margin-bottom: 12px;
			}

			label {
				display: block;
				margin-bottom: 4px;
				font-weight: bold;
			}



			/* — 이미지 업로드 전용 섹션 폼 */
			.section-form-wrap.file-upload {
				display: flex;
				flex-wrap: wrap;
				gap: 20px;
				background: #fafbfc;
				border: 1px solid #e3e8ee;
				border-radius: 8px;
				padding: 24px 30px;
				margin-bottom: 32px;
				align-items: flex-start;
			}

			/* 로고/배경 각 50% 너비 */
			.section-form-wrap.file-upload .section-form-row {
				width: calc(50% - 10px);
			}

			/* 부가 이미지들(full width) */
			.section-form-wrap.file-upload .section-form-row.extra {
				width: 100%;
			}

			/* 파일 input 만 살짝 높이 맞추기 */
			.section-form-wrap.file-upload input[type="file"] {
				padding: 8px 12px;
			}


			/* 1) img 에 최소 높이 주기 */
			.section-form-wrap.file-upload .section-form-row img {
				min-height: 200px;
				/* 원하는 높이로 맞추세요 */
				width: auto;
				/* 비율 유지 */
				display: block;
				margin-bottom: 12px;
			}

			/* 2) src 속성이 없으면 빈 박스만 보이게(투명) */
			.section-form-wrap.file-upload .section-form-row img:not([src]) {
				visibility: hidden;
				/* 빈 박스만 차지, 안 보임 */
			}
		</style>
	</head>

	<body>
		<p class="h2" id="fillCorpInfo">기업 정보 관리</p>
		<form id="companyForm" enctype="multipart/form-data">
			<div class="company-info-wrap">
				<div class="section" id="section-company-basic">

					<div class="section-form-wrap">
						<div class="section-form-row vertical">
							<div id="comNameDisplay" class="form-control-plaintext"></div>
							<input type="hidden" id="comName" name="comName">
						</div>

						<div class="section-form-row">
							<div id="comCreateYearDisplay" class="form-control-plaintext"></div>
							<input type="hidden" id="comCreateYear" name="comCreateYear">
						</div>

						<div class="section-form-row">
							<div id="comPaymentDisplay" class="form-control-plaintext"></div>
							<input type="hidden" id="comPayment" name="comPayment">
						</div>

						<div class="section-form-row vertical">
							<label>기업설명</label>
							<textarea id="comInfo" name="comInfo" class="form-control"></textarea>
						</div>

						<div class="section-form-row">
							<label>대표자 이름</label>
							<input type="text" id="ceoName" name="ceoName" class="form-control">
						</div>

						<div class="section-form-row">
							<label class="required">대표번호</label>
							<input type="text" id="comNum" name="comNum" class="form-control">
						</div>

						<div class="section-form-row">
							<label class="required">이메일</label>
							<input type="email" id="comEmail" name="comEmail" class="form-control">
						</div>

						<div class="section-form-row">
							<label>홈페이지 URL</label>
							<input type="text" id="comUrl" name="comUrl" class="form-control">
						</div>

						<div class="section-form-row">
							<label class="required">직원수</label>
							<input type="number" id="comMem" name="comMem" class="form-control" min="1">
						</div>

						<div class="section-form-row">
							<label class="required">주소</label>
							<input type="text" id="comAddr" name="comAddr" class="form-control">
						</div>

						<div class="section-form-row">
							<label class="required">자본금</label>
							<input type="number" id="comCapital" name="comCapital" class="form-control">
						</div>

						<div class="section-form-row">
							<label class="required">주요 사업</label>
							<input type="text" id="comMainBiz" name="comMainBiz" class="form-control">
						</div>

						<div class="section-form-row">
							<label class="required">4대보험 가입 여부</label>
							<select id="insuranceYn" name="insuranceYn" class="form-control">
								<option value="">--선택--</option>
								<option value="Y">가입</option>
								<option value="N">미가입</option>
							</select>
						</div>

						<div class="section-form-row">
							<label class="required">업종</label>
							<select id="industryType" name="industryType" class="form-control">
								<option value="">--선택하세요--</option>
							</select>
						</div>

						<div class="section-form-row">
							<label class="required">기업 형태</label>
							<select id="comType" name="comType" class="form-control">
								<option value="">--선택--</option>
							</select>
						</div>

						<div class="section-form-row">
							<label class="required">기업 규모</label>
							<select id="comSize" name="comSize" class="form-control">
								<option value="">--선택--</option>
							</select>
						</div>



					</div>

					<!-- 기존 “기업 기본 정보” 섹션 아래에 삽입 -->
					<p class="subheading">이미지 업로드</p>
					<hr class="section-divider">

					<div class="section-form-wrap file-upload">
						<!-- 기업 로고 -->
						<div class="section-form-row">
							<label for="logoInput">기업 로고</label>
							<img id="logoPreview" alt="로고 미리보기">
							<input type="file" id="logoInput" accept="image/*" class="form-control">
							<input type="hidden" id="logoUrl" name="logoUrl">
						</div>

						<!-- 기업 배경 이미지 -->
						<div class="section-form-row file-upload">
							<label for="backInput">기업 배경 이미지</label>
							<img id="backPreview" alt="배경 미리보기">
							<input type="file" id="backInput" accept="image/*" class="form-control">
							<input type="hidden" id="backUrl" name="backUrl">
						</div>

						<!-- 부가 이미지들 -->
						<div class="section-form-row extra">
							<label for="extraImagesInput">부가 이미지들</label>
							<div id="extraImagePreviewContainer" style="display:flex; gap:8px; flex-wrap:wrap; margin-bottom:8px;">
							</div>
							<input type="file" id="extraImagesInput" accept="image/*" multiple class="form-control">
						</div>
					</div>

					<!-- 버튼들 -->
					<div class="section-form-btns">
						<button type="button" class="btn btn_red_line" id="cancelBtn">취소</button>
						<button type="submit" class="btn btn_violet" id="saveBtn">저장</button>
					</div>
				</div>
			</div>
		</form>

		<script type="module">
			import { DATA } from '/js/dataConf.js';
			document.getElementById('fillCorpInfo').addEventListener('click', function () {
				const data = { ...DATA.comInfo };
				console.log(data);
				for (const selector in data) {
					const el = document.querySelector(selector);
					if (el) {
						el.value = data[selector];
					}
				}
			});
		</script>
	</body>

	</html>