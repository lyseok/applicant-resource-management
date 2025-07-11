// 로컬스토리지 데이터 파싱
const detail = JSON.parse(localStorage.getItem('interviewDetail'));
console.log(detail);
hideLoading();
const applicantList = detail.recruitProcess.applicantRecordList;
const questionList = detail.interviewQuestionList;

let interviewScoreMap = []; // { applicantRecordNo: interviewScoreNo }

// 평가 기본키 미리 insert
async function initializeInterviewScoreList() {
  // 지원자별 초기값으로 Insert 리스트 구성
  const interviewScoreList = applicantList.map(applicant => ({
    applicantId: applicant.applicantId,
    interviewNo: detail.interviewNo
    // 필요시 추가 필드
  }));
  showLoading();
  try {
    const res = await axios.post('/ajax/interviewscore', {
        interviewScoreList: interviewScoreList
    });
    if (res.status === 200) {
      console.log(res.data);
      res.data.forEach(item => {
        interviewScoreMap[item.applicantId] = item.interviewScoreNo;
      });
      console.log('interviewScoreMap', interviewScoreMap);
    } else {
      alert('초기 평가 정보 생성에 실패했습니다.');
    }
  } catch (err) {
    alert('초기 평가정보 생성 실패');
  } finally {
    hideLoading();
  }
}
initializeInterviewScoreList();

// 지원자 드롭다운 렌더링
const applicantSelect = document.getElementById('applicantSelect');
applicantList.forEach(ar => {
  const opt = document.createElement('option');
  opt.value = ar.applicantRecordNo;
  opt.textContent = ar.applicantName;
  opt.dataset.resumeNo = ar.applicant.resumeNo; // 이력서 번호 저장
  applicantSelect.appendChild(opt);
});

// 이력서 링크 업데이트 함수
function updateResumeLink() {
  const selected = applicantSelect.selectedOptions[0];
  const resumeNo = selected.dataset.resumeNo;
  const resumeLink = document.getElementById('resumeLink');
  resumeLink.onclick = function(e) {
    e.preventDefault();
    window.open('/company/resume/view?resumeNo=' + resumeNo, '_blank', 'width=900,height=700');
  };
}
applicantSelect.addEventListener('change', updateResumeLink);
updateResumeLink(); // 최초 1회

// 평가폼 렌더 함수
function renderEvaluationForm() {
  const form = document.getElementById('evaluationForm');
  form.innerHTML = questionList.map((q, idx) => `
    <div class="mb-3">
      <div class="fw-semibold mb-2">${q.interviewQuestionContent}</div>
      <div class="d-flex flex-wrap eval-radio gap-2">
        ${[1,2,3,4,5].map(score => `
          <label class="btn btn-outline-secondary">
            <input type="radio" name="score_${q.interviewQuestionNo}" value="${score}" required> 
            ${score}: ${['매우 그렇지 않다', '그렇지 않다', '보통이다', '그렇다', '매우 그렇다'][score-1]}
          </label>
        `).join('')}
      </div>
    </div>
  `).join('');
}
renderEvaluationForm();

// 평가 폼 제출 이벤트
document.getElementById('submitEvaluationBtn').onclick = async function() {
  const form = document.getElementById('evaluationForm');
  if (!form.checkValidity()) {
    form.reportValidity();
    return;
  }
  const formData = new FormData(form);

  // 1. 선택된 지원자(applicantId)
  const selectedApplicantRecordNo = applicantSelect.value;
const selectedApplicant = applicantList.find(a => a.applicantRecordNo === selectedApplicantRecordNo);
const applicantId = selectedApplicant.applicantId;
const interviewScoreNo = interviewScoreMap[applicantId];
  console.log(interviewScoreNo);

  // 3. 평가 항목 리스트 생성
  const scores = questionList.map(q => ({
    interviewScoreNo: interviewScoreNo, // 이 지원자의 PK
    interviewQuestionNo: q.interviewQuestionNo,
    interviewQuestionScore: + formData.get(`score_${q.interviewQuestionNo}`)
  }));

  showLoading();
  axios.post('/ajax/interview/score', {
    interviewQuestionScoreList: scores
  })
  .then(res => {
    alert('평가가 저장되었습니다.');
  })
  .catch(err => {
    if (err.response && err.response.data && err.response.data.msg) {
      alert(err.response.data.msg);
    } else {
      alert('저장 중 오류가 발생했습니다.');
    }
  })
  .finally(() => {
    hideLoading();
  });
};

// 로딩 show/hide 함수
function showLoading() {
  document.getElementById('loadingSpinner').style.setProperty('display', 'flex', 'important');
}
function hideLoading() {
  document.getElementById('loadingSpinner').style.setProperty('display', 'none', 'important');
}