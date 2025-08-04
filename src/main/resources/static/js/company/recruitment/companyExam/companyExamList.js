/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
	const examL = document.getElementById('examList');
	const examCount = document.getElementById('examCount')
	axios.get('/ajax/company/company_exam/list')
		.then(resp => {
			const examList = resp.data;
			examCount.textContent = `${examList.length}`;

			examL.innerHTML = ''; // 초기화

			examList.forEach((exam, idx) => {
				const card = document.createElement('div');
				card.className = 'exam-card d-flex align-items-center p-3 border rounded justify-content-between';
				card.dataset.examId = exam.comExamNo;

				card.innerHTML = `
		          <div class="class="exam-name d-flex flex-column gap-1">
								<b>시험문제${idx + 1}. ${exam.comExamName}</b>
								<p class="fs-14 opacity-75">등록된 항목 <span class="text-violet80 fw-semibold">${exam.questionCount}</span></p>
							</div>
		          <div class="exam-date">${exam.comExamCreateDate}</div>
		        `;

				// 카드 클릭 시 상세 페이지 이동
				card.addEventListener('click', () => {
					window.location.href = `/company/company_exam/detail/${exam.comExamNo}`;
				});

				examL.appendChild(card);
			});
		})
		.catch(err => {
			console.error(err);
			examCount.textContent = '시험 목록을 불러오는 중 오류가 발생했습니다.';
		});
});

