<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<head>
  <meta charset="UTF-8">
  <title>결제 상품 조회</title>
  <style>
    .report-container {
    	display:flex;
    	gap:24px;
			width:100%;
    }
    .report-section {
    	flex-basis:calc((100% - 24px) / 2);
      margin-bottom: 20px;
      border: 1px solid var(--gray60);
      padding:30px;
      border-radius:10px;
    }
    .report-label {
      font-weight: bold;
      color: #6f42c1;
      min-width: 150px;
    }
    .report-value {
      flex: 1;
      color: #333;
    }
    .report-row {
      display: flex;
      border-bottom: 1px solid #eee;
    }
    .report-row:last-child {
      border-bottom: none;
    }
    .btn-purple {
      background-color: #b39ddb;
      color: white;
      border: none;
      font-weight: bold;
    }
    .btn-purple:hover {
      background-color: #9c89c7;
    }
    .no-product {
      text-align: center;
      padding: 50px 20px;
      color: #777;
      font-size: 1.1rem;
    }
  </style>
</head>
<body>
	<p class="h2 mb-5 fw-bold">결제 상품 조회</p>
	
	<div class="d-flex justify-content-between align-items-end w100p mb-2 pb-2 border-bottom">
		<p class="fs-14">
			총 <span id="list-count" class="fw-bold">${not empty ppvo ? 1 : ''} ${empty ppvo ? 0 : ''}</span>건
		</p>			
		<div class="d-flex gap-2">					
				<!-- 글쓰기 버튼 -->
     	<a href="/company/payment/product/list" class="btn btn_violet">상품 목록 보기</a>
		</div>
	</div>	
		
  <!-- 보고서 스타일 카드 -->
  <div class="report-container">
    <!-- 상품 없을 경우 -->
    <c:if test="${empty ppvo}">
      <div class="no-product w100p">활성화된 상품이 없습니다.</div>
    </c:if>

    <!-- 상품 있을 경우 -->
    <c:if test="${not empty ppvo}">
      <div class="report-section">
         <div class="report-value">
           <c:forEach var="prod" items="${ppvo.paymentProductList}">
		         <div class="bg-violet08 px-3 fs-14 fw-semibold lh1-6 text-white mb-2">
		           <c:choose>
		             <c:when test="${ppvo.daysRemaining >= 0}">${ppvo.daysRemaining}일 남음</c:when>
		             <c:otherwise>기간 만료</c:otherwise>
		           </c:choose>
		         </div>
		         
             <a href="/company/payment/buydetail?paymentNo=${ppvo.paymentNo}&productNo=${prod.productNo}" class="h2 fw-bold mb-3">
               ${prod.productName}
             </a>
             
             <div class="d-flex my-3 align-items-end gap-2">
	             <div class="fs-5 fw-semibold"><fmt:formatNumber value="${ppvo.paymentPay}" type="currency" currencySymbol=""/>원</div>
	             <div class="fs-16 fw-semibold text-muted">/ ${ppvo.paymentMethod}</div>
             </div>
             
             
              <div class="d-flex gap-2 fs-14 mt-1">
			          <b>결제 예정상품</b>
				      	<c:forEach var="prod" items="${ppvo.paymentProductList}">
				       		<div class="report-value">${prod.productName}</div>
				        </c:forEach>
				      </div>
             
             <div class="d-flex gap-3">
				        <div class="d-flex gap-2 fs-14">
				          <b>잔여메일발송횟수</b>
				          <div class="report-value">
				            <c:choose>
				              <c:when test="${ppvo.usageRemaining == -1}">무제한 이용 가능</c:when>
				              <c:otherwise>${ppvo.usageRemaining}회 남음</c:otherwise>
				            </c:choose>
				          </div>
				        </div>
				        
				        
				        <div class="d-flex gap-2 fs-14">
				          <b>메일발송초기화일</b>
				          <div class="report-value">${ppvo.endDate}</div>
				        </div>
			        
			        </div>
			             
          		<div class="fs-14 text-muted mt-3">${ppvo.paymentDate}</div>
           </c:forEach>
         </div>
      </div>
    </c:if>

</body>
