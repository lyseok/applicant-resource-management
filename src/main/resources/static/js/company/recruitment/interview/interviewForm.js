const data = localStorage.getItem('interviewDetail');
const interview = JSON.parse(data);
console.log(interview);
const interviewQuestionList = interview.interviewQuestionList;
const videoInterview = interview.videoInterview;
const recruitProcess = interview.recruitProcess;
const applicantRecordList = recruitProcess.applicantRecordList;
const recruitmentNotice = recruitProcess.recruitmentNotice;
const interviewScoreList = interview.interviewScoreList;

// 데이터 관리 객체
let interviewData = {
  interviewNo: interview.interviewNo,
  recruitmentTitle: recruitmentNotice.recruitmentTitle,
  jobCode: recruitmentNotice.jobCode,
  jobCodeName: recruitmentNotice.jobCodeName,
  hireCount: recruitmentNotice.recPositionNumber,
  interviewDate: interview.interviewDate,
  interviewType: interview.interviewType,
  interviewLocation: interview.interviewLocation,
  processNo: recruitProcess.recruitProcessNo
}


// 데이터 뿌리기
const notice = interview?.recruitProcess?.recruitmentNotice || {};
document.getElementById('recruitmentTitle').value = interviewData.recruitmentTitle || '';
document.getElementById('jobName').value = interviewData.jobCodeName || '';
document.getElementById('hireCount').value = interviewData.hireCount || '';
document.getElementById('interviewDate').value = (interviewData.interviewDate || '').replace(' ', 'T');
document.getElementById('interviewType').value = interviewData.interviewType || 'N';
document.getElementById('interviewLocation').value = interviewData.interviewLocation || '';



// --- 면접 평가 항목 관리 ---
// 평가항목(문항) 데이터, [{ interviewNo, interviewQuestionNo, interviewQuestionContent }]
interviewData.interviewQuestionList = interviewQuestionList.map(q => ({
  interviewNo: q.interviewNo,
  interviewQuestionNo: q.interviewQuestionNo,
  interviewQuestionContent: q.interviewQuestionContent
}));

function renderScoreItems() {
  const box = document.getElementById('scoreItemsBox');
  box.innerHTML = '';
  interviewData.interviewQuestionList.forEach((item, i) => {
    const row = document.createElement('div');
    row.className = 'w-calc100-24-2 d-flex gap-2';

    const input = document.createElement('input');
    input.type = 'text';
    input.className = 'form-control';
    input.value = item.interviewQuestionContent;
    input.name = `scoreItem${i+1}`;
    input.id = `scoreItem${i+1}`;
    // --- data-* 속성 추가 ---
    input.setAttribute('data-interview-no', item.interviewNo);
    input.setAttribute('data-question-no', item.interviewQuestionNo);

    input.oninput = (e) => {
      interviewData.interviewQuestionList[i].interviewQuestionContent = e.target.value;
    };

    const delBtn = document.createElement('button');
    delBtn.type = 'button';
    delBtn.className = 'btn btn_red_line fw-semibold text-nowrap';
    delBtn.innerHTML = '<span class="material-symbols-outlined text-danger">close</span>';
    delBtn.onclick = () => {
      interviewData.interviewQuestionList.splice(i, 1);
      renderScoreItems();
    };

    row.appendChild(input);
    row.appendChild(delBtn);
    box.appendChild(row);
  });
}
renderScoreItems();

document.getElementById('addScoreItemBtn').onclick = function() {
  interviewData.interviewQuestionList.push({
    interviewNo: interview.interviewNo,      // 면접번호 그대로 입력
    interviewQuestionNo: '',                 // 신규면 빈값
    interviewQuestionContent: ''
  });
  renderScoreItems();
};



// --- 폼 내 input 변경사항 interviewData에 반영 ---
function setInputUpdater(id, key) {
  const el = document.getElementById(id);
  if (!el) return;
  el.oninput = function(e) {
    interviewData[key] = e.target.value;
  };
}

document.getElementById('edit-interview-confirm-btn').onclick = async function () {
  // 폼 인풋값을 interviewData에 최신 반영
  interviewData.recruitmentTitle = document.getElementById('recruitmentTitle').value;
  interviewData.hireCount = document.getElementById('hireCount').value;
  interviewData.interviewDate = document.getElementById('interviewDate').value;
  interviewData.interviewType = document.getElementById('interviewType').value;
  interviewData.interviewLocation = document.getElementById('interviewLocation').value;
  
  // 면접 평가항목(동적) input 값을 배열로 다시 수집
  const scoreInputs = document.querySelectorAll('#scoreItemsBox input[type="text"]');
  interviewData.interviewQuestionList = Array.from(scoreInputs).map(input => ({
    interviewNo: input.getAttribute('data-interview-no'),
    interviewQuestionNo: input.getAttribute('data-question-no'),
    interviewQuestionContent: input.value
  }));

  // PUT 요청
  try {
    const res = await axios.put('/ajax/company/interview', interviewData);
    if (res.data  === 'ok') {
      location.href = "/company/interview/detail?interviewNo=" + interview.interviewNo;
    } else {
      alert('저장에 실패했습니다. 다시 시도해 주세요.');
      bootstrap.Modal.getInstance(document.getElementById('editInterviewModal')).hide();
    }
  } catch (err) {
    if (err.response && err.response.data) {
        const errors = err.response.data;
        showValidationErrors(errors);
        bootstrap.Modal.getInstance(document.getElementById('editInterviewModal')).hide();
    }
  }
};

function showValidationErrors(errors) {
  // 기존 에러 메시지 모두 제거
  document.querySelectorAll('.score-error-msg').forEach(e => e.remove());

  Object.entries(errors).forEach(([key, messages]) => {
    // key에서 인덱스 추출
    // 예시: "interviewQuestionList[2].interviewQuestionContent"
    const match = key.match(/interviewQuestionList\[(\d+)\]\.interviewQuestionContent/);
    if (match) {
      const idx = match[1];
      // input id는 scoreItem{idx+1} 형식이므로 +1
      const input = document.getElementById(`scoreItem${parseInt(idx, 10)+1}`);
      if (input) {
        // 에러 메시지 span 생성
        const errorSpan = document.createElement('span');
        errorSpan.className = 'score-error-msg text-danger mt-1';
        errorSpan.style.fontSize = '0.95em';
        errorSpan.textContent = messages[0];
        // input 바로 아래에 붙이기
        input.parentNode.appendChild(errorSpan);
      }
    }
  });
}


// 수정 취소 버튼
document.getElementById('cancelBtn').onclick = function() {
  location.href = "/company/interview/detail?interviewNo=" + interview.interviewNo;
};