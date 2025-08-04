<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>면접 리뷰 등록</title>
		<link rel="stylesheet" href="/css/member/resume/resume.css">
		<script src="/js/member/common/mypage/interviewReview/interviewReviewWrite.js"></script>
		<style>
			.section-form-wrap {
				padding: 22px 30px;
			}

			.section-form-row>label {
				min-width: 137px;
			}

			.form-check-wrap label {
				min-width: auto;
			}

			.section-form-row input,
			.section-form-row select,
			.section-form-row textarea {
				width: 100%;
				/* 입력 필드가 전체 너비를 차지하도록 설정 */
				border: 1px solid #dbdbdb;
				border-radius: 7px;
				padding: 10px 15px;
				font-size: 15px;
				background: #fff;
				outline: none;
				transition: border 0.2s;
			}

			.suggestions {
				position: absolute;
				top: 100%;
				left: 0;
				right: 0;
				background: #fff;
				border-radius: 0 0 7px 7px;
				max-height: 200px;
				overflow-y: auto;
				margin: 0;
				padding: 0;
				list-style: none;
				z-index: 1000;
			}

			.suggestions li {
				padding: 8px 12px;
				cursor: pointer;
			}

			.suggestions li:hover {
				background: #f0f0f0;
			}

			.section-form-row.autocomplete {
				position: relative;
			}
		</style>
	</head>
	<p class="h1 mb-3 fw-bold" id="fillInterviewBtn">면접 후기 등록</p>
	<div class="interview-wrap">
		<form id="interviewReviewForm">
			<input type="hidden" id="comId" name="comId" />


			<!-- 기본 정보 (Basic Information) -->
			<div class="section" id="section-basic">
				<div class="section-title justify-content-start gap-3">
					<h6>기본 정보</h6>
					<div class="text-black-50 fw-500 fs-16">기본적인 정보를 입력해주세요.</div>
				</div>
				<div class="section-form-wrap">
					<div class="section-form-row">
						<label class="required" for="companyName">기업명</label>
						<input type="text" id="companyName" name="companyName" required>
					</div>
					<div class="section-form-row autocomplete">
						<label class="required" for="jobNameInput">직무 선택 </label>

						<div class="input-wrapper w100p">
							<input type="text" id="jobNameInput" name="jobName" autocomplete="off" placeholder="직무를 검색해주세요. ">
						</div>
						<input type="hidden" id="jobCodeHidden" name="jobCode">
						<ul id="jobSuggestions" class="suggestions"></ul>
					</div>

					<div class="section-form-row">
						<label class="required" for="interviewDate">면접일자</label>
						<input type="text" id="interviewDate" name="interviewDate" required>
					</div>
				</div>
			</div>

			<!-- 면접 정보 (Interview Information) -->
			<div class="section" id="section-interview">
				<div class="section-title justify-content-start gap-3">
					<h6>면접 정보</h6>
					<div class="text-black-50 fw-500 fs-16">면접에 대한 정보를 입력해주세요.</div>
				</div>
				<div class="section-form-wrap">
					<div class="section-form-row">
						<label class="required">전반적 평가 </label>
						<div class="input-wrapper">
							<div class="form-check-wrap d-flex gap-3">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="evaluation" value="1">
									<label class="form-check-label" for="evaluation">긍정적</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="evaluation" value="2">
									<label class="form-check-label" for="evaluation">보통</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="evaluation" value="3">
									<label class="form-check-label" for="evaluation">부정적</label>
								</div>
							</div>
						</div>
					</div>

					<div class="section-form-row">
						<label class="required">면접 난이도 </label>
						<div class="input-wrapper">
							<div class="form-check-wrap d-flex gap-3">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="interviewLevel" value="1">
									<label class="form-check-label">상</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="interviewLevel" value="2">
									<label class="form-check-label">중</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="interviewLevel" value="3">
									<label class="form-check-label">하</label>
								</div>
							</div>
						</div>
					</div>
					<div class="section-form-row">
						<label class="required">면접유형</label>
						<div class="d-flex flex-column">
							<div id="type-message" class="text-violet80 fs-14 my-2"></div>
							<div class="form-check-wrap d-flex gap-3">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="interviewType" value="Y">
									<label class="form-check-label">화상</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="interviewType" value="N">
									<label class="form-check-label">대면</label>
								</div>
							</div>
						</div>
					</div>
					<div class="section-form-row">
						<label class="required">전형 및 면접 진행방식 </label>
						<textarea id="interviewContent" name="interviewContent" cols="30" rows="5"
							placeholder="서류 합격 후 어떤 전형과 면접을 경험하셨나요? "></textarea>
					</div>
				</div>
			</div>

			<!-- 합격 정보 (Pass Information) -->
			<div class="section" id="section-pass">
				<div class="section-title justify-content-start gap-3">
					<h6>합격 정보</h6>
					<div class="text-black-50 fw-500 fs-16">면접 결과를 입력해주세요.</div>
				</div>
				<div class="section-form-wrap">
					<div class="section-form-row w100p">
						<label class="required">합격 여부</label>
						<div class="input-wrapper">
							<div class="form-check-wrap d-flex gap-3">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="interviewPassYn" value="Y">
									<label class="form-check-label">합격</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="interviewPassYn" value="N">
									<label class="form-check-label">불합격</label>
								</div>
							</div>
						</div>
					</div>
					<div class="section-form-row">
						<label class="required">면접질문</label>
						<textarea id="interviewQuestion" name="interviewQuestion" cols="30" rows="5"
							placeholder="ex) &#10;Q1. 자기소개 해주세요.&#10;Q2. 회사에 지원한 이유는 무엇인가요?"></textarea>
					</div>
					<div class="section-form-row">
						<label>TIP&특이사항</label>
						<textarea id="tip" name="tip" cols="30" rows="5" placeholder="분위기, 준비 tip을 공유해주세요. "></textarea>
					</div>
				</div>
			</div>

			<div class="section-form-btns">
				<button type="button" class="btn btn_red_line">취소</button>
				<button type="submit" class="btn btn_violet">등록</button>
			</div>

		</form>
	</div>
	<script type="module">
		import { interviewData } from '/js/dataConf.js';

		document.getElementById('fillInterviewBtn').addEventListener('click', function () {
			for (const selector in interviewData) {
				const value = interviewData[selector];
				const elements = document.querySelectorAll(selector);

				elements.forEach(el => {
					if (el.type === 'radio') {
						// 라디오 버튼일 경우 값 일치하는 것 체크
						if (el.value === value) el.checked = true;
					} else {
						el.value = value;
					}
				});
			}
		});
	</script>
	</body>

	</html>