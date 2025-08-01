<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업 리뷰 등록</title>
<style>
	/* 회사 정보 */
	.company-info-box {
		position:relative;
		display: flex;
		justify-content: space-between;
		align-items: flex-end; /* 하단 정렬로 맞춤 */
		min-height: 70px; /* 로고와 차트 높이 맞춤 */
		padding-top:90px;
		/* padding-bottom: 50px; */
		margin-bottom: 20px;
		background:rgba(239 229 255/0.5);
	}
	
	.company-info-left {
		position:relative;
		display: flex;
	  flex-direction: column;
		gap: 20px;
	  width: 100%;
	  background: #fff;
	  padding: 30px 50px;
	  padding-top: 80px;
	  margin-top: 30px;
	  border-radius: 10px;
	  z-index:2;
	}
	
	
	.company-logo {
		position:absolute;
		top:0;
		left:50px;
		transform:translateY(-50%);
		width: 100px;
		height: 100px;
		object-fit: contain;
		border: 1px solid #ddd;
		border-radius: 10px;
		background: #fff;
	}
	
	.company-name {
		margin: 0 0 5px 0; /* 이름과 리뷰 수 간격 */
		font-size: 1.5rem;
		font-weight: bold;
	}
	
	
	.review-card {
		flex-basis:calc((100% - 20px) / 2)
	}
	
.review-card {
  border-radius: 5px;
}

.review-card .card-body {
  padding: 12px 16px; /* 카드 내부 패딩 줄이기 */
}

.review-card label {
  font-size: 0.95rem;
}

.review-card .form-check-label {
  font-size: 0.85rem;
}

.review-card .form-check-wrap {
  gap: 10px; /* 라디오 버튼 간격 */
}

.review-card textarea.form-control {
  font-size: 0.9rem;
}

</style>
<script src="/js/member/community/companyView/companyReviewForm.js"></script>
</head>

<body class="n_inner">
	  <h3 class="h1 mb-5 fw-bold mw-1260 m-auto">기업 리뷰 등록</h3>
	  <form id="reviewForm">
	  	<div id="careerInfo" class="company-info-box">
				<div class="company-info-left mw-1260 m-auto">
					<img id="companyLogo" class="company-logo" src="https://dditjob-image-bucket.s3.ap-northeast-2.amazonaws.com/2025-07-24/5e8aa231-a4c1-4651-b63b-9d03e530a2cf_naver.png" alt="기업 로고">
					<div>
						<h3 id="companyName" class="company-name">네이버</h3>
						<p class="company-info fs-14 text-muted d-flex gap-3">
							<span id="jobName">승용차 및 기타 여객용 자동차 제조업</span>
							<span><b id="com_size" ></b> </span>
							<span>직원수 <b id="com_mem"></b>명</span>
							<span>대표명 <b id="ceo_name"></b></span>
						</p>
						<div class="company-info fs-14 text-muted d-flex gap-3 mt-2">
							<p>
								<b>재직여부</b> <span id="period"></span>
							</p>
							<p>
								<b>재직 기간</b> <span id="workPeriod"></span>
							</p>
						</div>
					</div>
				</div>
			</div>
	
	
			<div class="mw-1260 m-auto">
		    <!-- 질문 카드들이 여기에 동적 삽입됨 -->
		    <div id="questionCards" class="d-flex flex-wrap gap-3 mb-3 mt-5"></div>
		    <!-- 한 줄 평 -->
		    <div class="card review-card mb-4 shadow-sm">
		      <div class="card-body">
		        <label class="d-block mb-2 fw-semibold">한 줄 평</label>
		        <textarea class="form-control" name="companyReviewOneLine" rows="3" placeholder="기업에 대한 한 줄 평을 작성해주세요."></textarea>
		      </div>
		    </div>
		
		    <!-- 등록 버튼 -->
		    <div class="text-end">
		    	<button type="submit" class="btn btn_violet">등록</button>
		    </div>
	    </div>
	  </form>
</body>