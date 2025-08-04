/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
	const urlParams = new URLSearchParams(window.location.search);
	const careerNo = urlParams.get('careerNo');
	console.log("선택된 careerNo:", careerNo);
	const formEl = document.getElementById('reviewForm');
	const commentCard = formEl.querySelector('.review-card.mb-4');
	const questionContainer = document.getElementById('questionCards');
	let comId = null;




	axios.get(`/ajax/member/company_review/career/${careerNo}`)
	  .then(resp => {
	    const career = resp.data;
	    console.log(career);
	    document.getElementById('companyLogo').src = career.company.comLogo;
	    document.getElementById('companyName').textContent = career.comName || '-';
	    document.getElementById('jobName').textContent = career.jobCodeName 
	        ? `${career.jobCodeName}${career.department ? ` (${career.department}에서 근무)` : ''}` 
	        : (career.jobCode || '-');

			
		document.getElementById('com_size').textContent = career.company.comSize || '-';
		document.getElementById('com_mem').textContent = career.company.comMem ||'-';
		document.getElementById('ceo_name').textContent = career.company.ceoName || '-';
	    // endDate(retireDate)가 없으면 재직중, 있으면 퇴사
	    document.getElementById('period').textContent = career.retireDate ? '퇴사' : '재직중';

	    const startDate = career.startWorkDate || '-';
	    const endDate = career.retireDate ? career.retireDate : '현재';
	    document.getElementById('workPeriod').textContent = `${startDate} ~ ${endDate}`;
	  });

	axios.get('/ajax/code/cmncodegroup/REVU')
		.then(resp => {
			const questionList = resp.data.cmnCodeList;
			questionList.forEach((code, idx) => {
				const card = document.createElement('div');
				card.className = 'card review-card';
				card.innerHTML = `
                  <div class="card-body">
                    <label class="required d-block mb-1 fw-semibold">${code.codeName}</label>
                    <div class="form-check-wrap d-flex gap-3">
                      ${[1, 2, 3, 4, 5].map(num => `
                        <div class="form-check">
                          <input class="form-check-input" 
                                 type="radio" 
                                 name="companyReviewQuestion[${idx}].companyReviewScore" 
                                 value="${num}">
                          <label class="form-check-label">
                            ${['매우 아니다', '아니다', '보통', '그렇다', '매우 그렇다'][num - 1]}
                          </label>
                        </div>`).join('')}
                    </div>
                    <input type="hidden" 
                           name="companyReviewQuestion[${idx}].reviewSubjectCode" 
                           value="${code.codeDetailNo}">
                  </div>`;
				questionContainer.appendChild(card);
			});
		});





	// 에러 제거 함수
	function clearError(el) {
		const container = el.closest('.card-body');
		const error = container.querySelector('.text-danger');
		if (error) error.remove();
		container.querySelectorAll('.form-check-input').forEach(r => r.classList.remove('is-invalid'));
	}

	// 값 변경 시 에러 삭제
	formEl.addEventListener('change', e => {
		if (e.target.matches('input[type="radio"], textarea')) {
			clearError(e.target);
		}
	});

	// 3. 폼 제출
	formEl.addEventListener('submit', e => {
		e.preventDefault();
		formEl.querySelectorAll('.text-danger').forEach(el => el.remove());
		formEl.querySelectorAll('.is-invalid').forEach(el => el.classList.remove('is-invalid'));

		const formData = new FormData(formEl);
		const data = Object.fromEntries(formData.entries());
		data.careerNo = careerNo;

		// 라디오 값 배열 변환
		const questions = [];
		for (const [key, value] of formData.entries()) {
			const match = key.match(/companyReviewQuestion\[(\d+)\]\.(\w+)/);
			if (match) {
				const idx = parseInt(match[1]);
				const field = match[2];
				if (!questions[idx]) questions[idx] = {};
				questions[idx][field] = value;
			}
		}
		data.companyReviewQuestion = questions;

		// 전송
		axios.post('/ajax/member/company_review/submit', data)
			.then(() => {
				alert('리뷰가 등록되었습니다.');
				location.href = '/member/company_review';
			})
			.catch(err => {
				if (err.response && err.response.data) {
					const errors = err.response.data;
					Object.entries(errors).forEach(([field, messages]) => {
						const inputs = formEl.querySelectorAll(`[name="${field}"]`);
						if (inputs.length > 0) {
							const container = inputs[0].closest('.card-body');
							const label = container.querySelector('label');
							const span = document.createElement('div');
							span.className = 'text-danger small mt-1';
							span.textContent = messages.join(', ');
							label.insertAdjacentElement('afterend', span);
							container.querySelectorAll('.form-check-input').forEach(r => r.classList.add('is-invalid'));
						}
					});
				}
			});
	});
});
