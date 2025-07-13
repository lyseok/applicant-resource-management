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

    localStorage.setItem('interviewDetail', JSON.stringify(data));
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
  const interviewScoreList = interview.interviewScoreList;
  console.log(recruitProcess);
  console.log(applicantRecordList);
  console.log(recruitmentNotice);
    
  // 예시 데이터 (실제로는 axios 등으로 받아옴)
  const detail = {
    title: recruitmentNotice.recruitmentTitle,
    jobName: recruitmentNotice.jobCodeName,
    hireCount: recruitmentNotice.recPositionNumber,
    interviewTypeCode: interview.interviewType,
    interviewType: interview.interviewType === 'Y' ? '화상면접' : '대면면접',
    interviewDate: interview.interviewDate,
    interviewLocation: interview.interviewLocation,
    scoreItems: interviewQuestionList.map(
      (item) => item.interviewQuestionContent
    ),
    videoInterview: videoInterview && videoInterview.roomTitle
    ? {
        roomTitle: videoInterview.roomTitle,
        maxJoinCount: videoInterview.maxJoinCount,
        startTime: videoInterview.startDate,
        endTime: videoInterview.endDate,
      }
    : null, // 또는 {} 도 가능
    // applicants: applicantRecordList.map((item) => ({
    //   name: item.applicantName,
    //   resumeUrl: '#', // 필요시 item.resumeUrl 등으로 교체
    //   time: item.evaluationStartTime ? item.evaluationStartTime : '', // null 처리
    //   score: item.score ?? 0, // item.score가 없다면 0, 실제로는 적절히 매핑
    // })),
    applicants: applicantRecordList.map((item) => {
      const scoreObj = (interviewScoreList || []).find(
        (score) => score.applicantId === item.applicantId
      );
      return {
        name: item.applicantName,
        resumeUrl: '#', // 필요시 item.resumeUrl 등으로 교체
        time: item.evaluationStartTime ? item.evaluationStartTime : '',
        score: scoreObj && scoreObj.applicantRating != null ? scoreObj.applicantRating : 0,
      }
    }),
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
  if(detail.interviewTypeCode === 'Y'){
    const videoArea = document.getElementById('videoInterviewArea');
    if (detail.videoInterview) {
      videoArea.style.display = '';
      document.getElementById('roomTitle').textContent =
        detail.videoInterview.roomTitle;
      document.getElementById('maxJoinCount').textContent =
        detail.videoInterview.maxJoinCount + '명';
      document.getElementById('videoStartTime').textContent =
        detail.videoInterview.startTime;
      document.getElementById('videoEndTime').textContent =
        detail.videoInterview.endTime;
    } else {
      // 안내문구와 버튼 추가
      videoArea.style.display = '';
      videoArea.innerHTML = `
        <div class="p-4 text-center text-secondary">
          <div class="mb-3 fw-semibold fs-5">등록된 화상면접 정보가 없습니다.</div>
          <button class="btn btn-primary" id="addVideoInterviewBtn">
            화상면접 등록
          </button>
        </div>
      `;
      document.getElementById('addVideoInterviewBtn').onclick = function() {
        // 등록 페이지 이동
        const interviewNo = getParam('interviewNo');
        location.href="/company/interview/create?interviewNo=" + interviewNo;
      };
    }
  }

  // 지원자 리스트
  document.getElementById('applicantList').innerHTML = detail.applicants
    .map(
      (a) => `
      <tr>
        <td>${a.name}</td>
        <td><a href="${a.resumeUrl}" target="_blank">이력서 보기</a></td>
        <td>${a.time && a.time.trim() ? a.time : '-'}</td>
        <td>${a.score}점</td>
      </tr>
    `
    )
    .join('');
};




document.getElementById('editVideoInterviewBtn').onclick = function() {
  // 수정 페이지 이동
  const interviewNo = getParam('interviewNo');
  location.href="/company/interview/create?interviewNo=" + interviewNo;
};

// document.getElementById('startInterviewBtn').onclick = async function() {
//   const interviewNo = getParam('interviewNo'); // 인터뷰 번호 파라미터에서 가져오기
//   openEvaluationPopup();
//   try {
//     const res = await axios.get('/ajax/company/videointerview/' + interviewNo, {
//       params: { interviewNo }
//     });
//     const url = res.data;
//     if (url) {
//       window.open(url, '_blank'); // 새 창으로 열기
//     } else {
//       alert('화상면접 주소를 찾을 수 없습니다.');
//     }
//   } catch (e) {
//     alert('화상면접 접속 주소를 불러오는 데 실패했습니다.');
//   }
// };

document.getElementById('startInterviewBtn').onclick = async function() {
  // 1. 비동기 요청해서 "화상면접" 주소로 새 창 먼저 띄우기
  const interviewNo = getParam('interviewNo');
  let interviewPopup;
  try {
    const res = await axios.get('/ajax/company/videointerview/' + interviewNo, {
      params: { interviewNo }
    });
    const url = res.data;
    if (url) {
      interviewPopup = window.open(url, '_blank'); // 새 창으로 열기
      setTimeout(() => {
        openEvaluationPopup(); // localStorage 세팅 및 팝업창 오픈
    
        // 모달닫기
        const modalEl = document.getElementById('joinInterviewModal'); // 닫을 모달의 id
        if (modalEl) {
          const modalInstance = bootstrap.Modal.getInstance(modalEl) || new bootstrap.Modal(modalEl);
          modalInstance.hide();
        }
      }, 500); // 1000ms = 1초
    } else {
      alert('화상면접 주소를 찾을 수 없습니다.');
    }
  } catch (e) {
    alert('화상면접 접속 주소를 불러오는 데 실패했습니다.');
    return;
  }

  // 2. 1초 뒤에 평가 팝업 띄우기
};

function openEvaluationPopup() {
  // 팝업 오픈 (url/이름/옵션)
  window.open('/popup/evaluate', 'evaluate_popup', 'width=600,height=760,scrollbars=yes');
}

document.getElementById('edit-interview-confirm-btn').onclick = function() {
  location.href = "/company/interview/edit";
};