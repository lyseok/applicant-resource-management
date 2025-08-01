/**
 * 
 */
// =============================== 비동기로 공고 상세 데이터 가져오기 =====================================
async function fetchNotice() {
	const res = await axios.get(`/ajax/recruit/${recruitmentNo}`)
	console.log(res);
	return res.data;
}

function renderHeader(data) {
	const container = document.querySelector('.title_inner');
	container.insertAdjacentHTML(
		"afterbegin",
		`<a href="/member/company_view?no=${data.userId}" class="company" target="_blank" id="comName">
            ${data.company.comName || "기업명 없음"}
        </a>`
	);
	document.querySelector('.tit_job').textContent = data.recruitmentTitle;
	document.querySelector('.txt_scrap').textContent = data.viewScrab;

	const dday = calcDday(data.recruitmentFinishDate);
	document.querySelector('.dday').textContent = `D-${dday}`;
	const ddayEl = document.querySelector('.dday');

	const mainBtn = document.querySelector('.for_btn_event');
	const mainBtnSpan = mainBtn.querySelector('.sri_btn_homepage_apply');

	if (data.recruitFinishYn == 'N') {
		if (userName === data.userId) {
			mainBtn.id = 'deadLineBtn';
			mainBtnSpan.textContent = '지원 마감';
		} else {
			mainBtn.id = 'applyBtn';
			mainBtnSpan.textContent = '입사 지원';
			mainBtn.dataset.title = data.recruitmentTitle;
			mainBtn.dataset.recruitNo = data.recruitmentNo;
		}
	} else {
		mainBtn.disabled = true;
		mainBtnSpan.textContent = '마감';
		mainBtn.style.background = 'gray';
		mainBtn.style.color = 'white';
		ddayEl.style.display = 'none';
	}
}

function renderSummary(data) {
	const yearText = data.yearCodeName || '경력 정보 없음';
	document.getElementById('yearCodeName').innerHTML = `<strong>${yearText}</strong>`;
	
	const eduText = data.education?.codeDetailName || "학력 정보 없음";
	document.getElementById('education').innerHTML = `<strong>${eduText}</strong>` ;
	
	document.getElementById('salary').textContent = formatSalary(data.recruitmentSalary);
	
	document.getElementById('addr').innerHTML = `${data.cityCodeName}  ${data.districtCodeName}`;
	document.getElementById('viewRecruit').innerHTML = `조회수<strong>${data.viewHit}</strong>`
	
	document.getElementById('recContent').innerHTML = `${data.recContent}`;	
	
	document.getElementById('companyAddr').innerHTML =`
		<span class="wrap_ic">
          <svg class="ic" width="20" height="20" viewBox="0 0 20 20" fill="none">
            <path
              d="M14.305 10.9376L10.5998 16.6302C10.1775 17.2789 9.21948 17.2542 8.83119 16.5846L5.52495 10.883C4.85419 9.72631 4.5 8.39263 4.5 7.05307C4.5 4.01462 7.00816 1.5 10.0448 1.5C13.0381 1.5 15.5 3.92741 15.5 6.91099C15.5 8.34059 15.0849 9.73943 14.305 10.9376Z"
              stroke="#8491A7"
            ></path>
            <circle cx="10" cy="7" r="2.5" stroke="#8491A7"></circle>
          </svg>
        </span>
        ${data.company.comAddr}
	`;
	
	document.querySelector('.btn_mapview.relay_map').addEventListener('click', function() {
		const address = encodeURIComponent(`${data.company.comAddr}`);
		window.open(`https://map.naver.com/v5/search/${address}`, '_blank');
	});
	
	startCountdown(data.recruitmentFinishDate);
	document.getElementById('startDate').textContent = formatDateTime(data.recruitmentStartdate);
	document.getElementById('endDate').textContent = formatDateTime(data.recruitmentFinishDate);
	
	document.getElementById('recruitDesk').textContent = data.recruitmentDesk;
}

function renderCompanyInfo(company){
	const companyh3 = document.getElementById('companyh3');
	companyh3.insertAdjacentHTML(
		"afterbegin",
		`<a href="/member/company_view?no=${company.userId}" target="_blank">
            ${company.comName || "기업명 없음"}
        </a>`
	);
	
	document.getElementById('comLogo').innerHTML = `
	<img src="${company.comLogo}" alt="기업 로고" style="max-width: 100%; max-height: 100%; object-fit: contain;">
	`;
	
	document.getElementById('ceoName').textContent = company.ceoName;
	document.getElementById('comType').textContent = company.comType+' '+company.comSize;
	document.getElementById('induType').textContent = company.induName;
	document.getElementById('comMember').innerHTML = `${company.comMem}<span>(2025년 기준)</span>`;
	document.getElementById('createYear').innerHTML = `${company.comCreateYear} <span>년</span>`;
	document.getElementById('comUrl').innerHTML = `<a href="${company.comUrl}" target="_blank">${company.comUrl}`;
	document.getElementById('comSumAddr').textContent = company.comAddr;
	
	document.getElementById('companyLink').innerHTML = `
		<a href="/member/company_review/detail?company=${company.userId}" target="_blank" class="spr_jview btn_jview_new btn_link ga_data_layer"><span>기업리뷰</span></a>
		<a href="/member/company_salary/detail?companyId=${company.userId}" target="_blank" class="spr_jview btn_jview_new btn_link ga_data_layer"><span>연봉정보</span></a>
	`;
}

function formatSalary(amount) {
    if (!amount || amount === "0") return "면접 후 결정";
    const num = parseInt(amount, 10);
    const eok = Math.floor(num / 10000);
    const man = num % 10000;
    if (eok > 0 && man > 0) return `${eok}억 ${man}만원`;
    if (eok > 0 && man === 0) return `${eok}억`;
    return `${man}만원`;
}

function formatDateTime(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    return `${year}.${month}.${day} ${hours}:${minutes}`;
}

function startCountdown(endDate) {
    const timerEl = document.querySelector('.info_timer');
    if (!timerEl) return; // 엘리먼트 없으면 종료

    const dayEl = timerEl.querySelector('.day');
    const timeEl = timerEl.querySelector('.time');

    function update() {
        const now = new Date();
        const end = new Date(endDate);
        let diff = end - now;

        if (diff <= 0) { 
            // 마감 처리
            dayEl.textContent = "0";
            timeEl.textContent = "00:00:00";
           
            return;
        }

        const days = Math.floor(diff / (1000 * 60 * 60 * 24));
        diff -= days * (1000 * 60 * 60 * 24);
        const hours = Math.floor(diff / (1000 * 60 * 60));
        diff -= hours * (1000 * 60 * 60);
        const minutes = Math.floor(diff / (1000 * 60));
        diff -= minutes * (1000 * 60);
        const seconds = Math.floor(diff / 1000);

        dayEl.textContent = days;
        timeEl.textContent = `${String(hours).padStart(2, '0')}:${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
    }

    update(); // 초기값 설정
    const interval = setInterval(update, 1000);
}


function renderPositions(positionList){
	const position = document.getElementById('position');
    position.innerHTML = ''; // 기존 내용 초기화
    
    if (!positionList || positionList.length === 0) {
        position.innerHTML = '<span class="text-secondary">등록된 직급/직책이 없습니다.</span>';
        return;
    }

    positionList.forEach(pos => {
        const tag = document.createElement('span');
        tag.className = 'position-tag';
        tag.textContent = `${pos.codeDetailName}`;
        position.appendChild(tag);
    });
}

function renderSkills(skillList) {
    const wrapper = document.getElementById('skill');
    wrapper.innerHTML = ''; // 기존 내용 초기화

    if (!skillList || skillList.length === 0) {
        wrapper.innerHTML = '<span class="text-secondary">등록된 기술이 없습니다.</span>';
        return;
    }

    skillList.forEach(skill => {
        const tag = document.createElement('span');
        tag.className = 'badge-tag lh1';
        tag.textContent = `#${skill.recruitSkillName}`;
        wrapper.appendChild(tag);
    });
}

function calcDday(finishDate) {
	const end = new Date(finishDate);
	const now = new Date();
	return Math.max(0, Math.ceil((end - now) / (1000 * 60 * 60 * 24)));
}

async function renderNotice() {
	try {
		const data = await fetchNotice();
		renderHeader(data);
		renderSummary(data);
		renderCompanyInfo(data.company);
		renderSkills(data.skillList);
		renderPositions(data.positionList);
	} catch (err) {
		console.error("채용공고 데이터를 불러오는 중 오류 : ", err);
	}
}
renderNotice();

//================================= 일반회원의 관심기업 체크 ===========================================
const buttons = document.querySelectorAll('.scrab_company');

async function loadAllScrapStatus(){
    try{
        const data = await fetchNotice();
        const companyId = data.userId;
        const res = await fetch(`/ajax/member/scrabCompany/${companyId}`);
        const json = await res.json();
        
        // 모든 버튼 색상 업데이트
        buttons.forEach(button => {
            const svgPath = button.querySelector('svg path');
            svgPath.setAttribute('fill', json > 0 ? 'red' : 'white');
        });
    }catch(err){
        console.error('스크랩 상태 불러오기 실패', err);
    }
}

// 버튼별 이벤트 바인딩
buttons.forEach(button => {
    button.addEventListener('click', async function(){
        try{
            const svgPath = button.querySelector('svg path');
            const isScrapped = svgPath.getAttribute('fill') === 'red';
            const method = isScrapped ? 'DELETE' : 'POST';

            const data = await fetchNotice();
            const companyId = data.userId;

            const res = await fetch(`/ajax/member/scrabCompany/${companyId}`,{
                method : method,
                headers : {'Content-Type' : 'application/json'}
            });
            const result = await res.text();

            if(result === 'ok'){
                await loadAllScrapStatus(); // 👈 클릭 후 전체 새로고침
            }
        }catch(err){
            console.error('스크랩 처리 실패', err);
        }
    });
});

// 초기 로딩 시 전체 상태 적용
loadAllScrapStatus();


//================================= 일반 회원의 관심 공고 체크 ==========================================
const scrabBtn = document.querySelector('.btn_scrap');
const scrapText = scrabBtn.querySelector('.txt_scrap');

async function loadScrapStatus(){
	try {
        const res = await fetch(`/ajax/member/scrab_recruit/${recruitmentNo}`);
        const scrapped = await res.json(); 
        scrabBtn.classList.toggle('on', scrapped === 1);
    } catch (e) {
        console.error('스크랩 상태 로드 실패', e);
    }
}


async function toggleScrap() {
    try {
        const isScrapped = scrabBtn.classList.contains('on');

        // 1. UI를 즉시 토글 (사용자 체감 빠르게)
        scrabBtn.classList.toggle('on', !isScrapped);

        // 2. 서버 요청
        const method = isScrapped ? 'DELETE' : 'POST';
        const res = await fetch(`/ajax/member/scrab_recruit/${recruitmentNo}`, { method });

        // 3. 서버 응답으로 최종 상태 보정
       const data = await fetchNotice();
       scrapText.innerHTML = data.viewScrab;
       await loadScrapStatus();
    } catch (e) {
        console.error('스크랩 토글 실패', e);
    }
}

scrabBtn.addEventListener('click', toggleScrap);
loadScrapStatus();

//================================= 예솔 - 최근본 공고 인서트 ===========================================
document.addEventListener('DOMContentLoaded', function() {
	console.log("비동기 요청 전 스트립트 동작 확인", recruitmentNo)
	axios.post(`/ajax/recruit_view/${recruitmentNo}`)
		.then(resp => {
			console.log("최근본 공고 insert 확인", resp.data);
		});
});

console.log(recruitmentNo);


// ================================ 모달 랜더링 ================================

function openApplicationModal(recruitTitle) {
	// 공고 제목
	document.getElementById('recruitTitle').textContent = recruitTitle || '입사 지원';

	// 모달 띄우기 (부트스트랩)
	const modal = new bootstrap.Modal(document.getElementById('applicationModal'));
	modal.show();
}

//=============================== 동적 버튼 이벤트 위임===============================
//================================= 입사 지원,공고 마감 ===========================================
const mockResumes = [

];
let selectedResume = mockResumes[0];

const selectedResumeCard = document.getElementById('selectedResumeCard');
const resumeList = document.getElementById('resumeList');
const btnShowResumeList = document.getElementById('btnShowResumeList');
const btnSaveApplication = document.getElementById('btnSaveApplication');

document.addEventListener('click', async (e) => {
	// 입사지원 버튼
	if (e.target.closest('#applyBtn')) {
		const btn = e.target.closest('#applyBtn');
		const title = btn.dataset.title || '입사 지원';
		openApplicationModal(title);
	}

	// 마감 버튼
	if (e.target.closest('#deadLineBtn')) {
		const btn = e.target.closest('#deadLineBtn');

		try {
			const res = await axios.post(`/ajax/recruit/${recruitmentNo}`); // ← 원래대로
			if (res.data === 'ok') {
				alert("공고가 마감되었습니다.");

				const updatedData = await fetchNotice(); // 새 데이터 받아오기
				renderHeader(updatedData); // 헤더 다시 렌더
			} else {
				alert(res.data.msg || '마감 처리에 실패했습니다.');
			}
		} catch (err) {
			alert('서버 오류가 발생했습니다.\n' + (err.response?.data?.message || ''));
		}
	}
});


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
btnShowResumeList.onclick = function() {
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
            <p class="mb-1 text-secondary fs-13 fw-500" >
              ${resume.UPDATE_DATE ? `${resume.UPDATE_DATE}` : ''}
            </p>
            <h6 class="mb-1 fw-500">${resume.RESUME_NAME || resume.RESUME_NAME}</h6>
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
		card.onclick = function() {
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
        <p class="mb-1 text-secondary fs-13 fw-500">${selectedResume.UPDATE_DATE ? `${selectedResume.UPDATE_DATE}` : ''}</p>
        <h6 class="mb-0 fw-500">${selectedResume.RESUME_NAME || selectedResume.RESUME_NO}</h6>
      </div>
    </div>
  `;
}


// ================================= 지원 로직 ==================================
btnSaveApplication.onclick = async function() {
	if (!selectedResume) {
		alert("이력서를 선택해주세요!");
		return;
	}

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


/* ===============================*/

function scrollToWithOffset(selector, offsetPx) {
  const el = document.querySelector(selector);
  if (!el) return;
  
  const topPos = el.getBoundingClientRect().top + window.pageYOffset;
  window.scrollTo({
    top: topPos + offsetPx,
    behavior: 'smooth'
  });
}

// 모든 .spr_jview 버튼에 클릭 리스너 등록
document.querySelectorAll('.spr_jview.ready').forEach(btn => {
  btn.addEventListener('click', function() {
    // 1) 기존에 on 붙어있던 버튼들에서 on 제거
    document
      .querySelectorAll('.spr_jview.ready.on')
      .forEach(el => el.classList.remove('on'));

    // 2) 클릭된 버튼에 on 추가
    this.classList.add('on');

    // 3) scrollToWithOffset 호출
    //    data-* 속성에서 타깃 ID와 offset을 가져오도록 했습니다
    const target = this.getAttribute('data-target');
    const offset = parseInt(this.getAttribute('data-offset'), 10) || 0;
    scrollToWithOffset(target, offset);
  });
});

