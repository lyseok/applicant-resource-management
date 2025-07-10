document.addEventListener('DOMContentLoaded', () => {
  const containerEl = document.getElementById('container');
  const questionContainerEl = document.getElementById('questionContainer');
  const addQuestionBtnEl = document.getElementById('addQuestionBtn');
  const submitBtnEl = document.getElementById('submitAllBtn');
  const examNameInput = document.getElementById('comExamName');
  const existingExamNo = containerEl.dataset.examId || null;
  
  let questionCount = 0;

  // 문제/보기 생성 함수
  function addQuestionBlock(i, data) {
	const card = document.createElement('div');
	card.className = 'card';
	//card.id = `questionCard${i}`;
	card.dataset.idx = i;
	  if (data?.comQuestionsNo) card.dataset.qNo = data.comQuestionsNo;
	card.innerHTML = `
		<h3>문제 ${i + 1}</h3>
		<button type="button" class="delete-question-btn">×</button>
		<textarea id = "questionText${i}"
						rows="2"
						placeholder="문제를 입력하세요">${data?.comExamContents || ''}</textarea>
		
		<span class = "text-danger" id="question${i}Error"></span>
		<div id = "optionList${i}"></div>
		<button type = "button"
				class = "btn-secondary btn"
				id = "addOptBtn${i}">+ 보기 추가</button>
		`;
		
	 questionContainerEl.append(card);
					
	
	 // 문제 삭제
	    card.querySelector('.delete-question-btn')
	        .addEventListener('click', () => card.remove());
	 
	 let optionCount = 0;
	 
	 if(data?.optionList){
		data.optionList.forEach(opt =>{
			appendOption(i, optionCount,
						 opt.comOptionContent,
						 opt.comOptionCorrectYn ==='Y');
						 
			optionCount++;
		});
	}
	
	
	document.getElementById(`addOptBtn${i}`).addEventListener('click',() =>{
		appendOption(i, optionCount, '', false);
		optionCount++;
		});
	}
	
	
	function appendOption(qIdx, optIdx, value = '', isCorrect = false){
		const row = document.createElement('div');
		row.className = 'option-row';
		row.innerHTML = `
			<input type = "text" 
				   id="question${qIdx}option${optIdx}"
				   placeholder = "보기 내용을 입력"
				   value="${value}" />
			<label>
				<input type="radio"
					   name="correct${qIdx}"
					   value="${optIdx}"
					   ${isCorrect ? 'checked' : ''} /> 정답 
			<button type="button" class="delete-opt-btn">×</button>
			</label>`;
			
	 document.getElementById(`optList${qIdx}`).append(row);
			
	 // 보기 삭제
	     row.querySelector('.delete-opt-btn')
	        .addEventListener('click', () => row.remove());
	   
	}
	
	
	
	//수정 생성 판단 
	if(existingExamNo){
		axios.get(`/ajax/company/companyExam/detail/${existingExamNo}`)
			 .then(resp => {
				const exam = resp.data;
				examNameInput.value = exam.comExamName;
				
				exam.questionList.forEach((question, i) =>{
					addQuestionBlock(i, question);
					questionCount++;
				});
			 })
			 .catch(()=>alert('시험 정보를 불러오는 중 오류 발생 '));
		}else{
			addQuestionBlock(0);
			questionCount = 1;
		}
		
		
		//문제 추가 버튼을 누르면 새 문제 카드 생성
		addQuestionBtnEl.addEventListener('click', ()=>{
			addQuestionBlock(questionCount);
			questionCount++;
		})
		
		
		//등록 버튼 
		submitBtnEl.addEventListener('click', async () =>{
			if(!examNameInput.value.trim()){
				document.getElementById('comExamNameError').textContent = '시험명을 입력하세요 ';
				return;
			}
			document.getElementById('comExamNameError').textContent = '';
		})
		
		
		const questionList = [];
		
		for(let i = 0; i < questionCount; i++){
			const questionTextEl = document.getElementById(`questionText${i}`);
			const questionErrorEl = document.getElementById(`question${i}Error`);
			const questionText = questionTextEl.value.trim();
			
			if(!questionText){
				questionErrorEl.textContent = `문제 ${i+1}를 입력하세요 `;
				return;
			}
			questionErrorEl.textContent = '';
			
			
			const options = document.querySelectorAll(`#optionList${i} input[type="text"]`);
			const radios = document.getElementByName(`correct${i}`);
			
			if(options.length < 2){
				questionErrorEl.textContent = `문제 ${i + 1} 보기를 2개 이상 입력하세요 `;
				return;
			}		
			
			const optionList = [];
			let foundAnswer = false;
			
			optionList.forEach((inp, j) =>{
				const val = inp.value.trim();
				if(!val) return; 
				
				const isChecked = Array.from(radios).some(r => r.checked && r.value ===j);
				if(isChecked) foundAnswer = true;
				
				
				optionList.push({
					comOptionContent: val,
					comOptionCorrect: isChecked ? 'Y' : 'N'
				});
			});
			
			
			if(!foundAnswer){
				questionErrorEl.textContent = `문제 ${i+1} 정답을 선택하세요 `;
				return;
			}
			
			questionList.push({
				comExamContents : questionText,
				optionList
			});
		}
		
		
		const method = existingExamNo ? 'put' : 'post';
		const url = existingExamNo ? `/ajax/company/companyExam/edit/${existingExamNo}` : '/ajax/company/create/companyExam';
		
		
		const payload = {
			comExamName : examNameInput.value.trim(),
			questionList
		};
		if(existingExamNo) payload.comExamNo = existingExamNo;
		
		try{
			axios[method](url, payload)
			     .then(resp => {
					const result = resp.data;
					console.log(result);
					alert(existingExamNo ? '수정 완료 ' : '등록 완료 ');
					window.location.href = '/company/companyExam/list';
				 })
			
		}catch{
			alert('저장 중 오류 발생 ');
		}
	
		
   
});
