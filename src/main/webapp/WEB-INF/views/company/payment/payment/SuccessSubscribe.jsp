<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 결과</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
    body {
        background-color: #f8f9fa;
        font-family: 'Noto Sans KR', sans-serif;
    }
    .payment-result-container {
        width: 100%;
        max-width: 900px;
        margin: 40px auto;
        border: 2px solid #7e57c2;
        border-radius: 12px;
        padding: 40px;
        background: #fff;
        box-shadow: 0 4px 12px rgba(126, 87, 194, 0.2);
        transition: box-shadow 0.3s ease, transform 0.3s ease;
    }
    .payment-result-container:hover {
        box-shadow: 0 6px 18px rgba(126, 87, 194, 0.35);
        transform: translateY(-3px);
    }
    h3.card-title {
        font-weight: bold;
        color: #6a1b9a;
        margin-bottom: 30px;
    }
    .table th {
        width: 30%;
        background-color: #f3e5f5;
        color: #4a148c;
    }
    .btn-purple {
        background-color: #7e57c6;
        border-color: #7e57c6;
    }
    .btn-purple:hover {
        background-color: #5e3fa3;
        border-color: #5e3fa3;
    }
</style>
</head>
<body>
    <div class="payment-result-container">
        <h3 class="card-title text-center">결제 완료</h3>
        <table class="table table-bordered">
            <tbody>
                <tr>
                    <th>결제수단</th>
                    <td>${result.paymentMethod}</td>
                </tr>
                <tr>
                    <th>상품명</th>
                    <td>
                        <c:forEach var="product" items="${result.paymentProductList}">
                            ${product.productName}<br />
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <th>구매금액</th>
                    <td>${result.paymentPay}원</td>
                </tr>
                <%-- <tr>
                    <th>결제일시</th>
                    <td>${result.startDate}</td>
                </tr> --%>
                 <tr>
                    <th>상품 이용기간</th>
                    <td>구매일로부터 30일</td>
                </tr>
              <%--   <tr>
                    <th>상품설명</th>
                    <td>${result.}</td>
                </tr>     --%>  
            </tbody>
        </table>
        <div class="text-center mt-4">
            <a href="/company/payment/main" class="btn btn-purple btn-lg">대시보드로 이동</a>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
