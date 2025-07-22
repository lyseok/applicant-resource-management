/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
	let interviewReviewList = []; // 전체 데이터 저장
	const container = document.getElementById('interviewReviewList');
	const searchInput = document.getElementById('searchInput');

	// 렌더링 함수
	function renderList(list) {
		container.innerHTML = '';
		list.forEach((item, idx) => {
			const comName = item.company.comName;
			const passYn = item.passInformation.interviewPassYn === 'Y' ? '합격' : '불합격';
			const badgeClass = item.passInformation.interviewPassYn === 'Y' ? 'badge-pass' : 'badge-fail';
			const evalText = item.interviewInformation.evaluation === '1' ? '긍정적'
				: item.interviewInformation.evaluation === '2' ? '보통' : '부정적';
			const levelText = item.interviewInformation.interviewLevel === '1' ? '상'
				: item.interviewInformation.interviewLevel === '2' ? '중' : '하';
			const interviewType = item.interviewInformation.interviewType === 'Y' ? '화상 면접' : '대면 면접';
			const questionList = item.passInformation.interviewQuestion?.split('\n') || [];
			const jobName = item.recruitmentNotice.jobCodeName;
			const yearName = item.recruitmentNotice.yearCodeName;
			const reviewDate = item.interviewReviewDate;
			const interviewDate = item.interviewDate;
			const interviewContent = item.interviewInformation.interviewContent;
			const tip = item.passInformation.tip || '-';

			const accordionItem = `
	        <div class="accordion-item mb-3">
	          <h2 class="accordion-header" id="heading${idx}">
	            <button class="accordion-button collapsed" type="button"
	              data-bs-toggle="collapse" data-bs-target="#collapse${idx}"
	              aria-expanded="false" aria-controls="collapse${idx}">
	              <div>
	                <div class="fw-bold">${comName}
	                  <span class="badge ${badgeClass} ms-2">${passYn}</span>
	                </div>
	                <small class="text-muted">${jobName} | ${interviewDate} · ${yearName}</small>
	              </div>
	              <div class="ms-md-auto text-muted small">${reviewDate}</div>
	            </button>
	          </h2>
	          <div id="collapse${idx}" class="accordion-collapse collapse"
	               aria-labelledby="heading${idx}" data-bs-parent="#interviewReviewList">
	            <div class="accordion-body">
	              <div class="p-4 bg-white shadow-sm rounded">
	                <div class="row text-center border rounded py-3 mb-4">
	                  <div class="col">
	                    <div class="fw-bold">전체적 평가</div>
	                    <div class="text-success">${evalText}</div>
	                  </div>
	                  <div class="col">
	                    <div class="fw-bold">난이도</div>
	                    <div class="text-secondary">${levelText}</div>
	                  </div>
	                  <div class="col">
	                    <div class="fw-bold">결과</div>
	                    <div class="text-danger">${passYn}</div>
	                  </div>
	                </div>

	                <div>
	                  <div class="section-title">면접 유형</div>
	                  <p>${interviewType}</p>

	                  <div class="section-title">면접 인원</div>
	                  <p>지원자 1명, 면접관 다수</p>

	                  <div class="section-title">진행 및 면접 진행 방식</div>
	                  <p>${interviewContent}</p>

	                  <div class="section-title">면접 질문</div>
	                  <ul>
	                    ${questionList.map(q => `<li>${q}</li>`).join('')}
	                  </ul>

	                  <div class="section-title">TIP 및 특이사항</div>
	                  <p>${tip}</p>
	                </div>
	              </div>
	            </div>
	          </div>
	        </div>`;
			container.insertAdjacentHTML('beforeend', accordionItem);
		});
	}

	// 초기 데이터 로드
	axios.get('/ajax/member/interview/review')
		.then(resp => {
			interviewReviewList = resp.data;
			renderList(interviewReviewList);
		})
		.catch(err => console.error(err));

	searchBtn.addEventListener('click', () => {
		const keyword = searchInput.value.trim().toLowerCase();
		if (!keyword) {
			renderList(interviewReviewList);
			return;
		}
		const filtered = interviewReviewList.filter(item => {
			const comName = item.company.comName.toLowerCase();
			const jobName = item.recruitmentNotice.jobCodeName.toLowerCase();
			const title = item.recruitmentNotice.recruitmentTitle.toLowerCase();
			const content = item.interviewInformation.interviewContent.toLowerCase();
			const tip = (item.passInformation.tip || '').toLowerCase();
			const questions = (item.passInformation.interviewQuestion || '').toLowerCase();

			return [comName, jobName, title, content, tip, questions]
				.some(field => field.includes(keyword));
		});
		renderList(filtered);
	});
});