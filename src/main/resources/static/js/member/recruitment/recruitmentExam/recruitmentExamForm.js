document.addEventListener('DOMContentLoaded', () => {
	console.log('[examForm.js] DOMContentLoaded');
	const recruitExamNo = new URLSearchParams(window.location.search).get(
		'recruitExamNo'
	);
	const applicantId = new URLSearchParams(window.location.search).get(
		'applicantId'
	);
	console.log('[examForm.js] recruitExamNo =', recruitExamNo);
	console.log('넘어온 체킁1', recruitExamNo);
	console.log('넘어온 체킁2', applicantId);
	console.log('인라인 로그, location.search=', window.location.search);
	const examName = document.getElementById('exam-name');
	const qTitle = document.getElementById('q-title');
	const qOptions = document.getElementById('q-options');
	const status = document.getElementById('status');
	const prevBtn = document.getElementById('prevBtn');
	const nextBtn = document.getElementById('nextBtn');
	const submitBtn = document.getElementById('submitBtn');
	const timer = document.getElementById('timer');
	let questions = [],
		idx = 0;
	const answers = {}; // { questionNo: optionNo }

	axios
		.get(`/ajax/mypage/recruitment_exam/questions/${recruitExamNo}`)
		.then(({ data: exam }) => {
			console.log('시험 전체:', exam);
			examName.textContent = exam.recruitExamName;
			questions = exam.questionList;
			startTimer(exam.recruitExamTime * 60);
			renderAll();
		})
		.catch((err) => {
			console.error('에러 체킁', err);
		});

	function renderAll() {
		renderQuestion();
		renderStatus();
		renderButtons();
	}

	function renderQuestion() {
		const q = questions[idx];
		qTitle.innerHTML = `<span class="d-block fw-bold mb-1">문제 ${idx + 1}.</span> ${q.recruitExamQuestContent}`;
		qOptions.innerHTML = q.optionList
			.map((opt) => {
				const isSel = answers[q.recruitExamQuestNo] === opt.recruitOptionNo;
				return `
			     <label class="option-card${isSel ? ' selected' : ''}">
			       <input
			         type="radio"
			         name="opt"
			         data-qno="${q.recruitExamQuestNo}"
			         value="${opt.recruitOptionNo}"
			         ${isSel ? 'checked' : ''}
			       />
			       <span>${opt.recruitExamOptionContent}</span>
			     </label>
			   `;
			})
			.join('');
	}

	function renderStatus() {
		status.innerHTML = questions
			.map((q, i) => {
				const questionNo = String(i + 1).padStart(2, '0');
				const selectedOpt = answers[q.recruitExamQuestNo];
				const circlesHtml = q.optionList
					.map((opt, idx) => {
						const isSel = opt.recruitOptionNo === selectedOpt;
						return `<span class="circle${isSel ? ' selected' : ''}"> 
                                ${idx + 1}
                            </span>`;
					})
					.join('');

				return `<div class="status-item">
                                <div class="qno">${questionNo}</div>
                                <div class="flex-grow-1">${circlesHtml}</div>
                            </div> 
                            `;
			})
			.join('');
	}

	function renderButtons() {
		prevBtn.disabled = idx === 0;

		// 현재 질문번호
		const currentQno = questions[idx].recruitExamQuestNo;
		// 답이 있으면 next 활성화
		const hasAnswer = !!answers[currentQno];
		nextBtn.disabled = !hasAnswer;

		const isLast = idx === questions.length - 1;

		//마지막 문제면 다음에서  제출 버튼이 보여짐
		if (isLast) {
			nextBtn.classList.add('d-none');
			submitBtn.classList.remove('d-none');
			submitBtn.disabled = !hasAnswer;
		} else {
			nextBtn.classList.remove('d-none');
			submitBtn.classList.add('d-none');
			submitBtn.disabled = true;
		}
	}

	qOptions.addEventListener('change', (e) => {
		if (e.target.name == 'opt') {
			const qno = e.target.dataset.qno;
			const ono = e.target.value;
			answers[qno] = ono;
			renderStatus();
			renderButtons();
		}
	});

	prevBtn.addEventListener('click', () => {
		if (idx > 0) {
			idx--;
			renderAll();
		}
	});
	nextBtn.addEventListener('click', () => {
		if (answers[questions[idx].recruitExamQuestNo]) {
			idx++;
			renderAll();
		}
	});

	function startTimer(totalSeconds) {
		let remaining = totalSeconds;
		const tick = () => {
			const m = String(Math.floor(remaining / 60)).padStart(2, '0');
			const s = String(remaining % 60).padStart(2, '0');
			timer.textContent = `${m}:${s}`;
			if (remaining <= 0) {
				clearInterval(intervalId);
				timeUpHandler();
			}
			remaining--;
		};
		tick();
		const intervalId = setInterval(tick, 1000);
	}

	function buildPayload(timeLimit = true) {
		return questions.map((q) => ({
			applicantId,
			recruitExamNo: q.recruitExamNo,
			recruitExamQuestNo: q.recruitExamQuestNo,
			selectedOptionNo: timeLimit ? answers[q.recruitExamQuestNo] : '0',
		}));
	}

	function submitAnswer(payload) {
		axios
			.post('/ajax/mypage/recruitment_exam/submit', payload)
			.then((resp) => {
				const result = resp.data;
				console.log(result.examTotalScore);
				const iconEl = document.getElementById('resultIcon');
				const msgEl = document.getElementById('resultMessage');

				iconEl.textContent = '🟪 응시가 완료되었습니다.';
				iconEl.style.color = '#8d4dff';
				msgEl.innerHTML = `${result.examTotalScore}/100점 <span style="color:#8d4dff;"></span>`;

				const resultModal = new bootstrap.Modal(
					document.getElementById('resultModal')
				);
				resultModal.show();

				qOptions.querySelectorAll('input').forEach((i) => (i.disabled = true));
				prevBtn.disabled = nextBtn.disabled = submitBtn.disabled = true;

				if (window.opener && !window.opener.closed) {
					window.opener.location.reload();
				}
			})
			.catch((err) => {
				if (err.response && err.response.status == 409) {
					alert(err.response.data);
					submitBtn.disabled = true;
				} else {
					console.error(err);
					alert('제출 중 오류가 발생했습니다.');
				}
			});
	}

	submitBtn.addEventListener('click', () => {
		submitAnswer(buildPayload(true));
	});

	function timeUpHandler() {
		alert('시험시간이 종료되었습니다.');
		submitAnswer(buildPayload(false));
	}
});
