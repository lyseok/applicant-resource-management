/**
 * 
 */

document.addEventListener("DOMContentLoaded", () => {
  const container = document.getElementById('charts');
  const comId = container.dataset.comId;
  console.log(comId);

  Promise.all([
    axios.get("/ajax/member/companyReview/reviewQuestion"),
    axios.get("/ajax/member/companyReview/reviewDetail/" + comId)
  ])
  .then(([qResp, rResp]) => {
    const questions = qResp.data;   // CmnCodeVO 리스트
    const reviews   = rResp.data;   // CompanyReviewVO 리스트

    // 2) code -> 질문 텍스트 맵 생성
    const questionMap = {};
    questions.forEach(q => {
      // CmnCodeVO 안에 코드값, 코드명 필드명에 맞춰서 수정
      questionMap[q.codeDetailNo] = q.codeName;
    });

    // 3) 각 코드별 평균 점수 계산
    const stats = reviews
      .flatMap(r => r.companyReviewQuestion)
      .reduce((acc, q) => {
    	  // rawCode: "corp03:401 REVU-002" 또는 "REVU-001"
    	    const rawCode = q.reviewSubjectCode;
    	    // key: "REVU-002"처럼 마지막 토큰만
    	    const codeKey = rawCode.includes(' ') 
    	      ? rawCode.split(' ').pop() 
    	      : rawCode;

    	    // 안전하게 숫자로
    	    const score = Number(q.companyReviewScore) || 0;

    	    if (!acc[codeKey]) acc[codeKey] = { sum: 0, count: 0 };
    	    acc[codeKey].sum   += score;
    	    acc[codeKey].count += 1;
    	    return acc;
    	  }, {});

    const maxScore = 5;
    Object.entries(stats).sort().forEach(([codeKey, { sum, count }]) => {
    	  const avg     = count > 0 ? sum / count : 0;
    	  const avgText = avg.toFixed(2);
    	  console.log("age", avg, "avgText:", avgText);
    	  // 여기도 codeKey로 매핑
    	  const titleText = questionMap[codeKey] || codeKey;
    	  // 카드 아이템 생성
    	  console.log(titleText)
    	
    	  const card = document.createElement('div');
    	  card.className = 'card-item';

    	  // 캔버스
    	  const canvas = document.createElement('canvas');
    	  card.appendChild(canvas);

    	  // 질문 제목
    	  const title = document.createElement('div');
    	  title.className = 'chart-title';
    	  title.textContent = titleText;
    	  card.appendChild(title);

    	  // 평균 점수 텍스트
    	  const value = document.createElement('div');
    	  value.className = 'chart-value';
    	  value.textContent = avgText + ' / ' + maxScore;
    	  card.appendChild(value);

    	  document.getElementById('charts').appendChild(card);

    	  new Chart(canvas.getContext('2d'), {
    	    type: 'doughnut',
    	    data: {
    	      labels: ['평균', '나머지'],
    	      datasets: [{
    	        data: [avg, 5 - avg],
    	        backgroundColor: ['rgba(54,162,235,0.5)', 'rgba(200,200,200,0.3)'],
    	        borderWidth: 1
    	      }]
    	    },
    	    options: {
    	      plugins: { legend: { display: false } },
    	      responsive: true,
    	      maintainAspectRatio: false,
    	      cutout: '70%'
    	    }
    	  });
    	});
  })
  .catch(err => console.error("그래프 로드 실패", err));
});