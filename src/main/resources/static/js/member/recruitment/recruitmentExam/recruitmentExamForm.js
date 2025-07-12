document.addEventListener('DOMContentLoaded', () => {

     console.log('[examForm.js] DOMContentLoaded');
     const recruitExamNo = new URLSearchParams(window.location.search).get('recruitExamNo');
     console.log('[examForm.js] recruitExamNo =', recruitExamNo);
     console.log('넘어온 체킁', recruitExamNo);
     console.log('인라인 로그, location.search=', window.location.search);
    const qTitle   = document.getElementById('q-title');
    const qOptions = document.getElementById('q-options');
    const status   = document.getElementById('status');
    const prevBtn  = document.getElementById('prevBtn');
    const nextBtn  = document.getElementById('nextBtn');
    const submitBtn= document.getElementById('submitBtn');
    let questions = [], idx = 0;
    const answers = {};// { questionNo: optionNo }

    axios.get(`/ajax/mypage/recruitment_exam/questions/${recruitExamNo}`)
         .then(resp =>{
            questions = resp.data;
            console.log(questions);
            renderAll();
         })
         .catch(err => {
             console.error('에러 체킁', err);
         });

         function renderAll(){
            renderQuestion();
            renderStatus();
            renderButtons();
         }

         function renderQuestion(){
            const q = questions[idx];
            qTitle.textContent = `문제 ${idx + 1}. ${q.recruitExamQuestContent}`;
            qOptions.innerHTML = q.optionList.map(opt =>
                `<label class="option-label">
                    <input type ="radio"
                           name="opt"
						   data-qno="${q.recruitExamQuestNo}" 
                           value="${opt.recruitOptionNo}"
                           ${answers[q.recruitExamQuestNo]===opt.recruitOptionNo?'checked':''}/>
                    ${opt.recruitExamOptionContent}
                </label>
                `).join('');
         }

         function renderStatus(){
            status.innerHTML = questions.map((q,i)=> {
                const questionNo = String(i + 1).padStart(2, '0');
                const selectedOpt = answers[q.recruitExamQuestNo];
                const circlesHtml  = q.optionList.map((opt, idx) =>{
                    const isSel = opt.recruitOptionNo === selectedOpt;
                    return `<span class="circle${isSel ? ' selected' : ''}"> 
                                ${idx + 1}
                            </span>`;
                    }).join('');

                    return `<div class="status-item">
                                <div class="qno">${questionNo}</div>
                                <div class="flex-grow-1">${circlesHtml}</div>
                            </div> 
                            `;
                    }).join('');
         }

         function renderButtons(){
            prevBtn.disabled = idx === 0;
            
            // 현재 질문번호
            const currentQno = questions[idx].recruitExamQuestNo;
            // 답이 있으면 next 활성화
            nextBtn.disabled = !answers[currentQno];

            //마지막 문제면 다음에서  제출 버튼이 보여짐
            if(idx === questions.length - 1){
                nextBtn.classList.add('d-none');
                submitBtn.classList.remove('d-none');
            }else{
                nextBtn.classList.remove('d-none');
                submitBtn.classList.add('d-none');
            }
         }


         qOptions.addEventListener('change', e =>{
            if(e.target.name == 'opt'){
				const qno = e.target.dataset.qno;             
				const ono = e.target.value;                   
				answers[qno] = ono;  
                renderStatus();
                renderButtons();
            }
         })

         prevBtn.addEventListener('click', () =>{
            if(idx > 0) {idx--; renderAll();}
         });
         nextBtn.addEventListener('click', () =>{
            if(answers[questions[idx].recruitExamQuestNo]){
                idx++; 
                renderAll();
            }
         })


           
});
