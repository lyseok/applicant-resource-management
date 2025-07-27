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
			suggestEl.style.border = "1px solid #dbdbdb";
		});
	});

	suggestEl.addEventListener('click', e => {
		if(e.target.tagName === 'LI') {
			jobInput.value = e.target.textContent;
			jobCodeHidden.value = e.target.dataset.jobCode;
			suggestEl.innerHTML = '';
			suggestEl.style.border = "none";
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
		
			interviewContent: fData.get('interviewContent'),
			interviewPassYn: fData.get('interviewPassYn'),
			interviewQuestion: fData.get('interviewQuestion'),
			tip: fData.get('tip')
		};

		axios.post('/ajax/member/interview/review/write', payload)
			 .then(resp => {
				console.log(payload);
				alert('등록 성공');
				location.href = '/mypage/interview';
			 })
			 .catch(err => {
				
				 console.log('서버 에러 응답:', err.response?.data); // ✅ 꼭 확인

				if (err.response && err.response.data) {
						const errors = err.response.data;

						Object.entries(errors).forEach(([field, messages]) => {
							let inputEl = form.querySelector(`[name="${field}"], #${field}`);
							if (inputEl) {
								// wrapper 선택, 없을경우 ✅ inputEl 감싸는 새로운 div 생성
								let wrapper = inputEl.closest(".input-wrapper");

								if (field === "jobCode") {
					        inputEl = form.querySelector('#jobNameInput');
						    }
								
								if (!wrapper) {
									wrapper = document.createElement("div");
									wrapper.classList.add("input-wrapper", "w-100", "d-flex", "flex-column");
									inputEl.parentElement.insertBefore(wrapper, inputEl);
									wrapper.appendChild(inputEl);
								}

								// 기존 에러 제거
								const oldError = wrapper.querySelector(`.error`);
								if (oldError) oldError.remove();

								// 새 에러 span 추가
								const errorSpan = document.createElement('span');
								errorSpan.className = `error text-danger d-block mt-1 fs-14`;
								errorSpan.innerText = messages.join(', ');

								wrapper.appendChild(errorSpan);
							}
						});
					}
	  });
	});


	document.querySelector('.btn_red_line').addEventListener('click', () => {
  if (confirm('정말 취소하시겠습니까? 입력 내용이 사라집니다.')) {
    window.history.back(); // 또는 location.href = '/mypage/interview';
  }
});

 });