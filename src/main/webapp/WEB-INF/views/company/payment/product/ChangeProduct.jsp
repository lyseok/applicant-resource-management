<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>요금제 변경</title>
<script src="https://js.tosspayments.com/v2/standard"></script>
<script>
	const PbillingKey = "${param.billingKey}";
	const PproductName = "${product.productName}";
	const PoldPaymentNo ="${oldPaymentNo}";
	const Pamount = "${product.productPrice}";
	const PproductNo = "${product.productNo}";
</script>
</head>
<body class="container my-5">

	<div class="card shadow">
		<div class="row g-0">
			<div class="col-md-4">
				<img src="/upload/${product.productImg}"
					class="img-fluid rounded-start" alt="상품 이미지" />
			</div>
			<div class="col-md-8">
				<div class="card-body">
					<h3 class="card-title">${product.productName}</h3>
					<p class="card-text">${product.productDetail}</p>
					<p>oldPaymentNo: ${oldPaymentNo}</p>
					<p>
						<strong>가격:</strong> ₩
						<fmt:formatNumber value="${product.productPrice}" pattern="#,##0" />
					</p>
					<p>
						<strong>유형:</strong>
						<c:choose>
							<c:when test="${product.productType == 'L'}">정기권</c:when>
							<c:when test="${product.productType == 'S'}">단건</c:when>
							<c:otherwise>${product.productType}</c:otherwise>
						</c:choose>
					</p>
					<p>
						<strong>이용기간:</strong> ${product.productPeriod}
					</p>

					<button class="btn btn-success" id="change-btn"
						onclick="doChangeBilling()" >요금제 변경 결제</button>
					<button onclick="history.back()">뒤로가기</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 카드 등록 필요 시 모달 -->
	<div class="modal fade" id="noCardModal" tabindex="-1"
		aria-labelledby="noCardModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="noCardModalLabel">카드 등록 필요</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="닫기"></button>
				</div>
				<div class="modal-body">등록된 결제 카드가 없습니다. 먼저 카드를 등록해주세요.</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">취소</button>
					<button onclick="requestBillingAuth()">카드 등록하기</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 외부 JS 연결 -->
	<script src="/js/company/payment/ChangeProduct.js"></script>
</body>
</html>
