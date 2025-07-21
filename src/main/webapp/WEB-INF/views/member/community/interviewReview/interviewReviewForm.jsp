<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>면접 리뷰 등록</title>
<link rel="stylesheet" href="/css/member/resume/resume.css">
<script
	src="/js/member/common/mypage/interviewReviewWrite/interviewReviewWrite.js"></script>
<style>
	/* 입력 필드 수직으로 나열하기 위한 스타일 */
	.section-form-row {
		width: 100%; /* 전체 너비 */
		display: block; /* 수직으로 쌓이도록 설정 */
		margin-bottom: 20px; /* 입력 필드 간 간격 */
		color: #000;
	}
	
	.section-form-row label {
		display: block; /* 레이블을 블록 요소로 설정하여 상단에 여백을 추가 */
		font-size: 15px;
		color: #395081;
		font-weight: bold;
		margin-bottom: 8px; /* 레이블과 입력 필드 간 간격 */
	}
	
	.section-form-row input, .section-form-row select, .section-form-row textarea
	{
		width: 100%; /* 입력 필드가 전체 너비를 차지하도록 설정 */
		border: 1px solid #dbdbdb;
		border-radius: 7px;
		padding: 10px 15px;
		font-size: 15px;
		background: #fff;
		outline: none;
		transition: border 0.2s;
	}
	
	.section-form-row textarea {
		min-height: 80px; /* 텍스트 영역 높이 설정 */
	}
	.suggestions {
	  position: absolute;
	  top: 100%; left: 0; right: 0;
	  background: #fff;
	  border: 1px solid #dbdbdb;
	  border-top: none;
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
<p class="h2">면접 후기 등록</p>
<div class="interview-wrap">
	<form id="interviewReviewForm" >
	<input type="hidden" id="comId" name="comId" />
		<!-- 기본 정보 (Basic Information) -->
		<div class="section" id="section-basic">
			<div class="section-title">
				<h6>기본 정보</h6>
			</div>
			<div class="section-content" style="color: #b8bfc9;">기본적인 정보를 입력해주세요.</div>
			<div class="section-form-wrap">
				<div class="section-form-row">
					<label class="required" for="companyName">기업명</label> 
					<input type="text" id="companyName" name="companyName" required>
				</div>
				<div class="section-form-row autocomplete">
					<label class="required" for="jobNameInput">직무 선택 </label> 
					<input type="text"  id="jobNameInput" name="jobName" autocomplete="off" placeholder="직무를 검색해주세요. " required>
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
			<div class="section-title">
				<h6>면접 정보</h6>
			</div>
			<div class="section-content" style="color: #b8bfc9;">면접에 대한 정보를 입력해주세요.</div>
			<div class="section-form-wrap">
				<div class="section-form-row">
					<label class="required">전반적 평가 </label>
					<div class="form-check-wrap d-flex gap-3">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="evaluation" value="1" required> 
							<label class="form-check-label" for="evaluation">부정적</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="evaluation" value="2"> 
							<label class="form-check-label" for="evaluation" >보통</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="evaluation" value="3"> 
							<label class="form-check-label" for="evaluation">긍정적</label>
						</div>
					</div>
				</div>

				<div class="section-form-row">
					<label class="required">면접 난이도 </label>
					<div class="form-check-wrap d-flex gap-3">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="interviewLevel" value="1" required> 
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
				<div class="section-form-row">
					<label class="required">면접유형</label>
					<div class="form-check-wrap d-flex">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="interviewType" value="Y"> 
							<label class="form-check-label">화상</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="interviewType" value="N"> 
							<label class="form-check-label">대면</label>
						</div>
					</div>
					
					<div id = "type-message"></div>
				</div>
				<div class="section-form-row">
					<label class="required">전형 및 면접 진행방식 </label>
					<textarea id="interviewContent" name="interviewContent" cols="30" rows="5"  placeholder="서류 합격 후 어떤 전형과 면접을 경험하셨나요? " required></textarea>
				</div>
			</div>
		</div>

		<!-- 합격 정보 (Pass Information) -->
		<div class="section" id="section-pass">
			<div class="section-title">
				<h6>합격 정보</h6>
			</div>
			<div class="section-content" style="color: #b8bfc9;">면접 결과를 입력해주세요.</div>
			<div class="section-form-wrap">
				<div class="section-form-row">
					<label class="required">합격 여부</label>
					<div class="form-check-wrap d-flex">
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
				<div class="section-form-row">
					<label class="required">면접질문</label> 
					<input type="text" name="interviewQuestionContent" placeholder="Q.1 ex) 경력 사항에 대한 상세한 질문 " required> 
					<input type="text" name="interviewQuestionContent" placeholder="Q.2 ex) 우리 회사의 강점은 ?  " required>
					<input type="text" name="interviewQuestionContent" placeholder="Q.3 ex) 우리 회사를 선택한 이유  " required>
				</div>
				<div class="section-form-row">
					<label class="required">TIP&특이사항</label>
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
</body>
</html>