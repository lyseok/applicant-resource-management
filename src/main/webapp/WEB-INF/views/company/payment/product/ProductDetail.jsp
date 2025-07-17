<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${product.productName} 상세보기</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
	const cmProductNo = "${product.productNo}";
	const cmBillingKey = "${param.billingKey}";
</script>
</head>
<body class="container my-5"
      data-billing-key="${param.billingKey}"
      data-product-no="${product.productNo}">
    <div class="card shadow">
        <div class="row g-0">
            <div class="col-md-4">
                <img src="/upload/${product.productImg}" class="img-fluid rounded-start" alt="상품 이미지" />
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h3 class="card-title">${product.productName}</h3>
                    <p class="card-text">${product.productDetail}</p>
                    <p><strong>가격:</strong> ₩<fmt:formatNumber value="${product.productPrice}" pattern="#,##0" /></p>
                    <p><strong>유형:</strong> 
                        <c:choose>
                            <c:when test="${product.productType == 'L'}">정기권</c:when>
                            <c:when test="${product.productType == 'S'}">단건</c:when>
                            <c:otherwise>${product.productType}</c:otherwise>
                        </c:choose>
                    </p>
                    <p><strong>이용기간:</strong> ${product.productPeriod}</p>
					<p>productNo: ${product.productNo}</p>
					
                    <button class="btn btn-primary" onclick="subscribe()">구독하기</button>
                </div>
                	<button onclick="history.back()">뒤로가기</button>
            </div>
        </div>
    </div>
    <!-- 카드 등록 필요 모달 -->
<div class="modal fade" id="noCardModal" tabindex="-1" aria-labelledby="noCardModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="noCardModalLabel">카드 등록 필요</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
      </div>
      <div class="modal-body">
        등록된 결제 카드가 없습니다.<br>
        먼저 카드를 등록해주세요.
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <a href="/company/toss/billing?productNo=${product.productNo}" class="btn btn-primary">카드 등록하러 가기</a>
      </div>
    </div>
  </div>
</div>
  <script src="/js/company/payment/ProductDetail.js"></script>     
</body>
</html>