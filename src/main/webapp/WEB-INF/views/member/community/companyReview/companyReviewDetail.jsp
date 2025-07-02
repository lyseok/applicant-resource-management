<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>

<ul class="nav">
  <li class="nav-item">
    <a class="nav-link active" aria-current="page" href="#">전체</a>
  </li>
  <li class="nav-item">
    <a class="nav-link disabled" href="#">조직문화</a>
  </li>
  <li class="nav-item">
    <a class="nav-link disabled" href="#">복지</a>
  </li>
  <li class="nav-item">
    <a class="nav-link disabled" href="#">워라벨</a>
  </li>
  <li class="nav-item">
    <a class="nav-link disabled" href="#">연봉 </a>
  </li>
</ul>

 
  <canvas id="avgChart" width="400" height="300"></canvas>


  <script>
   
    const reviews = [
      <c:forEach items="${list}" var="review">
        <c:forEach items="${review.companyReviewQuestion}" var="q">
          {
            question: "${q.cmnCode.codeName}", 
            score: ${q.companyReviewScore}
          }<c:if test="${!q_last || !review_last}">,</c:if>
        </c:forEach>
      </c:forEach>
    ];
  </script>

   <script>
    const reviews = [
      <c:forEach items="${list}" var="review">
        <c:forEach items="${review.companyReviewQuestion}" var="q">
          {
            question: "${q.cmnCode.codeName}",
            score: ${q.companyReviewScore}
          }<c:if test="${!q_last || !review_last}">,</c:if>
        </c:forEach>
      </c:forEach>
    ];
  </script>

  <script>
    // 문항별 점수 집계
    const sums = {}, counts = {};
    reviews.forEach(({question, score}) => {
      sums[question] = (sums[question] || 0) + score;
      counts[question] = (counts[question] || 0) + 1;
    });

    const labels = Object.keys(sums);
    const data = labels.map(q => (sums[q] / counts[q]).toFixed(2));

    
    const palette = [
      'rgba(255, 99, 132, 0.6)',
      'rgba(54, 162, 235, 0.6)',
      'rgba(255, 206, 86, 0.6)',
      'rgba(75, 192, 192, 0.6)',
      'rgba(153, 102, 255, 0.6)',
      'rgba(255, 159, 64, 0.6)',
      'rgba(201, 203, 207, 0.6)',
      'rgba(255, 99, 71, 0.6)',
      'rgba(60, 179, 113, 0.6)',
      'rgba(106, 90, 205, 0.6)'
    ];

   
    const bgColors = labels.map((_, i) => palette[i % palette.length]);
    const bdColors = bgColors.map(c => c.replace('0.6', '1'));

    const ctx = document.getElementById('avgChart').getContext('2d');
    new Chart(ctx, {
      type: 'bar',
      data: {
        labels,
        datasets: [{
          label: '문항별 평균 점수',
          data,
          backgroundColor: bgColors,
          borderColor: bdColors,
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true,
            max: 5,
            title: {
              display: true,
              text: '평균 점수'
            }
          }
        },
        plugins: {
          legend: { display: false },
          tooltip: {
            callbacks: {
              label: ctx => `${ctx.parsed.y}점`
            }
          }
        }
      }
    });
  </script>

	
</body>
</html>