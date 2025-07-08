<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <style>
 body {
      background: #f5f7fa;
      color: #333;
    }
    .inner {
      max-width: 1200px;
      margin: 2rem auto;
      padding: 0 1rem;
    }
    .header-title {
      font-size: 2rem;
      font-weight: 600;
      margin-bottom: 0.5rem;
    }
    .header-subtitle {
      color: #666;
    }
	.card-container {
	  display: flex;           
	  flex-wrap: nowrap;      
	  justify-content: flex-start; 
	  align-items: flex-start;   
	  gap: 2rem;                 
	  margin: 2rem auto;         
	  overflow-x: auto;          
	  padding-bottom: 1rem;      
	}

	.card-item {
	  flex: 0 0 auto;
	  width: 180px;
	  display: flex;
	  flex-direction: column;
	  align-items: center;
	}
    .card-item:hover {
      transform: translateY(-4px);
      box-shadow: 0 8px 20px rgba(0,0,0,0.1);
    }
    .card-item canvas {
      width: 120px !important;
      height: 120px !important;
    }
    .chart-title {
      margin-top: 0.75rem;
      font-size: 1rem;
      font-weight: 500;
      text-align: center;
      color: #444;
    }
    .chart-value {
      margin-top: 0.5rem;
      font-size: 1.25rem;
      font-weight: 700;
      color: #2c7be5;
    }
    
    .charts {
	  display: flex;             
	  flex-wrap: nowrap;         
	  justify-content: flex-start;
	  align-items: flex-start;
	  gap: 2rem;
	  margin: 2rem auto;
	  overflow-x: auto;
	  padding-bottom: 1rem;
	}
  </style>

 <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
 <script defer src="/js/member/community/companyView/companyReviewChart.js"></script>
</head>
<body>


<div class = "inner">
  <div>
	<div>
	  <div >
	    <p class="h1">${company.comName}의 기업 리뷰입니다.</p>
	    <h6 class="card-subtitle mb-2 text-muted">${company.comInfo }</h6>
	    <h6 class="card-subtitle mb-2 text-muted">직원 수 ${company.comMem }명 </h6>
	    <h6 class="card-subtitle mb-2 text-muted">기업 설립년도 ${company.comCreateYear }</h6>
	    
	  </div>
	</div>
</div>
</div>
	<h1>${company.comName }의 리뷰 통계 </h1>
	
	  <div id="charts" class="charts" data-com-id="${company.userId}"></div>
</div>

</body>
</html>