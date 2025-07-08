/**
 * 
 */

document.addEventListener('DOMContentLoaded', () =>{
	 	let comExamNo = "";
	 	let comQuestionsNo = "";
	 	const questionContainer = document.getElementById('questionContainer');
	 	
	 	const addQuestionBlock = (idx) =>{
			const card = document.createElement('div')
			card.className = "card"
			card.id = `qCard${idx}`;
			
			card.innerHTML = `
								<h3>문항 ${idx + 1}</h3>
								<textarea id= "qText${idx }" rows="2" cols="60" placeholder = "문항 내용을 입력"></textarea>
								<div id = "optList${idx }"></div>	
								<button type = "button" class = "btn" id = "addOptBtn${idx }"> + 보기 추가</button>`;
			questionContainer.append(card);
								
		
		
		
		let optCount = 0;
		document.getElementById(`addOptBtn${idx}`).addEventListener('click', ()=>{
			const row = document.createElement('div');
			row.className = 'option-row';
			row.innerHTML = `
								<input type = "text" id = "q${idx }opt${optCount}" placeholder = "보기 내용 입력">
								<label><input type ="radio" name = "correct${idx }" value = "${optCount }">정답</label>
							`;
			document.getElementById(`optList${idx}`).append(row);
			optCount++;
			})
		}	
		
		
		addQuestionBlock(0);
		let questionCount = 1;
		
		
		
		document.getElementById('addQuestionBtn').addEventListener('click', () => {
			addQuestionBlock(questionCount);
			questionCount++;
		})
		
		
		document.getElementById('submitAllBtn').addEventListener('click', async () => {
			  const examName = document.getElementById('comExamName').value.trim();
		    if (!examName) return alert('시험명을 입력하세요!');
		
		    try {
		  
		      const respExam = await axios.post(
		        '/ajax/company/companyExam/exam',
		        { comExamName: examName }
		      );
		      comExamNo = respExam.data;
		      
		  
		      for (let i = 0; i < questionCount; i++) {
		        const qText = document.getElementById(`qText${i}`).value.trim();
		        if (!qText) throw new Error(`문항 ${i + 1} 내용을 입력하세요!`);
		        const respQ = await axios.post(
		          '/ajax/company/companyExam/question',
		          { comExamNo, comExamContents: qText }
		        );
		        const comQuestionsNo = respQ.data;
		
		      
		        const optsContainer = document.getElementById(`optList${i}`);
		        const radios = document.getElementsByName(`correct${i}`);
		        const optionElems = optsContainer.querySelectorAll('input[type=text]');
		        if (optionElems.length === 0) throw new Error(`문항 ${i + 1} 보기를 하나 이상 추가하세요!`);
		
		     
		        for (let j = 0; j < optionElems.length; j++) {
		          const content = optionElems[j].value.trim();
		          if (!content) throw new Error(`문항 ${i + 1}, 보기 ${j + 1} 내용을 입력하세요!`);
		          const isCorrect = Array.from(radios).find(r => r.checked && +r.value === j) ? 'Y' : 'N';
		          await axios.post(
		            '/ajax/company/companyExam/option',
		            {
		              comQuestionsNo,
		              comOptionContent: content,
		              comOptionCorrectYn: isCorrect
		            }
		          );
		        }
		      }
		
		      alert('시험·문항·보기 모두 생성 완료!');
		      window.location.reload();
		    } catch (e) {
		      alert(e.message);
		    }
		  });
		    
});