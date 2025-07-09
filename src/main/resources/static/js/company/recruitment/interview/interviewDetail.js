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
  const recruitProcess = data.recruitProcess;
  const applicantRecordList = recruitProcess.applicantRecordList;
  const recruitmentNotice = recruitProcess.recruitmentNotice;
  console.log(recruitProcess);
  console.log(applicantRecordList);
  console.log(recruitmentNotice);

  // 예시 데이터 (실제로는 axios 등으로 받아옴)
  const detail = {
    title: recruitProcess.recruitmentTitle,
    jobName: '백엔드 개발자',
    hireCount: 3,
    interviewType: '대면면접',
    interviewDate: '2025-07-14 14:00:00',
    interviewLocation: '서울 본사 3층',
    scoreItems: ['인성', '코딩테스트', '협업능력'],
    videoInterview: {
      roomTitle: '백엔드 채용 1차',
      maxJoinCount: 10,
      startTime: '2025-07-14 10:00:00',
      endTime: '2025-07-14 11:00:00',
    },
    applicants: [
      { name: '홍길동', resumeUrl: '#', time: '2025-07-14 14:10', score: 92 },
      { name: '김철수', resumeUrl: '#', time: '2025-07-14 14:20', score: 85 },
    ],
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
