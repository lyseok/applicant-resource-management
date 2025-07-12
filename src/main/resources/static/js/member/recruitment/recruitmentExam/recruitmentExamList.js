/**
 *
 */

document.addEventListener('DOMContentLoaded', () => {
  const examCountEl = document.getElementById('exam-count');
  const examListEl = document.getElementById('exam-list');
  const examBtnEl = document.getElementById('exam-btn');
  const examListGroupEl = document.querySelectorAll;

  axios
    .get('/ajax/mypage/recruitment_exam')
    .then((resp) => {
      const exams = resp.data;
      console.log('가져온 시험들 체킁: ', exams);
      renderExams(exams);
    })
    .catch((err) => {
      console.error('에러 체킁', err);
      examListEl.innerHTML = `
        <div class="list-group-item text-center text-muted">시험 목록을 불러오는 중 오류</div>
        `;
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
      const examStartDate = new Date(exam.recruitExamStartDate);
      const currentDate = new Date(); // 현재 시간
      const isExamAvailable = currentDate >= examStartDate; // 현재 시간이 시작일시 이후면 응시 가능
      item.className =
        'list-group-item exam-item d-flex justify-content-between align-items-center';
      item.innerHTML = `
                          <div>
                            <div class="h5 mb-1">${exam.recruitExamName}</div>
                                <small class="text-secondary">
                                  시작일시 : ${
                                    exam.recruitExamStartDate
                                  }&nbsp;&nbsp;
                                  시험시간 : ${exam.recruitExamTime}분
                                </small>
                            </div>
                            <div>
                             <a href ="/mypage/recruitment_exam/form" class ="btn btn-sm btn-primary 
                              ${isExamAvailable ? '' : 'disabled'}" > 
                              ${isExamAvailable ? '응시하기' : '응시불가'}</a>
                              <a href ="javascript:void(0)" class ="btn btn-sm btn-primary" data-exam-no="${
                                exam.recruitExamNo
                              }"  id="exam-btn">응시</a>
                            </div>
                          </div>`;

      examListEl.append(item);
    });
  }

  examListEl.addEventListener('click', (event) => {
    if (event.target && event.target.id === 'exam-btn') {
      const recruitExamNo = event.target.dataset.examNo;
      console.log('시험번호 체킁: ', recruitExamNo);

      openRecruitExamPopup(recruitExamNo);
    }
  });

  function openRecruitExamPopup(recruitExamNo) {
    const examPopup = window.open(
      `/popup/exam_form?recruitExamNo=${recruitExamNo}`,
      'exam_popup',
      'width=600, height=760, scrollbars=yes'
    );
    examPopup.focus();
  }
});
