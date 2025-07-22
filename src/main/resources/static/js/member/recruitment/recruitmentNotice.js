/**
 * 
 */
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

let resumeListCache = [];
const resumeListDiv = document.getElementById('resumeList');
// JS파일 로드 시 즉시 호출!
(async function preloadResumeList() {
  try {
    const res = await axios.get(`/ajax/resume`);
    resumeListCache = res.data;
  } catch (err) {
    resumeListCache = [];
    // console.error('이력서 미리 로딩 실패', err);
  }
})();

// ... 아래는 동일하게 사용 가능
btnShowResumeList.onclick = function () {
  if (resumeListDiv.style.display !== 'none') {
    resumeListDiv.style.display = 'none';
    return;
  }

  // 이미 로딩되어 있으므로 바로 사용
  const resumeList = resumeListCache || [];
  selectedResumeCard.style.display = 'none';

  if (!Array.isArray(resumeList) || resumeList.length === 0) {
    resumeListDiv.innerHTML = `<div class="text-secondary py-2">등록된 이력서가 없습니다.</div>`;
    resumeListDiv.style.display = 'block';
    return;
  }

  // 카드 UI 렌더링
  resumeListDiv.innerHTML = resumeList.map(resume => `
    <div class="card mb-2 resume-card ${selectedResume && selectedResume.RESUME_NO === resume.RESUME_NO ? 'selected-card' : ''}" data-id="${resume.RESUME_NO}">
      <div class="card-body py-2 px-3">
        <div class="d-flex justify-content-between align-items-center">
          <div>
            <p class="mb-1 text-secondary" style="font-size: .92em;">
              ${resume.UPDATE_DATE ? `수정일: ${resume.UPDATE_DATE}` : ''}
            </p>
            <h6 class="mb-1">${resume.RESUME_NAME || resume.RESUME_NAME}</h6>
            <div class="text-secondary" style="font-size:.96em;">
              ${resume.RESUME_MAIN_YN === 'Y' ? `<span class="badge bg-purple">대표 이력서</span>` : ''}
              ${resume.RESUME_SUBMIT_YN === 'Y' ? `<span class="badge bg-success">제출됨</span>` : ''}
            </div>
          </div>
          <div>
            ${resume.PHOTO ? `<img src="${resume.PHOTO}" alt="증명사진" style="width:38px; height:38px; border-radius:50%;">` : ''}
          </div>
        </div>
      </div>
    </div>
  `).join('');

  // 카드 클릭 이벤트로 선택
  resumeListDiv.querySelectorAll('.resume-card').forEach(card => {
    card.onclick = function () {
      const rid = this.getAttribute('data-id');
      selectedResume = resumeList.find(r => r.RESUME_NO === rid);
      renderSelectedResumeCard();
      selectedResumeCard.style.display = 'block';
      resumeListDiv.style.display = 'none';
    };
  });

  resumeListDiv.style.display = 'block';
};

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