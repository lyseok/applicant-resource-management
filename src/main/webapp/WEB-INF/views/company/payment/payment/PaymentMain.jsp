<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 결제상품</title>
</head>
<body>
	현재 살아있는 유료상품 내용 : (구현예정)
	<a href="/company/payment/product/list"><button>상품 보러가기</button></a>
	
	<div class="container my-5">
		<h2 class="mb-4">🧾 내가 구매한 상품</h2>
		<div class="row gy-4">
			<c:forEach var="item" items="${purchaseList}">
				<div class="col-md-6">
					<c:forEach var="product" items="${item.paymentProductList }">
						<div class="card shadow-sm flex-row">
							<div class="card-body">
								<h5 class="card-title">${item.productName}</h5>
								<p class="card-text mb-1">
									<strong>결제수단:</strong> ${item.paymentMethod}
								</p>
								<p class="card-text mb-1">
									<strong>결제금액:</strong> ₩
									<fmt:formatNumber value="${item.paymentPay}" pattern="#,##0" />
								</p>
								<p class="card-text">
									<small class="text-muted">결제일시: ${item.paymentDate}</small>
								</p>
							</div>
						</div>
						</c:forEach> 
				</div>
			</c:forEach>
		</div>
	</div>

</body>
</html>