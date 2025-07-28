<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.review-container {
  max-width: 600px; /* 폼 전체 너비 제한 */
  margin: 2rem auto;
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

#careerInfo{
 background-color: var(--violet40);
}
</style>
<script src="/js/member/community/companyView/companyReviewForm.js"></script>
</head>

<div class="container review-container my-4">
  <h3 class="mb-4 fw-bold">기업 리뷰 등록</h3>
  <form id="reviewForm">
    <div id="careerInfo" class="mb-4 p-3 border rounded bg-light">
        <p><strong>회사명:</strong> <span id="companyName"></span></p>
        <p><strong>직무:</strong> <span id="jobName"></span></p>
        <p><strong>재직여부:</strong> <span id="period"></span></p>
        <p><strong>재직 기간:</strong> <span id="workPeriod"></span></p>
    </div>


    <!-- 질문 카드들이 여기에 동적 삽입됨 -->
    <div id="questionCards"></div>
    <!-- 한 줄 평 -->
    <div class="card review-card mb-4 shadow-sm">
      <div class="card-body">
        <label class="d-block mb-2 fw-semibold">한 줄 평</label>
        <textarea class="form-control" name="companyReviewOneLine" rows="3" placeholder="기업에 대한 한 줄 평을 작성해주세요."></textarea>
      </div>
    </div>

    <!-- 등록 버튼 -->
    <button type="submit" class="btn btn_violet">리뷰 등록</button>
  </form>
</div>
</body>
</html>