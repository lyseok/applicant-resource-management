/**
 *
 */
document.addEventListener('DOMContentLoaded', () => {
  const storedUserId = sessionStorage.getItem('userId');
  console.log(storedUserId);
  const examDiv = document.querySelector('.exam-no');
  const examNo = examDiv.dataset.examId;
  const examQuestion = document.querySelector('.exam-question');
  //const examEdit = document.getElementById('exam-edit-btn')
  const exitBtnEl = document.getElementById('exitBtn');

  console.log('체크', examNo);

  axios.get(`/ajax/company/company_exam/detail/${examNo}`).then((resp) => {
    const exam = resp.data;
    console.log(exam);

    
    if (storedUserId === exam.userId) {
      const editBtn = document.getElementById('exam-edit-btn');
      const deleteBtn = document.getElementById('openDeleteModalBtn');
      if (editBtn) editBtn.style.display = 'inline-block';
      if (deleteBtn) deleteBtn.style.display = 'inline-block';
    }

    let html = '';
    exam.questionList.forEach((q, qi) => {
      html += `
						<div class ="card-body question-card">
						<p class="h2">문제 ${qi + 1}: ${q.comExamContents}</p>
							<br>
				            <ul class="list-group">
				              ${q.optionList
                        .map(
                          (opt, oi) =>
                            ` <li class="list-group-item">
							               보기 ${oi + 1}: ${opt.comOptionContent}
							                ${
                                opt.comOptionCorrectYn === 'Y'
                                  ? '<strong>(정답)</strong>'
                                  : ''
                              }
							      </li>`
                        )
                        .join('')}
							</ul>
						</div>`;
    });
    examQuestion.innerHTML = html;
  });

  document.getElementById('exam-edit-btn').addEventListener('click', () => {
    window.location.href = `/company/company_exam/edit/${examNo}`;
  });

  exitBtnEl.addEventListener('click', () => {
    location.href = '/company/company_exam';
  });

  document.getElementById('confirmDeleteBtn').addEventListener('click', () => {
    axios
      .get(`/ajax/company/company_exam/delete/${examNo}`)
      .then((resp) => {
        bootstrap.Modal.getOrCreateInstance(
          document.getElementById('deleteExamModal')
        ).hide();
        location.href = '/company/company_exam';
      })
      .catch((err) => {
        console.error('삭제 오류:', err);
        alert('삭제 중 오류가 발생했습니다.');
      });
  });
});
