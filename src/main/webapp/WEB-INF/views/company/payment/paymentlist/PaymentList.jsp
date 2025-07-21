<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제내역 List</title>
</head>
<body>
	이 페이지는 기간이 지난 상품들만 나타내기위함
	<div class="container my-5">
		<h2 class="mb-4">🧾 내가 구매한 상품</h2>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>상품명</th>
					<th>결제금액</th>
					<th>결제일시</th>
					<th>남은기간</th>
					<th>결제수단</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${purchaseList}">
					<tr>
						<td><c:forEach var="product"
								items="${item.paymentProductList}">
                        ${product.productName}<br />
							</c:forEach></td>
						<td>${item.paymentPay}원</td>
						<td>${item.paymentDate}</td>
						<td>
   					 <c:forEach var="product" items="${item.paymentProductList}">
     					<br />
   	   				  <script>
        		    const paymentDate = new Date("${item.paymentDate.replace(' ', 'T')}");
         			const now = new Date();
        		    const timeDiff = Math.floor((now - paymentDate) / (1000 * 60 * 60 * 24));
       		  		const daysLeft = 30 - timeDiff;
       			     document.write("${product.productName} 의 남은기간은 " + daysLeft + "일 입니다<br/>");
       			 	</script>
  				  </c:forEach>
				</td>
						<td>${item.paymentMethod}</td>
					</tr>
				</c:forEach>
			</tbody>
	</div>
</body>
</html>