<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<script>
	const ProductNo = "${product.productNo}";
	const BillingKey = "${sessionScope.billingKey}";
	const Amount = "${product.productPrice}";
	const customerKey = "${sessionScope.customerKey}";
	const orderName = "${product.productName}";
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="container my-5" data-billing-key="${billingKey}"
	data-product-no="${product.productNo}"
	data-old-payment-no="${oldPaymentNo}"
	data-trigger-billing="${triggerBilling}">
	<div style="display: flex; gap: 40px;">

		<!-- 왼쪽: 현재 상품 정보 -->
		<div style="flex: 1; border: 1px solid #ccc; padding: 20px;">
			<h3>현재 이용 중인 상품</h3>
			<%-- <p>payment 객체 확인: ${payment}</p>
			<p>product 리스트 크기: ${fn:length(payment.paymentProductList)}</p> --%>
			<c:forEach var="product" items="${payment.paymentProductList}">
				<p>상품명: ${product.productName}</p>
				<p>상품 내용: ${product.productDetail}</p>
				<p>금액: ${product.productPrice} 원</p>
				<p>유효기간: ${payment.startDate} ~ ${payment.endDate}</p>
			</c:forEach>
		</div>

		<!-- 오른쪽: 변경 가능한 상품 목록 -->
		<div style="flex: 1; border: 1px solid #ccc; padding: 20px;">
			<h3>변경 가능한 상품</h3>
			<c:forEach var="product" items="${changeableProducts}">
				<div style="margin-bottom: 20px; border-bottom: 1px solid #eee;">
					${product.productNo }
					<p>상품명: ${product.productName}</p>
					<p>내용: ${product.productDetail}</p>
					<p>가격: ${product.productPrice} 원</p>
					<form id="changeForm">
						<input type="hidden" name="oldPaymentNo"
							value="${payment.paymentNo}" /> <input type="hidden"
							name="newProductNo" value="${product.productNo}" /> <input
							type="hidden" name="billingKey"
							value="${payment.paymentBillingKey}" />
						<button id="change-btn"
								class="btn btn-success"
								data-product-no="${product.productNo}"
								data-product-name="${product.productName}"
								data-amount="${product.productPrice}"
							onclick="doChangeBilling(this)">이 요금제로 변경하기
							</button>

					</form>

				</div>
			</c:forEach>
		</div>

	</div>
	<script src="/js/company/payment/ProductDetail.js"></script>
	<script src="/js/company/payment/ComparsionProduct.js"></script>
</body>
</html>