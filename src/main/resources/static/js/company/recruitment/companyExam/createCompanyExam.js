// createCompanyExam2.js
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
  const containerEl    = document.getElementById('container');
  const formEl         = document.getElementById('examForm');
  const addQuestionBtn = document.getElementById('addQuestionBtn');
  const submitBtn      = document.getElementById('submitAllBtn');
  const exitBtn        = document.getElementById('exitBtn');
  const existingExamNo = containerEl.dataset.examId || null;

  let questionCount = 0;

  // --- 문제 카드 추가 ---
  function addQuestionBlock(idx, data) {
    const div = document.createElement('div');
    div.className = 'card p-3 mb-3 question-card';
    div.dataset.idx = idx;
    if (data?.comQuestionsNo) div.dataset.qNo = data.comQuestionsNo;

    div.innerHTML = `
      <div class="d-flex justify-content-between mb-2">
        <h5>문제 ${idx + 1}</h5>
        <button type="button" class="btn-close remove-question"></button>
      </div>
      <div class="mb-2">
        <textarea class="form-control"
                  id="questionList[${idx}].comExamContents"
                  name="questionList[${idx}].comExamContents"
                  rows="2"
                  placeholder="문제 내용을 입력">${data?.comExamContents||''}</textarea>
        <span id="error-question-${idx}" class="text-danger small"></span>
      </div>
      <div id="optList${idx}" class="mb-2"></div>
      <button type="button"
              class="btn btn-sm btn-outline-secondary mb-2 add-opt-btn"
              data-qidx="${idx}">
        + 보기 추가
      </button>
    `;
    document.getElementById('questionContainer').append(div);

    // 삭제 버튼: 카드 제거 후 인덱스 재정렬
    div.querySelector('.remove-question').addEventListener('click', () => {
      div.remove();
      reindexQuestions();
    });

    // 기존 보기가 있으면 렌더
    if (data?.optionList) {
      data.optionList.forEach((opt, optIdx) => {
        appendOption(
          idx,
          optIdx,
          opt.comOptionContent,
          opt.comOptionCorrectYn === 'Y',
          opt.comOptionNo
        );
      });
    }

    // 보기 추가 버튼 (화면에 보이는 옵션 수 기준 5개 제한)
    div.querySelector('.add-opt-btn').addEventListener('click', () => {
      const optListEl = document.getElementById(`optList${idx}`);
      const visibleCount = Array.from(optListEl.children)
        .filter(r => r.classList.contains('option-row'))
        .filter(r => r.style.display !== 'none')
        .length;

      if (visibleCount >= 5) {
        alert('보기는 최대 5개까지 가능합니다.');
        return;
      }
      const newIdx = optListEl.querySelectorAll('.option-row').length;
      appendOption(idx, newIdx, '', false, null);
    });
  }

  // --- 보기(row) 추가 ---
  function appendOption(qIdx, optIdx, value = '', isCorrect = false, comOptionNo = null) {
    const row = document.createElement('div');
    row.className = 'input-group mb-1 option-row';

    // 기존 옵션이면 hidden inputs 로 comOptionNo, comOptionDelDate 관리
    const hiddenNo = comOptionNo
      ? `<input type="hidden"
                name="questionList[${qIdx}].optionList[${optIdx}].comOptionNo"
                value="${comOptionNo}">`
      : '';
    const hiddenDel = comOptionNo
      ? `<input type="hidden"
                name="questionList[${qIdx}].optionList[${optIdx}].comOptionDelDate"
                value="">`
      : '';

    row.innerHTML = `
      ${hiddenNo}
      ${hiddenDel}
      <input type="text"
             class="form-control"
             id="questionList[${qIdx}].optionList[${optIdx}].comOptionContent"
             name="questionList[${qIdx}].optionList[${optIdx}].comOptionContent"
             placeholder="보기 내용을 입력"
             value="${value}">
      <div class="input-group-text">
        <input type="radio"
               name="questionList[${qIdx}].optionList[${optIdx}].comOptionCorrectYn"
               value="Y"
               ${isCorrect ? 'checked' : ''}
               aria-label="정답(Y)">
      </div>
      <div class="input-group-text">
        <input type="radio"
               name="questionList[${qIdx}].optionList[${optIdx}].comOptionCorrectYn"
               value="N"
               ${!isCorrect ? 'checked' : ''}
               aria-label="오답(N)">
      </div>
      <button type = "button" class="btn btn-outline-danger btn-sm remove-opt">×</button>
    `;
    const optListEl = document.getElementById(`optList${qIdx}`);
    optListEl.append(row);

    // 삭제 버튼: 논리 삭제 표시 + 화면 숨김
    row.querySelector('.remove-opt').addEventListener('click', () => {
      const delInput = row.querySelector(
        `input[name="questionList[${qIdx}].optionList[${optIdx}].comOptionDelDate"]`
      );
      if (delInput) {
        delInput.value = new Date().toISOString();
      }
      row.style.display = 'none';
    });
  }

  // --- 인덱스 재정렬 함수 (추가된 부분) ---
  function reindexQuestions() {
    document.querySelectorAll('.question-card').forEach((card, newIdx) => {
      card.dataset.idx = newIdx;
      card.querySelector('h5').textContent = `문제 ${newIdx + 1}`;

      const ta = card.querySelector('textarea');
      ta.id   = `questionList[${newIdx}].comExamContents`;
      ta.name = `questionList[${newIdx}].comExamContents`;

      card.querySelector(`[id^="error-question-"]`).id = `error-question-${newIdx}`;

      const optList = card.querySelector(`[id^="optList"]`);
      optList.id = `optList${newIdx}`;

      optList.querySelectorAll('.option-row').forEach((row, optIdx) => {
        row.querySelectorAll('input, button').forEach(el => {
          if (el.name) {
            el.name = el.name
              .replace(/questionList\[\d+\]/, `questionList[${newIdx}]`)
              .replace(/optionList\[\d+\]/,   `optionList[${optIdx}]`);
          }
          if (el.id) {
            el.id = el.id
              .replace(/questionList\[\d+\]/, `questionList[${newIdx}]`)
              .replace(/optionList\[\d+\]/,   `optionList[${optIdx}]`);
          }
        });
      });
    });
  }

  // --- 서버 Validation Error 표시 함수 ---
  function showFieldErrors(errors) {
    console.log('>> 서버 검증 에러:', errors);
    Object.entries(errors).forEach(([field, messages]) => {
      // 1) 보기 수(@Size) 에러
      if (/^questionList\[\d+\]\.optionList(\.size)?$/.test(field)) {
        const idx = field.match(/^questionList\[(\d+)\]/)[1];
        const errEl = document.getElementById(`error-question-${idx}`);
        if (errEl) errEl.textContent = messages.join(', ');
        return;
      }
      // 2) 보기 내용(NotBlank) 에러
      const optMatch = field.match(
        /^questionList\[(\d+)\]\.optionList\[(\d+)\]\.comOptionContent$/
      );
      if (optMatch) {
        const [, qIdx, oIdx] = optMatch;
        const input = formEl.querySelector(
          `input[name="questionList[${qIdx}].optionList[${oIdx}].comOptionContent"]`
        );
        if (input) {
          const ex = input.parentElement.querySelector('.text-danger');
          if (ex) ex.remove();
          const span = document.createElement('span');
          span.className = 'text-danger small d-block';
          span.textContent = messages.join(', ');
          input.insertAdjacentElement('afterend', span);
        }
        return;
      }
      // 3) 문제 내용(NotBlank) 에러
      const qMatch = field.match(/^questionList\[(\d+)\]\.comExamContents$/);
      if (qMatch) {
        const idx = qMatch[1];
        const errEl = document.getElementById(`error-question-${idx}`);
        if (errEl) errEl.textContent = messages.join(', ');
        return;
      }
      // 4) 기타 일반 폼 필드 에러
      const el = formEl.querySelector(`[name="${field}"]`);
      if (el) {
        const nxt = el.nextElementSibling;
        if (nxt && nxt.classList.contains('text-danger')) nxt.remove();
        const span = document.createElement('span');
        span.className = 'text-danger small d-block';
        span.textContent = messages.join(', ');
        el.insertAdjacentElement('afterend', span);
      }
    });
  }

  // --- 초기 로드 & 문제 추가 핸들러 ---
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
    const idx = document.querySelectorAll('.question-card').length;
    addQuestionBlock(idx);
    reindexQuestions();
  });

  // --- 제출 핸들러 ---
  submitBtn.addEventListener('click', () => {
    const data   = formToJson(formEl);
    const method = existingExamNo ? 'put' : 'post';
    const url    = existingExamNo
                 ? `/ajax/company/companyExam/edit/${existingExamNo}`
                 : '/ajax/company/companyExam/create';

    axios[method](url, data)
      .then(() => {
        alert(existingExamNo ? '수정 완료' : '등록 완료');
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
