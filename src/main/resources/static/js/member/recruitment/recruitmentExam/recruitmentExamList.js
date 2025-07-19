/**
 *
 */

document.addEventListener('DOMContentLoaded', () => {
  const examCountEl = document.getElementById('exam-count');
  const examListEl = document.getElementById('exam-list');
  const examBtnEl = document.getElementById('exam-btn');
  const confirmExamBtn = document.getElementById('confirmExamBtn');
  const examListGroupEl = document.querySelectorAll;
  const examSearchEl     = document.getElementById('examSearch');
  const examModal = new bootstrap.Modal(document.getElementById('recruitExamModal'));
  const notAvailableModal = new bootstrap.Modal(document.getElementById('notAvailableModal'));

  let allExams = [];
  axios
    .get('/ajax/mypage/recruitment_exam')
	.then(({ data: exams }) => {
		  console.log('가져온 시험들 체킁: ', exams);
	      allExams = exams;
	      renderExams(allExams);
	    })
    .catch((err) => {
      console.error('에러 체킁', err);
      examListEl.innerHTML = `
        <div class="list-group-item text-center text-muted">시험이 존재하지 않습니다.</div>
        `;
    });
	
	
	
	examSearchEl.addEventListener('input', () => {
	    const keyword = examSearchEl.value.trim().toLowerCase();
	    const filtered = keyword
	      ? allExams.filter(exam => 
	          exam.recruitExamName.toLowerCase().includes(keyword) ||
	          exam.recruitmentTitle.toLowerCase().includes(keyword) ||
	          exam.companyName.toLowerCase().includes(keyword)
	        )
	      : allExams;
	    renderExams(filtered);
	  });

	  
  function renderExams(exams) {
    examCountEl.textContent = `총 ${exams.length}건`;
    if (!exams.length) {
      item.innerHTML = `<div class="list-group-item text-center text-muted">시험이 존재하지 않습니다.</div>`;
      return;
    }
    examListEl.innerHTML = '';
    exams.forEach((exam) => {
      const item = document.createElement('div');
      // 시작일시 비교
      const startDate = new Date(exam.recruitExamStartDate); //시작일시
      const endDate   = new Date(startDate.getTime() + exam.recruitExamTime * 60 * 1000); //시작일시 + 시험시간
      const nowDate = new Date(); // 현재 시간
      // 시험 전 또는 시험 종료 후 모두 불가
      const isExamAvailable = (nowDate >= startDate && nowDate <= endDate);
      const isTaken         = exam.taken === 1 || exam.taken === true;
      item.className =
        'list-group-item exam-item d-flex justify-content-between align-items-center';
      item.innerHTML = `
                          <div>
                            <div class="h5 mb-1">${exam.recruitExamName}</div>
							    <div class="small text-secondary mb-1">
							     ${exam.companyName} &nbsp;|&nbsp; ${exam.recruitmentTitle}
							    </div>
                                <small class="text-secondary">
                                  시작일시 : ${
                                    exam.recruitExamStartDate
                                  }&nbsp;&nbsp;
                                  시험시간 : ${exam.recruitExamTime}분
                                </small>
                            </div>
                            <div>
                            ${isTaken
                              ? `<button type="button" class="btn btn_gray_line btn-secondary" disabled>응시완료</button>
                                  <button type="button" 
                                    class="btn btn_violet_line btn-info result-btn"
                                    data-exam-no="${exam.recruitExamNo}"
                                    data-applicant-id="${exam.applicantId}">
                                    시험결과
                                </button>`
                              : `
                                <button type="button" class="btn btn_violet btn-primary exam-btn"
                                  data-exam-no="${exam.recruitExamNo}"
                                  data-applicant-id="${exam.applicantId}"
                                  data-exam-time="${exam.recruitExamTime}"
                                  data-start-date="${exam.recruitExamStartDate}"
								  data-available="${isExamAvailable}" >
                                  응시
                                </button>`}
                          </div>
                        `;

      examListEl.append(item);
    });
  }


  examListEl.addEventListener('click', event => {
      const btn = event.target;
	  
	  //왜 이걸먼저 처리하지..
	  if(btn.classList.contains('result-btn')){
        const recruitExamNo = btn.dataset.examNo;
        const applicantId = btn.dataset.applicantId;
         const resultPopup = window.open(
          `/popup/exam_result?recruitExamNo=${recruitExamNo}&applicantId=${applicantId}`,
          'result_popup',
          'width=600,height=400,scrollbars=yes'
        );
        if (resultPopup) resultPopup.focus();
        return;
      }
  
      if (!btn.classList.contains('exam-btn')) return;
      const recruitExamNo = event.target.dataset.examNo;
      const applicantId = event.target.dataset.applicantId;
      const recruitExamTime = event.target.dataset.examTime;
      console.log('시험번호 체킁: ', recruitExamNo);
      console.log('지원자번호 체킁: ', applicantId);

       const available = btn.dataset.available === 'true';
       if (!available) {
         // 기간 아닐 때
         notAvailableModal.show();
         return;
       }

   
      confirmExamBtn.dataset.examNo = recruitExamNo;
      confirmExamBtn.dataset.applicantId = applicantId;
      confirmExamBtn.dataset.examTime = recruitExamTime;

      examModal.show();
      //openRecruitExamPopup(recruitExamNo, applicantId,recruitExamTime);
    
  });


  confirmExamBtn.addEventListener('click', () =>{
      const recruitExamNo = confirmExamBtn.dataset.examNo;
      const applicantId = confirmExamBtn.dataset.applicantId;

      examModal.hide();

      const examPopup = window.open(
        `/popup/exam_form?recruitExamNo=${recruitExamNo}&applicantId=${applicantId}`,
        'exam_popup',
        'width=600,height=760,scrollbars=yes'
      );
      if (examPopup) examPopup.focus();
  })
});


