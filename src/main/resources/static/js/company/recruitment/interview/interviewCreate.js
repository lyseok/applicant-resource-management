let gDetail;

function getParam(name) {
  // 쿼리스트링에서 파라미터 추출 (크로스 브라우저)
  const url = new URL(window.location.href);
  return url.searchParams.get(name);
}

const detailDate = async () => {
  const interviewNo = getParam('interviewNo');
  const { data, status } = await axios.get(
    '/ajax/company/interview/' + interviewNo
  );
  if (status) {
    console.log(data);
    setData(data);
  }
};

detailDate();

const setData = (data) => {
  const interview = data;
  const interviewQuestionList = interview.interviewQuestionList;
  const videoInterview = interview.videoInterview;
  const recruitProcess = interview.recruitProcess;
  const applicantRecordList = recruitProcess.applicantRecordList;
  const recruitmentNotice = recruitProcess.recruitmentNotice;

  // 예시 데이터 (실제로는 axios 등으로 받아옴)
  const detail = {
    title: recruitmentNotice.recruitmentTitle,
    companyInterviewUrl: videoInterview.companyInterviewUrl,
    videoInterviewNo: videoInterview.videoInterviewNo,
    interviewNo: interview.interviewNo,
    jobName: recruitmentNotice.jobCodeName,
    hireCount: recruitmentNotice.recPositionNumber,
    interviewDate: interview.interviewDate,
    recContent : recruitmentNotice.recContent,
    applicants: applicantRecordList.map((item) => ({
      name: item.applicantName,
      applicantId: item.applicantId,
      resumeUrl: '#', // 필요시 item.resumeUrl 등으로 교체
      time: item.evaluationStartTime ? item.evaluationStartTime : '', // null 처리
    })),
  };

  gDetail = detail;

  // 뿌리기
  document.getElementById('noticeTitle').textContent = detail.title;
  document.getElementById('jobName').textContent = detail.jobName;
  document.getElementById('hireCount').textContent = detail.hireCount + '명';
  document.getElementById('interviewDate').textContent = detail.interviewDate;
  document.getElementById('interviewInfo').textContent = detail.recContent;
  
  // hidden input에 value값삽입
  document.getElementById('videoInterviewNo').value = detail.videoInterviewNo;
  document.getElementById('interviewNo').value = detail.interviewNo;
  document.getElementById('companyInterviewUrl').value = detail.companyInterviewUrl;
  // 지원자 리스트
  document.getElementById('applicantTimeList').innerHTML = detail.applicants
    .map(
      (a) => `
      <tr>
        <td>${a.name}</td>
        <td><a href="${a.resumeUrl}">이력서 보기</a></td>
        <td>
          <input
            type="datetime-local"
            name="applicantTime_${a.applicantId}"
            class="form-control"
            value="${a.time ? a.time.replace(' ', 'T').substring(0, 16) : ''}"
          >
        </td>
      </tr>
    `
    )
    .join('');
  
};

const form = document.getElementById('videoInterviewForm');
form.addEventListener('submit', function(e) {
  e.preventDefault();

  // FormData 객체로 폼 데이터 수집
  const formData = new FormData(form);

  // applicantId와 time을 추출해서 배열로 만듦
  const applicantTimes = [];
  for (let [key, value] of formData.entries()) {
    if (key.startsWith("applicantTime_")) {
      const applicantId = key.replace("applicantTime_", "");
      applicantTimes.push({
        applicantId: applicantId,
        evaluationStartTime: value
      });
    }
  }

  const data = {
    videoInterviewNo: formData.get('videoInterviewNo'),
    interviewNo: formData.get('interviewNo'),
    companyInterviewUrl: formData.get('companyInterviewUrl'),
    roomTitle: formData.get('roomTitle'),
    maxJoinCount: formData.get('maxJoinCount'),
    startDate: formData.get('startDate'),
    endDate: formData.get('endDate'),
    applicantTimes: applicantTimes // 배열!
  };
  // axios POST로 전송 (예시 URL)
  axios.post('/ajax/company/interview/video', data)
    .then(res => {
      alert('저장 성공!');
      // 필요시 location.href = ... 등 처리
    })
    .catch(err => {
      // 에러 응답이 있고, 응답 데이터가 존재하면
      if (err.response && err.response.data) {
        const errors = err.response.data; // 서버에서 내려준 에러(필드별 메시지) 객체

        // errors 객체의 각 필드별로 반복
        Object.entries(errors).forEach(([field, messages]) => {
          const el = document.getElementById(field); // 필드명(id)로 input 요소 찾기
          if (el) {
            // 기존에 이미 에러메시지(span)가 있으면 삭제 (한 번에 한 개만)
            const next = el.nextElementSibling;
            if (next && next.classList.contains('text-danger')) next.remove();

            // 새로운 에러 메시지(span) 생성
            const span = document.createElement('span');
            span.className = 'text-danger small'; // 부트스트랩 에러 텍스트 스타일
            span.textContent = messages.join(', '); // 에러 메시지가 여러 개일 경우 ,로 합침
            el.insertAdjacentElement('afterend', span); // input 아래에 에러 메시지 삽입
          }
        });
        setErrorData(errors);
      }
    });
});

const setErrorData = (errors) => {
  document.getElementById('applicantTimeList').innerHTML = gDetail.applicants
    .map((a, i) => {
      // errors 키가 applicantTimes[인덱스].evaluationStartTime
      const errorKey = `applicantTimes[${i}].evaluationStartTime`;
      const errorMsg = errors && errors[errorKey] ? errors[errorKey].join(', ') : '';
  
      return `
        <tr>
          <td>${a.name}</td>
          <td><a href="${a.resumeUrl}">이력서 보기</a></td>
          <td>
            <input
              type="datetime-local"
              name="applicantTime_${a.applicantId}"
              class="form-control"
              value="${a.time ? a.time.replace(' ', 'T').substring(0, 16) : ''}"
            >
            ${errorMsg ? `<span class="text-danger small">${errorMsg}</span>` : ''}
          </td>
        </tr>
      `;
    })
    .join('');
}