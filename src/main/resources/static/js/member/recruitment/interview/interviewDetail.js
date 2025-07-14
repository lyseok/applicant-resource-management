// 면접상세 데이터 불러오기 예시
// 면접번호 등으로 조회
const params = new URLSearchParams(window.location.search);
const interviewNo = params.get('interviewNo');

axios.get(`/ajax/member/mypage/interview/${interviewNo}`)
  .then(res => {
    const data = res.data;
    console.log(data);

    // 채용공고
    document.getElementById('REPR_COMPANY_NAME').textContent = data.COPN_COM_NAME;
    document.getElementById('RENO_RECRUITMENT_TITLE').textContent = data.RENO_RECRUITMENT_TITLE;
    document.getElementById('RENO_JOB_CODE').textContent = data.RENO_JOB_CODE;
    document.getElementById('APPLICANT_COUNT').textContent = data.APPLICANT_COUNT;
    document.getElementById('RENO_RECRUITMENT_CHARGER_TEL').textContent = `${data.RENO_RECRUITMENT_CHARGER_TEL} (${data.REPR_COMPANY_NAME})`;
    document.getElementById('RENO_RECRUITMENT_FINISH_DATE').textContent = formatIsoDateToDatetime(data.RENO_RECRUITMENT_FINISH_DATE);

    // 면접정보
    document.getElementById('INTR_INTERVIEW_DATE').textContent = formatIsoDateToDatetime(data.INTR_INTERVIEW_DATE);
    document.getElementById('INTR_INTERVIEW_LOCATION').textContent = data.INTR_INTERVIEW_LOCATION;
    document.getElementById('INTR_INTERVIEW_TYPE').textContent = data.INTR_INTERVIEW_TYPE === 'Y' ? '화상면접' : '대면면접';
    document.getElementById('INTR_INTERVIEW_PASS_SCORE').textContent = data.INTR_INTERVIEW_PASS_SCORE;
    document.getElementById('VIV_MAX_JOIN_COUNT').textContent = data.VIV_MAX_JOIN_COUNT;
    document.getElementById('REPR_RECRUIT_PROCESS_STEP').textContent = data.REPR_RECRUIT_PROCESS_STEP;
    document.getElementById('ETC_INFO').textContent = data.ETC_INFO ?? '';

    // 수정하기 버튼 링크
    document.getElementById('editBtn').href = `/member/recruitment/interview/edit/${interviewNo}`;

    // 화상면접 정보
    if (data.INTR_INTERVIEW_TYPE === 'Y') {
      document.getElementById('videoInterviewArea').style.display = '';
      document.getElementById('VIV_ROOM_TITLE').textContent = data.VIV_ROOM_TITLE;
      document.getElementById('VIV_COMPANY_INTERVIEW_URL').href = data.VIV_COMPANY_INTERVIEW_URL;
      document.getElementById('VIV_START_DATE').textContent = formatIsoDateToDatetime(data.VIV_START_DATE);
      document.getElementById('VIV_END_DATE').textContent = formatIsoDateToDatetime(data.VIV_END_DATE);
    } else {
      document.getElementById('videoInterviewArea').style.display = 'none';
    }

    // 면접 질문(항목)
    const questionList = data.interviewQuestionList || [];
    const listElem = document.getElementById('interviewQuestionList');
    listElem.innerHTML = questionList.map(q => `<li class="list-group-item">${q.INQ_INTERVIEW_QUESTION_CONTENT}</li>`).join('');
  })
  .catch(err => {
    alert('면접 정보 조회 실패');
    console.error(err);
  });

// 날짜 포맷 함수 (yyyy-mm-dd HH:MM:SS)
function formatIsoDateToDatetime(isoString) {
  if (!isoString) return '';
  const date = new Date(isoString);
  const pad = n => n < 10 ? '0' + n : n;
  const year = date.getFullYear();
  const month = pad(date.getMonth() + 1);
  const day = pad(date.getDate());
  const hour = pad(date.getHours());
  const min = pad(date.getMinutes());
  const sec = pad(date.getSeconds());
  return `${year}-${month}-${day} ${hour}:${min}:${sec}`;
}