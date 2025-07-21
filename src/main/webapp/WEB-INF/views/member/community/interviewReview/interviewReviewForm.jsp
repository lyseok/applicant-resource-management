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
</style>
</head>
<p class="h2">면접 후기 등록</p>
<div class="interview-wrap">

	<!-- 기본 정보 (Basic Information) -->
	<div class="section" id="section-basic">
		<div class="section-title">
			<h6>기본 정보</h6>
		</div>
		<div class="section-content" style="color: #b8bfc9;">기본적인 정보를
			입력해주세요.</div>
		<div class="section-form-wrap">
			<div class="section-form-row">
				<label class="required">기업명</label> 
				<input type="text" id ="companyName" name="companyName" required>
			</div>
			<div class="section-form-row">
				<label class="required">직무</label> 
				<input type="text" name="jobCode" required>
			</div>
			<div class="section-form-row">
				<label class="required">면접 당시 경력</label> 
				<div class="form-check-wrap d-flex">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="interview_type"
							value="video" checked> <label class="form-check-label">신입</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="interview_type"
							value="in_person"> <label class="form-check-label">경력</label>
					</div>
				</div>
			</div>
			<div class="section-form-row">
				<label class="required">면접일자</label> <input type="text"
					name="graduation_date" required>
			</div>
		</div>
	</div>

	<!-- 면접 정보 (Interview Information) -->
	<div class="section" id="section-interview">
		<div class="section-title">
			<h6>면접 정보</h6>
		</div>
		<div class="section-content" style="color: #b8bfc9;">면접에 대한 정보를
			입력해주세요.</div>
		<div class="section-form-wrap">
			<div class="section-form-row">
				<label class="required">전반적 평가 </label>
				<div class="form-check-wrap d-flex gap-3">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="overall_rating"
							value="NEGATIVE" required> <label
							class="form-check-label">부정적</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="overall_rating"
							value="NEUTRAL"> <label class="form-check-label">보통</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="overall_rating"
							value="POSITIVE"> <label class="form-check-label">긍정적</label>
					</div>
				</div>
			</div>


			<div class="section-form-row">
				<label class="required">면접 난이도 </label>
				<div class="form-check-wrap d-flex gap-3">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="difficulty"
							value="HARD" required> <label class="form-check-label">상</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="difficulty"
							value="MEDIUM"> <label class="form-check-label">중</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="difficulty"
							value="EASY"> <label class="form-check-label">하</label>
					</div>
				</div>
			</div>
			<div class="section-form-row">
				<label class="required">면접유형</label>
				<div class="form-check-wrap d-flex">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="interview_type"
							value="video" checked> <label class="form-check-label">화상</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="interview_type"
							value="in_person"> <label class="form-check-label">대면</label>
					</div>
				</div>
			</div>
			<div class="section-form-row">
				<label class="required">전형 및 면접 진행방식 </label>
				<textarea name="interview_content" cols="30" rows="5" required></textarea>
			</div>
		</div>
	</div>

	<!-- 합격 정보 (Pass Information) -->
	<div class="section" id="section-pass">
		<div class="section-title">
			<h6>합격 정보</h6>
		</div>
		<div class="section-content" style="color: #b8bfc9;">면접 결과를
			입력해주세요.</div>
		<div class="section-form-wrap">
			<div class="section-form-row">
				<label class="required">합격 여부</label>
				<div class="form-check-wrap d-flex">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="pass_status"
							value="Y" checked> <label class="form-check-label">합격</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="pass_status"
							value="N" checked> <label class="form-check-label">불합격</label>
					</div>
				</div>
			</div>
			<div class="section-form-row">
				<label class="required">면접질문</label> <input type="text"
					name="pass_score" placeholder="Q.1" required> <input
					type="text" name="pass_score" placeholder="Q.2" required> <input
					type="text" name="pass_score" placeholder="Q.3" required>
			</div>
			<div class="section-form-row">
				<label class="required">TIP&특이사항</label>
				<textarea name="interview_content" cols="30" rows="5" required></textarea>
			</div>
		</div>
	</div>

	<div class="section-form-btns">
		<button type="button" class="btn btn_red_line"
			onclick="toggleForm('military',true)">취소</button>
		<button type="submit" class="btn btn_violet">등록</button>
	</div>
</div>
</body>
</html>