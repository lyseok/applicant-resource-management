/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
	const examDiv = document.querySelector('.exam-no');
	const examNo = examDiv.dataset.examId;
	const examQuestion = document.querySelector('.exam-question')
	//const examEdit = document.getElementById('exam-edit-btn')

	console.log("체크", examNo);

	axios.get(`/ajax/company/companyExam/detail/${examNo}`)
		.then(resp => {
			const exam = resp.data;
			console.log(exam);

			let html = '';
			exam.questionList.forEach((q, qi) => {
				html += `
						<div class ="card-body question-card">
						<p class="h2">문제 ${qi + 1}: ${q.comExamContents}</p>
							<br>
				            <ul class="list-group">
				              ${q.optionList.map((opt, oi) =>

					` <li class="list-group-item">
				                  보기 ${oi + 1}: ${opt.comOptionContent}
				                  ${opt.comOptionCorrectYn === 'Y' ? '<strong>(정답)</strong>' : ''}
				                </li>
				              `).join('')}
				            </ul>
				          </div>`;

				examQuestion.innerHTML = html;

			})
		})


	document.getElementById('exam-edit-btn').addEventListener('click', () => {
		window.location.href = `/company/companyExam/edit/${examNo}`;
	})



})