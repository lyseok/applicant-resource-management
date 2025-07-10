document.addEventListener('DOMContentLoaded', () => {
  
  const containerEl         = document.getElementById('container');            
  const questionContainerEl = document.getElementById('questionContainer');   
  const addQuestionBtnEl    = document.getElementById('addQuestionBtn');    
  const submitBtnEl         = document.getElementById('submitAllBtn');        
  const examNameInput       = document.getElementById('comExamName');         
  const existingExamNo      = containerEl.dataset.examId || null;          
  const exitBtnEl 			= document.getElementById('exitBtn'); 
  const examEditModalEl 	= document.getElementById('examEditModal');
  const addExamModalEl 		= document.getElementById('addExamModal');
 
  let questionCount = 0;  

 
  function addQuestionBlock(idx, data) {
    const card = document.createElement('div');
    card.className = 'card question-card';
    card.dataset.idx = idx;
    if (data?.comQuestionsNo) {
      card.dataset.qNo = data.comQuestionsNo;  
    }
    card.innerHTML = `
      <div class="d-flex justify-content-between align-items-center mb-2">
        <h3>문제 ${idx + 1}</h3>
        <button type="button" class="delete-question-btn">×</button>
      </div>
      <textarea id="questionText${idx}"
                rows="2"
                placeholder="문제를 입력하세요"
      >${data?.comExamContents || ''}</textarea>
      <span class="text-danger" id="question${idx}Error"></span>
      <div id="optList${idx}"></div>
      <button type="button"
              class="btn-secondary btn add-opt-btn"
              data-qidx="${idx}"
      >+ 보기 추가</button>
    `;
    questionContainerEl.append(card);


    card.querySelector('.delete-question-btn')
        .addEventListener('click', () => card.remove());

    
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

   
    card.querySelector('.add-opt-btn')
        .addEventListener('click', () => {
			
	  if(optCount >= 5){
		alert('보기는 5개까지 생성 가능합니다.');
		return;
	  }
      appendOption(idx, optCount, '', false, null);
      optCount++;
      if(optCount === 5)card.querySelector('.add-opt-btn').disabled = ture;
    });
  }

 
  function appendOption(qIdx, optIdx, value = '', isCorrect = false, optNo = null) {
    const row = document.createElement('div');
    row.className = 'option-row';
    if (optNo) row.dataset.optNo = optNo;  

    row.innerHTML = `
      <input type="text"
             placeholder="보기 내용을 입력"
             value="${value}" />
     
      <label>
        <input type="radio"
               name="correct${qIdx}"
               value="${optIdx}"
               ${isCorrect ? 'checked' : ''}
        /> 정답
      </label>
      <button type="button" class="delete-opt-btn">×</button>
    `;
    document.getElementById(`optList${qIdx}`).append(row);


    row.querySelector('.delete-opt-btn')
       .addEventListener('click', () => row.remove());
  }


  if (existingExamNo) {
    
    axios.get(`/ajax/company/companyExam/detail/${existingExamNo}`)
         .then(resp => {
           const exam = resp.data;
           examNameInput.value = exam.comExamName;
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


  addQuestionBtnEl.addEventListener('click', () => {
    addQuestionBlock(questionCount);
    questionCount++;
  });

  
  submitBtnEl.addEventListener('click', async () => {
   
    const name = examNameInput.value.trim();
    if (!name) {
      document.getElementById('comExamNameError')
              .textContent = '시험명을 입력하세요';
      return;
    }
    document.getElementById('comExamNameError').textContent = '';

  
    const cards = document.querySelectorAll('.question-card');
    const questionList = [];
   	const optionList = [];
    for (const card of cards) {
      const idx   = card.dataset.idx;
      const text  = card.querySelector('textarea').value.trim();
      const errEl = document.getElementById(`question${idx}Error`);
      if (!text) {
        errEl.textContent = `문제 ${+idx + 1}를 입력하세요`;
        return;
      }
      errEl.textContent = '';

      const opts  = card.querySelectorAll(`#optList${idx} input[type="text"]`);
      const radios = card.querySelectorAll(`input[name="correct${idx}"]`);
      if (opts.length < 2) {
        errEl.textContent = `문제 ${+idx + 1} 보기를 2개 이상 입력하세요`;
        return;
      }

	  for(let i = 0; i < opts.length; i++){
		if(!opts[i].value.trim()){
			errEl.textContent = `문제 ${idx + 1}의 보기 ${i + 1} 내용을 입력하세요`;
			return;
		}
	  }
	  errEl.textContent = '';

 
      let hasAnswer = false;
      opts.forEach((inp, j) => {
        const val = inp.value.trim();
        if (!val) return;
        const isC = radios[j]?.checked;
        if (isC) hasAnswer = true;
        optionList.push({
          comOptionNo: inp.closest('.option-row').dataset.optNo || null,
          comOptionContent: val,
          comOptionCorrectYn: isC ? 'Y' : 'N'
        });
      });
      if (!hasAnswer) {
        errEl.textContent = `문제 ${+idx + 1} 정답을 선택하세요`;
        return;
      }
      errEl.textContent = '';

      questionList.push({
        comQuestionsNo: card.dataset.qNo || null,
        comExamContents: text,
        optionList
      });
    }

   
    const payload = {
      comExamNo: existingExamNo,
      comExamName: name,
      questionList
    };
    const method = existingExamNo ? 'put' : 'post';
    const url    = existingExamNo
                 ? `/ajax/company/companyExam/edit/${existingExamNo}`
                 : '/ajax/company/companyExam/create';

    try {
      await axios[method](url, payload);
      alert(existingExamNo ? '수정 완료' : '등록 완료');
      location.href = '/company/companyExam';
    } catch (e) {
      console.error(e);
      alert('저장 중 오류 발생');
    }
  });
  
  
  
  exitBtnEl.addEventListener('click', () =>{
	  location.href = '/company/companyExam';
  })
  
});
