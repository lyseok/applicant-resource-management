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
  const interviewScoreList = applicantList.map((applicant) => ({
    applicantId: applicant.applicantId,
    interviewNo: detail.interviewNo,
    // 필요시 추가 필드
  }));
  showLoading();
  try {
    const res = await axios.post('/ajax/interviewscore', {
      interviewScoreList: interviewScoreList,
    });
    if (res.status === 200) {
      console.log(res.data);
      res.data.forEach((item) => {
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
applicantList.forEach((ar) => {
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
  resumeLink.onclick = function (e) {
    e.preventDefault();
    window.open(
      '/company/resume/view?resumeNo=' + resumeNo,
      '_blank',
      'width=900,height=700'
    );
  };
}
applicantSelect.addEventListener('change', updateResumeLink);
updateResumeLink(); // 최초 1회

// 평가폼 렌더 함수
// function renderEvaluationForm() {
//   const form = document.getElementById('evaluationForm');
//   form.innerHTML = questionList.map((q, idx) => `
//     <div class="mb-3">
//       <div class="fw-semibold mb-2">${q.interviewQuestionContent}</div>
//       <div class="d-flex flex-wrap eval-radio gap-2">
//         ${[1,2,3,4,5].map(score => `
//           <label class="btn btn-outline-secondary">
//             <input type="radio" name="score_${q.interviewQuestionNo}" value="${score}" required>
//             ${['매우 그렇지 않다', '그렇지 않다', '보통이다', '그렇다', '매우 그렇다'][score-1]}
//           </label>
//         `).join('')}
//       </div>
//     </div>
//   `).join('');
// }
// renderEvaluationForm();

function renderEvaluationForm() {
  const form = document.getElementById('evaluationForm');
  const scoreLabels = [
    '매우 그렇지 않다',
    '그렇지 않다',
    '보통이다',
    '그렇다',
    '매우 그렇다',
  ];

  let html = `
    <div class="table-responsive">
      <table class="table table-bordered align-middle text-center table-hover" style="border-radius:10px; overflow:hidden;">
        <thead class="table-light">
          <tr>
            <th style="width:220px;" ></th>
            ${scoreLabels
              .map((label) => `<th class="align-middle">${label}</th>`)
              .join('')}
          </tr>
        </thead>
        <tbody>
          ${questionList
            .map(
              (q, idx) => `
            <tr>
              <td class="text-start fw-semibold bg-light" 
                  style="vertical-align:middle; max-width:220px; white-space:normal; font-size:13px; overflow:hidden; text-overflow:ellipsis;" 
                  title="${q.interviewQuestionContent}">
                ${idx + 1}. ${q.interviewQuestionContent}
              </td>
              ${[1, 2, 3, 4, 5]
                .map(
                  (score) => `
                <td style="vertical-align:middle;">
                  <label class="d-block mb-0">
                    <input type="radio"
                      name="score_${q.interviewQuestionNo}"
                      value="${score}"
                      required
                      style="accent-color:#8d4dff; transform:scale(1.15); vertical-align:-2px;" />
                  </label>
                </td>
              `
                )
                .join('')}
            </tr>
          `
            )
            .join('')}
        </tbody>
      </table>
    </div>
  `;
  form.innerHTML = html;
}
renderEvaluationForm();

// 평가 폼 제출 이벤트
document.getElementById('submitEvaluationBtn').onclick = async function () {
  const form = document.getElementById('evaluationForm');
  if (!form.checkValidity()) {
    form.reportValidity();
    return;
  }
  const formData = new FormData(form);

  // 1. 선택된 지원자(applicantId)
  const selectedApplicantRecordNo = applicantSelect.value;
  const selectedApplicant = applicantList.find(
    (a) => a.applicantRecordNo === selectedApplicantRecordNo
  );
  const applicantId = selectedApplicant.applicantId;
  const interviewScoreNo = interviewScoreMap[applicantId];
  console.log(interviewScoreNo);

  // 3. 평가 항목 리스트 생성
  const scores = questionList.map((q) => ({
    interviewScoreNo: interviewScoreNo, // 이 지원자의 PK
    interviewQuestionNo: q.interviewQuestionNo,
    interviewQuestionScore: +formData.get(`score_${q.interviewQuestionNo}`),
  }));

  showLoading();
  axios
    .post('/ajax/interview/score', {
      processNo: detail.processNo,
      applicantId: applicantId,
      interviewQuestionScoreList: scores,
    })
    .then((res) => {
      alert('평가가 저장되었습니다.');
      // 1. 선택된 option에 (평가완료) 붙이기 (이미 붙어있지 않으면)
      const selectedOption =
        applicantSelect.options[applicantSelect.selectedIndex];
      if (!selectedOption.text.includes('(평가완료)')) {
        selectedOption.text = selectedOption.text + ' (평가완료)';
        selectedOption.disabled = true; // 선택불가 처리
      }
      // 2. 다음 평가자 자동 선택
      for (let i = 0; i < applicantSelect.options.length; i++) {
        if (!applicantSelect.options[i].disabled) {
          applicantSelect.selectedIndex = i;
          updateResumeLink();
          break;
        }
      }
      // 모든 지원자 평가 완료시 안내
      const hasLeft = Array.from(applicantSelect.options).some(
        (opt) => !opt.disabled
      );
      if (!hasLeft) {
        alert('모든 지원자 평가가 완료되었습니다!');
      }
      // 폼 라디오 해제
      document
        .querySelectorAll('#evaluationForm input[type="radio"]')
        .forEach((input) => {
          input.checked = false;
        });
    })
    .catch((err) => {
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

// 지원자 select 변경시 라디오 체크 해제
applicantSelect.addEventListener('change', function () {
  // 기존 resume 링크 갱신
  updateResumeLink();
  // 평가폼 내 모든 라디오 체크 해제
  document
    .querySelectorAll('#evaluationForm input[type="radio"]')
    .forEach((input) => {
      input.checked = false;
    });
});

// 로딩 show/hide 함수
function showLoading() {
  document
    .getElementById('loadingSpinner')
    .style.setProperty('display', 'flex', 'important');
}
function hideLoading() {
  document
    .getElementById('loadingSpinner')
    .style.setProperty('display', 'none', 'important');
}
