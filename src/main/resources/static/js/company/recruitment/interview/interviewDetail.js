console.log('test');

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
  console.log(recruitProcess);
  console.log(applicantRecordList);
  console.log(recruitmentNotice);

  // 예시 데이터 (실제로는 axios 등으로 받아옴)
  const detail = {
    title: recruitmentNotice.recruitmentTitle,
    jobName: recruitmentNotice.jobCodeName,
    hireCount: recruitmentNotice.recPositionNumber,
    interviewType: interview.interviewType === 'Y' ? '화상면접' : '대면면접',
    interviewDate: interview.interviewDate,
    interviewLocation: interview.interviewLocation,
    scoreItems: interviewQuestionList.map(
      (item) => item.interviewQuestionContent
    ),
    videoInterview: {
      roomTitle: videoInterview.roomTitle,
      maxJoinCount: videoInterview.maxJoinCount,
      startTime: videoInterview.startDate,
      endTime: videoInterview.endDate,
    },
    applicants: applicantRecordList.map((item) => ({
      name: item.applicantName,
      resumeUrl: '#', // 필요시 item.resumeUrl 등으로 교체
      time: item.evaluationStartTime ? item.evaluationStartTime : '', // null 처리
      score: item.score ?? 0, // item.score가 없다면 0, 실제로는 적절히 매핑
    })),
  };

  // 뿌리기
  document.getElementById('interviewTitle').textContent = detail.title;
  document.getElementById('jobName').textContent = detail.jobName;
  document.getElementById('hireCount').textContent = detail.hireCount + '명';
  document.getElementById('interviewType').textContent = detail.interviewType;
  document.getElementById('interviewDate').textContent = detail.interviewDate;
  document.getElementById('interviewLocation').textContent =
    detail.interviewLocation;

  // 평가항목 리스트
  document.getElementById('scoreItemList').innerHTML = detail.scoreItems
    .map((item) => `<li class="list-group-item ps-0">${item}</li>`)
    .join('');

  // 화상면접 정보가 있으면
  if (detail.videoInterview) {
    document.getElementById('videoInterviewArea').style.display = '';
    document.getElementById('roomTitle').textContent =
      detail.videoInterview.roomTitle;
    document.getElementById('maxJoinCount').textContent =
      detail.videoInterview.maxJoinCount + '명';
    document.getElementById('videoStartTime').textContent =
      detail.videoInterview.startTime;
    document.getElementById('videoEndTime').textContent =
      detail.videoInterview.endTime;
  }

  // 지원자 리스트
  document.getElementById('applicantList').innerHTML = detail.applicants
    .map(
      (a) => `
      <tr>
        <td>${a.name}</td>
        <td><a href="${a.resumeUrl}" target="_blank">이력서 보기</a></td>
        <td>${a.time}</td>
        <td>${a.score}점</td>
      </tr>
    `
    )
    .join('');
};
