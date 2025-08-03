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
				card.className = 'exam-card d-flex align-items-center p-3 border rounded';
				card.dataset.examId = exam.comExamNo;

				card.innerHTML = `
		          <div class="exam-name">${idx + 1}. ${exam.comExamName}</div>
		          <div class="exam-date">등록일시 - ${exam.comExamCreateDate}</div>
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

