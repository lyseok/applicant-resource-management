/**
 *
 */

document.addEventListener('DOMContentLoaded', () => {
  const recruitExamNo = new URLSearchParams(window.location.search).get(
    'recruitExamNo'
  );
  const applicantId = new URLSearchParams(window.location.search).get(
    'applicantId'
  );

  axios
    .get(`/ajax/mypage/recruitment_exam/result/${recruitExamNo}/${applicantId}`)
    .then((resp) => {
      const result = resp.data;
      const score = result.examTotalScore;
      const scoreDiv = document.createElement('div');
      scoreDiv.className = "d-flex flex-wrap flex-column align-items-center";
      scoreDiv.innerHTML = `응시하신 시험의 점수는 <div><span class="score-value">${score}</span>점</div> 입니다.`;
      const resultContainer = document.getElementById('result-container');
      resultContainer.appendChild(scoreDiv);
    })
    .catch((err) => {
      console.error(err,'시험 결과를 가져오는 데 실패했습니다.');
      document.getElementById('result-score').textContent =
        '시험 결과를 불러오는 데 실패했습니다.';
    });
});
