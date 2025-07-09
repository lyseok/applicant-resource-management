document.addEventListener('DOMContentLoaded', () => {
  const questionContainer = document.getElementById('questionContainer');
  const addQbtn = "";
  const existingExamNo = body.dataset.examId;
  let questionCount = 0;

  // 문제/보기 블록 생성 함수
  function addQuestionBlock(idx) {
    const card = document.createElement('div');
    card.className = 'card';
    card.id = `qCard${idx}`;
    card.innerHTML = `
      <h3>문제 ${idx + 1}</h3>
      <textarea id="qText${idx}" rows="2" placeholder="문제 내용을 입력"></textarea>
      <div id="optList${idx}"></div>
      <button type="button" class="btn-secondary btn" id="addOptBtn${idx}">+ 보기 추가</button>
    `;
    questionContainer.append(card);

    let optCount = 0;
    const addOptBtn = document.getElementById(`addOptBtn${idx}`);
    addOptBtn.addEventListener('click', () => {
      const row = document.createElement('div');
      row.className = 'option-row';
      row.innerHTML = `
        <input type="text" id="q${idx}opt${optCount}" placeholder="보기 내용 입력" />
        <label>
          <input type="radio" name="correct${idx}" value="${optCount}" /> 정답
        </label>
      `;
      document.getElementById(`optList${idx}`).append(row);
      optCount++;
    });
  }

  // 초기 1문제 블록
  addQuestionBlock(questionCount);
  questionCount++;

  // 문제 추가 버튼
  document.getElementById('addQuestionBtn').addEventListener('click', () => {
    addQuestionBlock(questionCount);
    questionCount++;
  });
  
  
 
 
 	

  // 전체 제출 버튼
  document.getElementById('submitAllBtn').addEventListener('click', async () => {
    const examName = document.getElementById('comExamName').value.trim();
    if (!examName) {
      return alert('시험명을 입력하세요!');
    }

    // questionList 수집
    const questionList = [];
    for (let i = 0; i < questionCount; i++) {
      const qText = document.getElementById(`qText${i}`).value.trim();
      if (!qText) {
        return alert(`문제 ${i + 1} 내용을 입력하세요!`);
      }

      const optsContainer = document.getElementById(`optList${i}`);
      const textInputs = optsContainer.querySelectorAll('input[type=text]');
      const radios     = document.getElementsByName(`correct${i}`);
      if (textInputs.length === 0) {
        return alert(`문제 ${i + 1} 보기를 하나 이상 추가하세요!`);
      }

      // optionList 생성
      const optionList = [];
      textInputs.forEach((input, j) => {
        const content = input.value.trim();
        if (!content) return;
        const isCorrect = Array.from(radios).some(r => r.checked && +r.value === j);
        optionList.push({
          comOptionContent: content,
          comOptionCorrectYn: isCorrect ? 'Y' : 'N'
        });
      });

      questionList.push({
        comExamContents: qText,
        optionList
      });
    }

  
	    const payload = {
	      comExamName: examName,
	      questionList
	    };

    try {
      const data = await axios.post(
        '/ajax/company/companyExam/full',
        payload
      );
        alert(data);
        window.location.href = '/company/companyExam';
      
   
    } catch (err) {
      console.error(err);
      alert('저장 중 오류 발생');
    }
  });
});
