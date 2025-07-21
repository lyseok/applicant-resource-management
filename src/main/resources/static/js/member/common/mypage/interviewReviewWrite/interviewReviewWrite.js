/**
 * 
 */

 document.addEventListener('DOMContentLoaded', () => {
	const interviewNo = new URLSearchParams(window.location.search).get('intr_interview_no');
	console.log("체킁", interviewNo);
    const comIdEl       = document.getElementById('comId');
	const companyNameEl = document.getElementById('companyName');
	const interviewDateEl = document.getElementById('interviewDate');
	const jobInput = document.getElementById('jobNameInput');
	const jobCodeHidden = document.getElementById('jobCodeHidden');
	const suggestEl = document.getElementById('jobSuggestions');


	
	
	
	axios.get(`/ajax/member/interview/review/${interviewNo}`)
		 .then(resp => {
			const interviewInfo = resp.data;
			console.log(interviewInfo);
			companyNameEl.value = interviewInfo.COMPANY_NAME;
			const onlyDate = interviewInfo.INTERVIEW_DATE.split(' ')[0];
    		interviewDateEl.value = onlyDate;
			comIdEl.value = interviewInfo.USER_ID;
			const interviewType = interviewInfo.INTERVIEW_TYPE;
			const typeMessageEl = document.getElementById('type-message');
			if(interviewType === 'Y'){
				 document.querySelector('input[name="interviewType"][value="Y"]').checked = true;
				 typeMessageEl.textContent = '화상면접으로 진행하셨습니다.';
			} else if(type === 'N'){
				document.querySelector('input[name="interviewType"][value="N"]').checked = true;
				 typeMessageEl.textContent = '대면면접으로 진행하셨습니다.';
			}

			
		 })

	let jobList = [];
	axios.get('/ajax/code/job')
		 .then(resp=> {
			jobList = resp.data;
			console.log(jobList);
		 })

	jobInput.addEventListener('input', ()=>{
		const search = jobInput.value.trim().toLowerCase();
		suggestEl.innerHTML = '';
		if(!search) return;

		const matches = jobList.filter(job => job.jobName.toLowerCase().includes(search));
		matches.slice(0,10).forEach(job => {
			const li = document.createElement('li');
			li.textContent = job.jobName;
			li.dataset.jobCode = job.jobCode;
			suggestEl.appendChild(li);
		});
	});

	suggestEl.addEventListener('click', e => {
		if(e.target.tagName === 'LI') {
			jobInput.value = e.target.textContent;
			jobCodeHidden.value = e.target.dataset.jobCode;
			suggestEl.innerHTML = '';
		}
	})

	document.addEventListener('click', e => {
		if(!e.target.closest('.section-form-row')){
			suggestEl.innerHTML = '';
		}
	})



	const form = document.getElementById('interviewReviewForm');
	form.addEventListener('submit', function(e) {
		e.preventDefault();

		form.querySelectorAll('span.text-danger').forEach(el => el.remove());
		const fData = new FormData(form);
		const questions = fData.getAll('interviewQuestionContent');
		
		const rawDate  = fData.get('interviewDate');
		const yyyymmdd = rawDate.replace(/-/g, '');

		const payload = {
			comId: fData.get('comId'),
			 // interviewNo는 URL 파라미터에서 이미 꺼내둔 변수 사용
			interviewNo: interviewNo,
			// jobCodeHidden 에 채워둔 코드
			jobCode: fData.get('jobCode'),
			interviewDate: yyyymmdd,
			interviewType: fData.get('interviewType'),
			evaluation: fData.get('evaluation'),
			interviewLevel: fData.get('interviewLevel'),
			// textarea name="interview_content" → interviewContent
			interviewContent: fData.get('interviewContent'),
			interviewPassYn: fData.get('interviewPassYn'),
			interviewQuestionContent: questions,
			tip: fData.get('tip')
		};

		axios.post('/ajax/member/interview/review/write', payload)
			 .then(resp => {
				alert('등록 성공');
			 })
			 .catch(err => {
				
				 if (err.response && err.response.data) {
					const errors = err.response.data; // { field: ["msg1","msg2"], ... }
					console.log('서버 에러:', errors);
					Object.entries(errors).forEach(([field, messages]) => {
						
						const baseField = field.replace(/\[\d+\]$/, '');
						// name 속성 또는 id가 field인 요소 찾아서
						const inputEl = form.querySelector(`[name="${baseField}"], #${baseField}`);
						if (inputEl) {
						// 기존 메시지 지웠으니 바로 추가
						
						const next = inputEl.nextElementSibling;
						  if (next?.classList.contains('text-danger')) next.remove();

						const span = document.createElement('span');
						span.className = 'text-danger small';
						span.textContent = messages.join(', ');
						inputEl.insertAdjacentElement('afterend', span);
				}
          });
        }
	  });
	});
 });