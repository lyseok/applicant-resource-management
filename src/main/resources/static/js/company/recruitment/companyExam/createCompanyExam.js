import { examData } from '/js/dataConf.js';

// JSON 변환 함수
function formToJson(form) {
  const data = new FormData(form);
  const json = {};
  for (const [fullName, value] of data.entries()) {
    const path = fullName.replace(/\]/g, '').split(/[\[\.]/);
    let cur = json;
    for (let i = 0; i < path.length; i++) {
      const key = path[i];
      if (i === path.length - 1) {
        cur[key] = value;
      } else {
        if (!cur[key]) {
          const next = path[i + 1];
          cur[key] = /^[0-9]+$/.test(next) ? [] : {};
        }
        cur = cur[key];
      }
    }
  }
  return json;
}

document.addEventListener('DOMContentLoaded', () => {
  const containerEl = document.getElementById('container');
  const formEl = document.getElementById('examForm');
  const addQuestionBtn = document.getElementById('addQuestionBtn');
  const submitBtn = document.getElementById('submitAllBtn');
  const exitBtn = document.getElementById('exitBtn');
  const existingExamNo = containerEl.dataset.examId || null;

  // --- 문제 블록 생성 ---
  function addQuestionBlock(idx, data) {
    const div = document.createElement('div');
    div.className = 'card question-card border-secondary p-3 border-opacity-75';
    div.dataset.idx = idx;
    if (data?.comQuestionsNo) div.dataset.qNo = data.comQuestionsNo;

    // 히든 필드 (PK, 삭제일)
    div.innerHTML = `
       <div class="d-flex justify-content-between mb-2">
         <h5 class="form-label fs-16 fw-bold text-dark required">문제 ${idx + 1}</h5>
         <button type="button" class="btn-close remove-question"></button>
       </div>
       <div class="mb-2">
         ${
           data?.comQuestionsNo
             ? `<input type="hidden" name="questionList[${idx}].comQuestionsNo" value="${data.comQuestionsNo}">`
             : ''
         }
         <input type="hidden"
                name="questionList[${idx}].comExamQuestDelDate"
                value="${data?.comExamQuestDelDate || ''}">
         <textarea class="form-control"
		 		   id="questionList[${idx}].comExamContents"
                   name="questionList[${idx}].comExamContents"
                   rows="2"
                   placeholder="문제 내용을 입력">${
                     data?.comExamContents || ''
                   }</textarea>
       </div>

			 <div class="d-flex justify-content-between align-items-end mb-2">
			   <h5 class="form-label fs-16 fw-bold text-dark required mb-0">보기</h5>
			   <button type="button" class="btn btn_violet_line add-opt-btn" data-qidx="${idx}">보기 추가</button>
			 </div>
       <div id="optList${idx}" class="mb-2"></div>
     `;
    document.getElementById('questionContainer').append(div);

    // 문제 삭제: 히든 삭제일 세팅 + 숨기기 + 재인덱싱
    div.querySelector('.remove-question').addEventListener('click', () => {
      const delInput = div.querySelector(
        `input[name="questionList[${idx}].comExamQuestDelDate"]`
      );
      if (delInput) delInput.value = new Date().toISOString();
      div.style.display = 'none';
      reindexQuestions();
    });

    // 기존 보기가 있으면 바인딩
    if (data?.optionList) {
      data.optionList.forEach((opt, optIdx) => {
        appendOption(
          idx,
          optIdx,
          opt.comOptionContent,
          opt.comOptionCorrectYn === 'Y',
          opt.comOptionNo,
          opt.comOptionDelDate
        );
      });
    }

    // + 보기 추가
    div.querySelector('.add-opt-btn').addEventListener('click', () => {
      const optListEl = document.getElementById(`optList${idx}`);
      const visibleCnt = Array.from(optListEl.children).filter(
        (r) => r.classList.contains('option-row') && r.style.display !== 'none'
      ).length;
      if (visibleCnt >= 5) {
        alert('보기는 최대 5개까지 가능합니다.');
        return;
      }
      appendOption(idx, visibleCnt, '', false, null, null);
    });
  }

  // --- 보기(row) 추가 ---
  function appendOption(
    qIdx,
    optIdx,
    value = '',
    isCorrect = false,
    comOptionNo = null,
    delDate = null
  ) {
    const row = document.createElement('div');
    row.className = 'input-group mb-1 option-row';

    // PK 히든 + 삭제일 히든 (항상 생성)
    const hiddenNo = comOptionNo
      ? `<input type="hidden" name="questionList[${qIdx}].optionList[${optIdx}].comOptionNo"           value="${comOptionNo}">`
      : '';
    const hiddenDel = `<input type="hidden" name="questionList[${qIdx}].optionList[${optIdx}].comOptionDelDate" value="${
      delDate || ''
    }">`;

    row.innerHTML = `
       ${hiddenNo}
       ${hiddenDel}
			 <div class="input-group-text form-check m-0 bg-white border-0">
			   <input 
			     class="form-check-input ms-0" 
			     type="radio"
			     name="questionList[${qIdx}].optionList[${optIdx}].comOptionCorrectYn"
			     value="Y" 
			     id="correctYn_${qIdx}_${optIdx}"
			     ${isCorrect ? 'checked' : ''} 
			     aria-label="정답(Y)">
			   <label class="form-check-label ms-2" for="correctYn_${qIdx}_${optIdx}">
			     정답
			   </label>
			 </div>

			 <div class="input-group-text form-check m-0 bg-white border-0">
			   <input 
			     class="form-check-input ms-0" 
			     type="radio"
			     name="questionList[${qIdx}].optionList[${optIdx}].comOptionCorrectYn"
			     value="N" 
			     id="incorrectYn_${qIdx}_${optIdx}"
			     ${!isCorrect ? 'checked' : ''} 
			     aria-label="오답(N)">
			   <label class="form-check-label ms-2" for="incorrectYn_${qIdx}_${optIdx}">
			     오답
			   </label>
			 </div>
       <input type="text"
              class="form-control rounded"
              name="questionList[${qIdx}].optionList[${optIdx}].comOptionContent"
              placeholder="보기 내용을 입력"
		      value="${value}">

       <button type="button" class="btn text-danger btn-sm remove-opt">
			 	<span class="material-symbols-outlined">cancel</span>
			 </button>
     `;
    document.getElementById(`optList${qIdx}`).append(row);

    // 옵션 삭제: 히든 삭제일 세팅 + 숨기기 + 재인덱싱
    row.querySelector('.remove-opt').addEventListener('click', () => {
      const delInput = row.querySelector(
        `input[name="questionList[${qIdx}].optionList[${optIdx}].comOptionDelDate"]`
      );
      if (delInput) delInput.value = new Date().toISOString();
      row.style.display = 'none';
      reindexQuestions();
    });
  }

  function reindexQuestions() {
    document.querySelectorAll('.question-card').forEach((card, newQIdx) => {
      if (card.style.display === 'none') return; // 삭제된 문제는 재인덱스 제외

      // 문제 이름/텍스트 업데이트 (PK는 그대로 유지)
      card
        .querySelectorAll('input[type="hidden"][name^="questionList"]')
        .forEach((input) => {
          if (!/comQuestionsNo/.test(input.name)) {
            // PK는 이름 변경 금지
            input.name = input.name.replace(
              /questionList\[\d+\]/,
              `questionList[${newQIdx}]`
            );
          }
        });

      const ta = card.querySelector('textarea');
      if (ta) ta.name = `questionList[${newQIdx}].comExamContents`;

      // 옵션 리스트
      const optList = card.querySelector(`[id^="optList"]`);
      if (optList) {
        optList.id = `optList${newQIdx}`;
        let optIdx = 0;
        optList.querySelectorAll('.option-row').forEach((row) => {
          if (row.style.display === 'none') return; // 삭제된 보기 제외
          row.querySelectorAll('input').forEach((el) => {
            if (el.name && !/comOptionNo/.test(el.name)) {
              // PK는 이름 변경 금지
              el.name = el.name
                .replace(/questionList\[\d+\]/, `questionList[${newQIdx}]`)
                .replace(/optionList\[\d+\]/, `optionList[${optIdx}]`);
            }
          });
          optIdx++;
        });
      }

      const header = card.querySelector('h5');
      if (header) header.textContent = `문제 ${newQIdx + 1}`;
    });
  }

  // 문제 삭제: 삭제일 세팅 + 숨김
  function markQuestionDeleted(div, idx) {
    const delInput = div.querySelector(
      `input[name="questionList[${idx}].comExamQuestDelDate"]`
    );
    if (delInput) delInput.value = new Date().toISOString();
    div.style.display = 'none';
    reindexQuestions();
  }

  // 보기 삭제: 삭제일 세팅 + 숨김
  function markOptionDeleted(row, qIdx, optIdx) {
    const delInput = row.querySelector(
      `input[name="questionList[${qIdx}].optionList[${optIdx}].comOptionDelDate"]`
    );
    if (delInput) delInput.value = new Date().toISOString();
    row.style.display = 'none';
    reindexQuestions();
  }

  function showFieldErrors(errors) {
    // 기존 모든 에러 메시지 제거
    document.querySelectorAll('.text-danger').forEach((el) => el.remove());

    Object.entries(errors).forEach(([field, messages]) => {
      // --- 1) 보기 개수(@Size) 에러 처리 ---
      let sizeMatch = field.match(
        /^questionList\[(\d+)\]\.optionList(?:\.size)?$/
      );
      if (sizeMatch) {
        const idx = sizeMatch[1];
        const optList = document.getElementById(`optList${idx}`);
        if (!optList) return; // null 체크
        // 기존 span 제거
        let nxt = optList.nextElementSibling;
        if (nxt && nxt.classList.contains('text-danger')) {
          nxt.remove();
        }
        // 새 span 삽입
        const span = document.createElement('span');
        span.className = 'text-danger small d-block';
        span.textContent = messages.join(', ');
        optList.insertAdjacentElement('afterend', span);
        return;
      }

      // --- 2) 문제 내용(NotBlank) 에러 처리 ---
      let qMatch = field.match(/^questionList\[(\d+)\]\.comExamContents$/);
      if (qMatch) {
        const idx = qMatch[1];
        const ta = document.getElementById(
          `questionList[${idx}].comExamContents`
        );
        if (!ta) return; // null 체크
        // 기존 span 제거
        let nxt = ta.nextElementSibling;
        if (nxt && nxt.classList.contains('text-danger')) {
          nxt.remove();
        }
        // 새 span 삽입
        const span = document.createElement('span');
        span.className = 'text-danger small d-block';
        span.textContent = messages.join(', ');
        ta.insertAdjacentElement('afterend', span);
        return;
      }

      // 보기 내용 에러 처리
      let optMatch = field.match(
        /^questionList\[(\d+)\]\.optionList\[(\d+)\]\.comOptionContent$/
      );
      if (optMatch) {
        const [, qIdx, oIdx] = optMatch;
        const input = formEl.querySelector(
          `input[name="questionList[${qIdx}].optionList[${oIdx}].comOptionContent"]`
        );
        if (!input) return; // null 체크

        // 기존 span 제거
        let nxt = input.nextElementSibling;
        if (nxt && nxt.classList.contains('text-danger')) {
          nxt.remove();
        }

        // 새 span 삽입
        const span = document.createElement('span');
        span.className = 'text-danger small d-block'; // 에러 메시지를 아래에 표시
        span.textContent = messages.join(', ');
        input.insertAdjacentElement('afterend', span); // 에러 메시지를 input 바로 아래에 삽입
        return;
      }

      // --- 4) 기타 필드 에러 처리 ---
      const el = formEl.querySelector(`[name="${field}"]`);
      if (!el) return; // 없는 필드는 스킵
      let nxt = el.nextElementSibling;
      if (nxt && nxt.classList.contains('text-danger')) {
        nxt.remove();
      }
      const span = document.createElement('span');
      span.className = 'text-danger small d-block';
      span.textContent = messages.join(', ');
      el.insertAdjacentElement('afterend', span);
    });
  }

  // --- 초기 로드 ---
  if (existingExamNo) {
    axios
      .get(`/ajax/company/company_exam/detail/${existingExamNo}`)
      .then((resp) => {
        const exam = resp.data;
        formEl['comExamName'].value = exam.comExamName;
        exam.questionList.forEach((q, i) => addQuestionBlock(i, q));
      })
      .catch(() => alert('시험 정보를 불러오는 중 오류 발생'));
  } else {
    addQuestionBlock(0);
  }

  addQuestionBtn.addEventListener('click', () => {
    const idx = document.querySelectorAll('.question-card').length;
    addQuestionBlock(idx);
    reindexQuestions();
  });

  // --- 제출 핸들러 ---
  submitBtn.addEventListener('click', () => {
    reindexQuestions();
    const data = formToJson(formEl);
    console.log('▶️ Payload:', JSON.stringify(data, null, 2));
    const method = existingExamNo ? 'put' : 'post';
    const url = existingExamNo
      ? `/ajax/company/company_exam/edit/${existingExamNo}`
      : '/ajax/company/company_exam/create';

    showLoading();
    axios[method](url, data)
      .then(() => {
        alert(existingExamNo ? '수정 완료' : '등록 완료');
        location.href = '/company/company_exam';
      })
      .catch((err) => {
        if (err.response?.status === 400 && err.response.data) {
          showFieldErrors(err.response.data);
        } else {
          console.error(err);
          alert('서버 오류가 발생했습니다.');
        }
      })
      .finally(hideLoading);
  });

  exitBtn.addEventListener('click', () => {
    location.href = '/company/company_exam';
  });

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

  // 데이터 예제 버튼 핸들러
  document.getElementById('data-btn')?.addEventListener('click', () => {
    const examNameEl = document.getElementById('comExamName');
    if (examNameEl) examNameEl.value = examData.comExamName;

    // Clear existing questions
    document.getElementById('questionContainer').innerHTML = '';

    examData.questionList.forEach((q, qi) => {
      addQuestionBlock(qi);
      const questionEl = document.querySelector(
        `[name="questionList[${qi}].comExamContents"]`
      );
      if (questionEl) questionEl.value = q.comExamContents;
      q.optionList.forEach((opt, oi) => {
        appendOption(qi, oi, opt.content, opt.correct === 'Y');
      });
    });
    reindexQuestions();
  });
});
