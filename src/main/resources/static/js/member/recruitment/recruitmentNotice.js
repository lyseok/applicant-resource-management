/**
 * 
 */

//================================= 예솔 - 최근본 공고 인서트 ===========================================
document.addEventListener('DOMContentLoaded', function() {
	const recruitmentNo = document.getElementById('recruitNo').dataset.no;
	console.log("비동기 요청 전 스트립트 동작 확인", recruitmentNo)
	axios.post(`/ajax/recruit_view/${recruitmentNo}`)
	.then(resp => {
		console.log("최근본 공고 insert 확인", resp.data);
	});
});

console.log(document.getElementById('recruitNo').dataset.no);
const mockResumes = [
  
];
let selectedResume = mockResumes[0];

const selectedResumeCard = document.getElementById('selectedResumeCard');
const resumeList = document.getElementById('resumeList');
const btnShowResumeList = document.getElementById('btnShowResumeList');
const btnSaveApplication = document.getElementById('btnSaveApplication');

const btn = document.getElementById('applyBtn');
const deadLineBtn = document.getElementById('deadLineBtn');
  if (btn) {
    btn.onclick = function() {
	  const title = btn.dataset.title || '입사 지원';
	  openApplicationModal(title);
    };
  }
  
  
// ================================ 모달 랜더링 ================================

function openApplicationModal(recruitTitle) {
  // 프로젝트 제목
  const title = btn.dataset.title || '입사 지원';
  document.getElementById('recruitTitle').textContent = title;

  // 모달 띄우기 (부트스트랩)
  const modal = new bootstrap.Modal(document.getElementById('applicationModal'));
  modal.show();
}


// ================================ 이력서 랜더링 ================================



// 이력서 카드(선택된 것) 랜더링 함수
function renderSelectedResumeCard() {
  const div = document.getElementById('selectedResumeCard');
  if (!selectedResume) {
    div.innerHTML = '<span class="text-secondary">이력서를 선택하세요.</span>';
    return;
  }
  div.innerHTML = `
    <div class="card mb-0 selected-card">
      <div class="card-body py-2 px-3">
        <p class="mb-1 text-secondary" style="font-size: .92em;">${selectedResume.UPDATE_DATE ? `수정일: ${selectedResume.UPDATE_DATE}` : ''}</p>
        <h6 class="mb-1">${selectedResume.RESUME_NAME || selectedResume.RESUME_NO}</h6>
      </div>
    </div>
  `;
}


// ================================= 지원 로직 ==================================
btnSaveApplication.onclick = async function () {
  if (!selectedResume) {
    alert("이력서를 선택해주세요!");
    return;
  }

  // prjAnncNo (현재 공고 번호)
  const recruitmentNo = document.getElementById('recruitNo').dataset.no;

  // 이력서 번호
  const resumeNo = selectedResume.RESUME_NO;

  // 서버로 전송할 객체
  const applyData = {
    recruitmentNo,      // 채용공고 번호
    resumeNo        // 이력서 번호
  };

  try {
    // POST로 전송 (엔드포인트는 실제 경로에 맞게!)
    const res = await axios.post('/ajax/resume/recruit', applyData);
    if (res.data === 'ok') {
      alert('지원이 완료되었습니다!');
      // 모달 닫기
      const modal = bootstrap.Modal.getOrCreateInstance(document.getElementById('applicationModal'));
      modal.hide();
    } else {
      alert(res.data.msg || '지원에 실패했습니다. 다시 시도해 주세요.');
    }
  } catch (err) {
    alert('서버 오류가 발생했습니다.\n' + (err.response?.data?.message || ''));
  }
};

//================================= 공고 마감 ===========================================
deadLineBtn.onclick = async function(){	
	
	const recruitmentNo = document.getElementById('recruitNo').dataset.no;
	try{
		const res = await axios.post(`/ajax/recruit/${recruitmentNo}`);
		if(res.data === 'ok'){
			// 버튼 disabled로 만들기 회색 만들기
			alert("공고가 마감되었습니다.");
			deadLineBtn.disabled = true;
	        deadLineBtn.classList.remove('btn_violet');
	        deadLineBtn.classList.add('btn-secondary');
	        deadLineBtn.innerHTML = `<span id="dDayCounter">-</span><br>마감 완료`;
		}
		
	}catch(err){
		alert('서버 오류가 발생했습니다.\n' + (err.response?.data?.message || ''));
	}
}

