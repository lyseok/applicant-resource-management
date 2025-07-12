/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
    const listEl  = document.getElementById('exam-list');
    const totalEl = document.getElementById('total-count');


    // 시험 목록을 서버에서 가져와 렌더링
    function loadExams() {
      axios.get('/ajax/member/recruitment_exam')
        .then(res => {
          const exams = res.data;
		  console.log(exams);
          renderList(exams);
        })
        .catch(err => {
          listEl.innerHTML = `
            <div class="list-group-item text-center text-danger">
              시험 목록을 불러오는 중 오류가 발생했습니다.
            </div>`;
          console.error(err);
        });
    }

   

    // 리스트 렌더 함수
    function renderList(exams) {
      totalEl.textContent = `총 ${exams.length}건`;
      if (!exams.length) {
        listEl.innerHTML = `
          <div class="list-group-item text-center text-muted">
            현재 응시 가능한 시험이 없습니다.
          </div>`;
        return;
      }
      listEl.innerHTML = ''; // clear
      exams.forEach(exam => {
        const item = document.createElement('div');
        item.className = 'list-group-item exam-item d-flex justify-content-between align-items-center';
        item.innerHTML = `
          <div>
            <div class="h5 mb-1">${exam.recruitExamName}</div>
            <small class="text-secondary">
              시작일시: ${exam.recruitExamStartDate}&nbsp;&nbsp;
              제한시간: ${exam.recruitExamTime}분
            </small>
          </div>
          <div>
            <a href="/member/exam/question/${exam.recruitExamNo}" 
               class="btn btn-sm btn-primary">
              응시하기
            </a>
          </div>`;
        listEl.appendChild(item);
      });
    }

    // 최초 로드
    loadExams();
  });