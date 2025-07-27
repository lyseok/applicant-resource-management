<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://js.tosspayments.com/v1"></script>
<script src="/js/company/payment/TossPayment.js"></script>
    <title>상품 목록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container my-5">

<div class="d-flex justify-content-between align-items-center mb-3">
    <h2 class="mb-0">등록된 상품 목록</h2>

    <!-- 🔍 필터 + 검색 버튼 -->
    <form class="d-flex" method="get" action="">
        <select class="form-select me-2" name="filterType">
    <option value="" ${empty param.filterType ? "selected" : ""}>전체</option>
    <option value="S" ${param.filterType == 'BUSINESS' ? "selected" : ""}>Business</option>
    <option value="L" ${param.filterType == 'PREMIUM' ? "selected" : ""}>Premium</option>
</select>
        <button type="submit" class="btn btn-primary">검색</button>
    </form>
    	<a href="/admin/add"><button>상품 등록하기</button></a>
</div>

<!-- ✅ 카드 목록 -->
<div class="row gy-4">
    <c:forEach var="product" items="${productList}">
        <div class="col-12">
            <div class="card flex-row shadow-sm" data-product-no="${product.productNo }"
                   onclick="AdminDetail(this)" >
                <img src="/upload/${product.productImg}" class="img-fluid rounded-start" style="width: 200px; height: 200px; object-fit: cover;" alt="상품 이미지">
                <div class="card-body">
                    <h5 class="card-title" id="orderName">${product.productName}</h5>
                    <p class="card-text">${product.productDetail}</p>
                    <p class="card-text mb-1" id="amount"><strong>가격:</strong> ₩<fmt:formatNumber value="${product.productPrice}" pattern="#,##0" /></p>
                    <p class="card-text mb-1">
                        <strong>유형:</strong>
                        <c:choose>
                            <c:when test="${product.productType == 'PREMIUM'}">PREMIUM</c:when>
                            <c:when test="${product.productType == 'BUSINESS'}">BUSINESS</c:when>
                            <c:otherwise>${product.productType}</c:otherwise>
                        </c:choose>
                    </p>
                    <p class="card-text"><small class="text-muted">이용기간: ${product.productPeriod}</small></p>
               		<button>수정</button>
               		<button>삭제</button>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<!-- ⏬ 페이징 -->
<c:if test="${totalPages > 1}">
    <nav class="mt-4">
        <ul class="pagination justify-content-center">
            <c:forEach begin="1" end="${totalPages}" var="i">
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link"
                       href="?page=${i}&filterType=${param.filterType}">
                        ${i}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </nav>
</c:if>
<script src="/js/admin/payment/AdminProduct.js"></script>
</body>
</html>
