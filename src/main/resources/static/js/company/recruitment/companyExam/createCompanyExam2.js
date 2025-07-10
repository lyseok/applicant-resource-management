document.addEventListener('DOMContentLoaded', () => {
	
  /**
 * form 엘리먼트를 받아 name/value 쌍을 재귀적으로 파싱해
 * 중첩된 객체로 만들어 반환합니다.
 */
function formToJson(form) {
  const data = new FormData(form);
  const json = {};

  for (const [fullName, value] of data.entries()) {
    // "questionList[0].comExamContents" → ["questionList","0","comExamContents"]
    const path = fullName
      .replace(/\]/g, '')        // 대괄호 닫기 제거
      .split(/[\[\.]/);          // [ or . 로 분리

    let cur = json;
    for (let i = 0; i < path.length; i++) {
      const key = path[i];
      // 마지막 키면 값을 할당
      if (i === path.length - 1) {
        cur[key] = value;
      } else {
        // 배열 인덱스(숫자)면 Array로, 아니면 Object로 초기화
        if (!cur[key]) {
          const nextKey = path[i+1];
          cur[key] = /^[0-9]+$/.test(nextKey) ? [] : {};
        }
        cur = cur[key];
      }
    }
  }

  return json;
}


  const containerEl      = document.getElementById('container');
  const formEl           = document.getElementById('examForm');
  const addQuestionBtn   = document.getElementById('addQuestionBtn');
  const submitBtn        = document.getElementById('submitAllBtn');
  const exitBtn          = document.getElementById('exitBtn');
  const existingExamNo   = containerEl.dataset.examId || null;

  let questionCount = 0;

  // --- 문제 카드 추가 함수 ---
  function addQuestionBlock(idx, data) {
    const div = document.createElement('div');
    div.className = 'card p-3 mb-3 question-card';
    div.dataset.idx = idx;
    if (data?.comQuestionsNo) {
      div.dataset.qNo = data.comQuestionsNo;
    }
    div.innerHTML = `
      <div class="d-flex justify-content-between align-items-center mb-2">
        <h5>문제 ${idx + 1}</h5>
        <button type="button" class="btn-close remove-question"></button>
      </div>
      <div class="mb-2">
        <textarea class="form-control"
                  id="questionList[${idx}].comExamContents"
                  name="questionList[${idx}].comExamContents"
                  rows="2"
                  placeholder="문제 내용을 입력">${data?.comExamContents||''}</textarea>
      </div>
      <div id="optList${idx}" class="mb-2"></div>
      <button type="button"
              class="btn btn-sm btn-outline-secondary mb-2 add-opt-btn"
              data-qidx="${idx}">
        + 보기 추가
      </button>
    `;
    document.getElementById('questionContainer').append(div);

    // 문제 삭제
    div.querySelector('.remove-question')
       .addEventListener('click', () => div.remove());

    // 기존 보기 렌더링
    let optCount = 0;
    if (data?.optionList) {
      data.optionList.forEach(opt => {
        appendOption(idx, optCount,
                     opt.comOptionContent,
                     opt.comOptionCorrectYn === 'Y',
                     opt.comOptionNo);
        optCount++;
      });
    }

    // 보기 추가 버튼
    div.querySelector('.add-opt-btn')
       .addEventListener('click', () => {
      if (optCount >= 5) {
        alert('보기는 최대 5개까지 가능합니다.');
        return;
      }
      appendOption(idx, optCount, '', false, null);
      optCount++;
    });
  }

  // --- 보기(row) 추가 함수 ---
  function appendOption(qIdx, optIdx, value='', isCorrect=false, optNo=null) {
    const row = document.createElement('div');
    row.className = 'input-group mb-1 option-row';
    if (optNo) row.dataset.optNo = optNo;
    row.innerHTML = `
      <input type="text"
             id="questionList[${qIdx}].optionList[${optIdx}].comOptionContent"
             name="questionList[${qIdx}].optionList[${optIdx}].comOptionContent"
             class="form-control"
             placeholder="보기 내용을 입력"
             value="${value}">
      <div class="input-group-text">
        <input type="radio"
               name="correct${qIdx}"
               ${isCorrect? 'checked':''}
               aria-label="정답">
      </div>
      <button class="btn btn-outline-danger btn-sm remove-opt">×</button>
    `;
    document.getElementById(`optList${qIdx}`).append(row);

    // 삭제 버튼
    row.querySelector('.remove-opt')
       .addEventListener('click', () => row.remove());
  }

  // 초기 1문제
  if (existingExamNo) {
    axios.get(`/ajax/company/companyExam/detail/${existingExamNo}`)
      .then(resp => {
        const exam = resp.data;
        formEl['comExamName'].value = exam.comExamName;
        exam.questionList.forEach((q, i) => {
          addQuestionBlock(i, q);
          questionCount++;
        });
      })
      .catch(() => alert('시험 정보를 불러오는 중 오류 발생'));
  } else {
    addQuestionBlock(0);
    questionCount = 1;
  }

  addQuestionBtn.addEventListener('click', () => {
    addQuestionBlock(questionCount++);
  });

  // --- 서버 Validation Error 표시 함수 ---
  function showFieldErrors(errors) {
    Object.entries(errors).forEach(([field, messages]) => {
      const el = document.getElementById(field);
      if (!el) return;
      // 기존 에러 제거
      const nxt = el.nextElementSibling;
      if (nxt && nxt.classList.contains('text-danger')) nxt.remove();
      // 새 에러 span 추가
      const span = document.createElement('span');
      span.className = 'text-danger small d-block';
      span.textContent = messages.join(', ');
      el.insertAdjacentElement('afterend', span);
    });
  }

  // --- 제출 핸들러 ---
  submitBtn.addEventListener('click', () => {
    // 1) 화면단 기본 검증 → 생략(필요시 추가)
    //    (시험명, 최소 보기 개수, 정답 선택 등)

    // 2) formToJson 으로 payload 생성
    const data = formToJson(formEl);  

    // 3) REST 호출
    const method = existingExamNo ? 'put' : 'post';
    const url    = existingExamNo
                 ? `/ajax/company/companyExam/edit/${existingExamNo}`
                 : '/ajax/company/companyExam/create';

    axios[method](url, data)
      .then(() => {
        alert(existingExamNo? '수정 완료':'등록 완료');
        location.href = '/company/companyExam';
      })
      .catch(err => {
        if (err.response?.status === 400 && err.response.data) {
          showFieldErrors(err.response.data);
        } else {
          console.error(err);
          alert('서버 오류가 발생했습니다.');
        }
      });
  });

  exitBtn.addEventListener('click', () => {
    location.href = '/company/companyExam';
  });
});
