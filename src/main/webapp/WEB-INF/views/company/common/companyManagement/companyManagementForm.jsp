<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업 정보 관리</title>
<script src="/js/company/common/companyManagement/companyManagementForm.js"></script>
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<link href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" rel="stylesheet">
<style type="text/css">
/* ... 기존 스타일 그대로 유지 ... */
#logoPreview, #backPreview { max-height: 100px; margin-bottom: 8px; display: none; }
.form-row { margin-bottom: 12px; }
label { display: block; margin-bottom: 4px; font-weight: bold; }
</style>
</head>
<body>
	<p class="h2">기업 정보 관리</p>
	<form id="companyForm">
		<div class="company-info-wrap">
			<div class="section" id="section-company-basic">
				<div class="section-title">
					<h6>기업 기본 정보</h6>
				</div>
				<div class="section-content" style="color: #b8bfc9;">기업에 대한 기본 정보를 입력해주세요.</div>
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

					<!-- 기업 로고 -->
					<div class="section-form-row">
						<label>기업 로고</label>
						<img id="logoPreview" alt="로고 미리보기">
						<input type="file" id="logoInput" accept="image/*" class="form-control">
						<input type="hidden" id="logoUrl" name="logoUrl">
					</div>

					<!-- 배경 이미지 -->
					<div class="section-form-row">
						<label>기업 배경 이미지</label>
						<img id="backPreview" alt="배경 미리보기">
						<input type="file" id="backInput" accept="image/*" class="form-control">
						<input type="hidden" id="backUrl" name="backUrl">
					</div>

					<!-- 부가 이미지들 -->
					<div class="form-row">
						<label>부가 이미지들</label>
						<div id="extraImagePreviewContainer"
						     style="margin-top:10px; display:flex; gap:8px; flex-wrap:wrap;"></div>
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
</body>
</html>
