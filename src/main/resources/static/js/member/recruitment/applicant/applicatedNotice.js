
					/**
 * 
 */
const searchInput = document.getElementById('search-title');
const filterSelect = document.getElementById('filter-period');
const sortSelect = document.getElementById('sort-date');
const totalCount = document.getElementById('exam-count');

let recruitmentData = [];
let filteredData = [];
let currentPage = 1;


function truncateText(text, maxLength) {
    if (!text) return '공고 내용 없음';
    return text.length > maxLength ? text.substring(0, maxLength) + '...' : text;
}

function mapStepType(code) {
    switch (code) {
        case 'RERP-001': return '시험';
        case 'RERP-002': return '면접';
        case 'RERP-003': return '서류';
        default: return code; // 모르는 값은 그대로 출력
    }
}

async function loadAndRenderApplications() {
    try {
        const [infoRes, stepRes] = await Promise.all([
            axios.get('/member/ajax/applicant/list'),
            axios.get('/member/ajax/applicant/step')
        ]);

        const infoList = infoRes.data;
        const stepList = stepRes.data;

        const infoMap = new Map();
        infoList.forEach(item => infoMap.set(item.RECRUITMENTNO, item));

        const mergedData = stepList.map(step => {
            const info = infoMap.get(step.RECRUITMENT_NO) || {};
            return {
                ...info,
                ...step
            };
        });

        recruitmentData = mergedData;
        filterAndSearchData();
    } catch (err) {
        console.error('데이터 불러오기 실패:', err);
        document.getElementById('recruitment-list').innerHTML = `<div class="text-danger">데이터 불러오는 중 오류 발생</div>`;
    }
}

function filterAndSearchData() {
    const keyword = searchInput.value.trim().toLowerCase();
    const period = filterSelect.value;
    const sortOrder = sortSelect.value;
    const now = new Date();

    filteredData = recruitmentData.filter(item => {
        const titleMatch = item.RECRUITMENTTITLE?.toLowerCase().includes(keyword);
        const startDate = new Date(item.RECRUITMENTSTARTDATE);
        let dateMatch = true;

        if (period !== 'all') {
            const monthsAgo = new Date();
            monthsAgo.setMonth(monthsAgo.getMonth() - parseInt(period));
            dateMatch = startDate >= monthsAgo && startDate <= now;
        }

        return titleMatch && dateMatch;
    });

    filteredData.sort((a, b) => {
        const dateA = new Date(a.RECRUITMENTSTARTDATE);
        const dateB = new Date(b.RECRUITMENTSTARTDATE);
        return sortOrder === 'latest' ? dateB - dateA : dateA - dateB;
    });

    renderPagination(filteredData.length);
    renderPage(1);
}

function renderPagination(totalItems) {
    const paginationContainer = document.getElementById('pagination');
    paginationContainer.innerHTML = '';
    const itemsPerPage = 5;
    const totalPages = Math.ceil(totalItems / itemsPerPage);
    
     // ✅ 이전 버튼
    const prevBtn = document.createElement('a');
    prevBtn.className = 'BtnType SizeS BtnPrev';
    prevBtn.textContent = '이전';
    prevBtn.href = 'javascript:void(0)';
    // ❗ 첫 페이지면 비활성화
		if (currentPage === 1) {
		    prevBtn.classList.add('disabled');
		} else {
		    prevBtn.addEventListener('click', (e) => {
		        e.preventDefault();
		        currentPage--; // ✅ 이전 페이지로 이동
		        renderPage(currentPage);
		        renderPagination(totalItems);
		    });
		}
    paginationContainer.appendChild(prevBtn);

    for (let i = 1; i <= totalPages; i++) {
			if (i === currentPage) {
            // ✅ 현재 페이지는 <span>
            const span = document.createElement('span');
            span.className = 'BtnType SizeS page active';
            span.textContent = i;
            paginationContainer.appendChild(span);
        } else{
	        const btn = document.createElement('button');
	        btn.className = 'BtnType SizeS page';
	        btn.textContent = i;	        
          btn.addEventListener('click', () => {
              currentPage = i; // 페이지 업데이트
              renderPage(currentPage);
              renderPagination(totalItems); // 페이지 다시 그림
          });
	        paginationContainer.appendChild(btn);
        }
    }
    
     // ✅ 다음 버튼
    const nextBtn = document.createElement('a');
    nextBtn.className = 'BtnType SizeS BtnNext';
    nextBtn.textContent = '다음';
    nextBtn.href = 'javascript:void(0)';
    nextBtn.disabled = currentPage === totalPages; // 마지막 페이지면 비활성화
    
		// 마지막 페이지면 disabled 클래스만 추가
		if (currentPage === totalPages) {
		    nextBtn.classList.add('disabled');
		}		
		// 클릭 이벤트
		nextBtn.addEventListener('click', (e) => {
		    e.preventDefault();
		
		    // disabled 클래스가 있으면 무시
		    if (nextBtn.classList.contains('disabled')) return;
		
		    currentPage++;
		    renderPage(currentPage);
		    renderPagination(totalItems);
		});

    paginationContainer.appendChild(nextBtn);
}

// 날짜 데이터 변환 (YYYY-MM-DD 형식으로)
function formatHireDate(hireDate) {
  if (!hireDate || hireDate === '-') return '협의';

  // 8자리 문자열이면 가공
  if (/^\d{8}$/.test(hireDate)) {
    const yyyy = hireDate.slice(0, 4);
    const mm = hireDate.slice(4, 6);
    const dd = hireDate.slice(6, 8);
    return `${yyyy}-${mm}-${dd}`;
  }

  // 예외 처리
  return hireDate;
}


function renderPage(page) {
		// 총 갯수 표시
		totalCount.innerHTML = `${filteredData.length}`;
		
		currentPage = page;
    const listContainer = document.getElementById('recruitment-list');
    const itemsPerPage = 5;
    listContainer.innerHTML = '';
    const start = (page - 1) * itemsPerPage;
    const end = start + itemsPerPage;
    const paginatedItems = filteredData.slice(start, end);

    paginatedItems.forEach(item => {
        const row = document.createElement('ul');

        const statusDiv = document.createElement('div');
        statusDiv.className = 'd-flex gap-1 flex-column align-items-center lh1 w100p';

        const isPassedFinal = item.PASS === 'Y' && item.FINAL === 'Y';
        const accepted = item.RECRUIT_ACCEPT_YN === 'Y';
        const hasPasser = item.PASS_ALARM_YN || item.RECRUIT_ACCEPT_YN || item.HIRE_DATE;

		let label = '';
		if (item.FAIL === 'Y') {
			label = `${item.STEP}단계 ${mapStepType(item.STEP_TYPE)} 불합격`;
		} else if (accepted) {
			label = '🎉 최종 합격';
		} else if (isPassedFinal) {
			label = `${item.STEP}단계 ${mapStepType(item.STEP_TYPE)} 합격`;
		} else {
			label = `${item.STEP}단계 ${mapStepType(item.STEP_TYPE)} 심사중`;
		}

        statusDiv.innerHTML = `<div class="fs-14 fw-bold">${label}</div>`;

        // PASSER 정보 표시
        if (isPassedFinal && hasPasser) {
						const formattedHireDate = formatHireDate(item.HIRE_DATE);
            statusDiv.innerHTML += `
                <div class="d-flex align-items-center fs-12 text-center my-2 gap-2">
	                <div class="d-flex align-items-center gap-1">
		                <span class="material-symbols-outlined fs-5">calendar_today</span>
		                <span class="text-muted fw-bold">입사일자</span>
	                </div> 
	                <p>${formattedHireDate}</p>
                </div>
            `;
        }

        // 채용 수락 버튼
        if (isPassedFinal && !accepted) {
            const acceptBtn = document.createElement('button');
            acceptBtn.className = 'btn btn-sm  btn_violet mt-1';
            acceptBtn.innerText = '채용 수락하기';
            acceptBtn.addEventListener('click', async (e) => {
                e.stopPropagation();
                const confirmed = confirm('정말 채용을 수락하시겠습니까?');
                if (!confirmed) return;

                try {
                    const res = await axios.post('/member/ajax/applicant/accept', {
                        applicantId: item.APPLICANT_ID,
                        recruitmentNo: item.RECRUITMENT_NO
                    });
                    if (res.status === 200) {
                        acceptBtn.disabled = true;
                        acceptBtn.innerText = '수락 완료';
                        if (item.HIRE_DATE) {
													const formattedHireDate = formatHireDate(item.HIRE_DATE);
											    statusDiv.innerHTML = `
											    	<div class="fs-14 fw-bold">🎉 최종 합격</div>
										        <div class="d-flex align-items-center fs-12 text-center my-2 gap-2">
									            <div class="d-flex align-items-center gap-1">
								                <span class="material-symbols-outlined fs-5">calendar_today</span>
								                <span class="text-muted fw-bold">입사일자</span>
									            </div> 
									            <p>${formattedHireDate}</p>
										        </div>
											    `;
												} else{
	      									statusDiv.innerHTML = `<div class="fs-14 fw-bold">🎉 최종 합격</div>`;													
												}

                    } else {
                        alert('수락 실패');
                    }
                } catch (err) {
                    console.error('수락 요청 실패:', err);
                    alert('서버 오류로 인해 수락 실패');
                }
            });
            statusDiv.appendChild(acceptBtn);
        }
        
        
        if (item.RECRUITMENTFINISHDATE) {
			    const endDate = new Date(item.RECRUITMENTFINISHDATE); // 마감일
					console.log(endDate);
			    const today = new Date();
					const weekDays = ['일', '월', '화', '수', '목', '금', '토'];
					const todayWeekdayKor = weekDays[today.getDay()]; // 0: 일요일 ~ 6: 토요일
			
			    // 시간은 비교에서 제외하고, 날짜만 비교 (연-월-일 기준)
			    const endYMD = endDate.toISOString().split('T')[0];
			    const todayYMD = today.toISOString().split('T')[0];
			
			    if (endYMD > todayYMD) {
						const [_, mm, dd] = endYMD.split('-');              // ["2025", "09", "09"]
			      finishDateText = `~${mm}-${dd}(${todayWeekdayKor})`;
			    } else {
			        finishDateText = '공고 마감';
			    }
				}


        row.innerHTML = `
        <li class="p-4 border-bottom d-flex justify-content-between align-items-center recruit_list gap-5">
        	<div class="d-flex flex-fill align-items-start">
	        	<div class="com_name_box viewat_box">
							<h6 class="recruit_comName mb-1 text-muted">${item.COM_NAME || '-'}</h6>
							<div class="label">${finishDateText}</div>
						</div>
						<div class="recruit_tit"> 
	          	<a class="d-block fs16 fw-bold m-0" href="/recruit_notice/${item.RECRUITMENTNO}">${item.RECRUITMENTTITLE || '(제목 없음)'}</a>     	
							<div class="d-flex align-items-center">
								<span class="fs-14 text-muted">${item.jobCodeName || '-'}</span>
							</div>
							<span class="fs-14 text-secondary d-block mt-2">${item.RECRUITMENTSTARTDATE?.substring(0, 10) || '-'}</span>
						</div>
	          <div class="recruit_info">
	          	<div class="d-flex align-items-center">
	          		<span class="material-symbols-outlined">distance</span>
	          		<span class="">${item.cityCodeName || '-'} ${item.districtCodeName || '-'}</span>
	        		</div>
							<div class="d-flex align-items-center">
								<span class="material-symbols-outlined">money_bag</span>
								<span class="num_line">${item.SALARY === '0'? '협의 후 결정': item.SALARY + '만원' || '-'}</span>
							</div> 
							<div class="d-flex align-items-center">
								<span class="material-symbols-outlined">business_center</span>
								<span class="num_line">${item.yearCodeName || '-'}</span>
							</div>
	          </div>
	        </div>

						<div class="d-flex gap-1" id="btn_wrap">
							
						</div>
					</li>
        `;
				const btnWrap = row.querySelector('#btn_wrap');
				btnWrap.appendChild(statusDiv);

        // row.appendChild(statusDiv);
        listContainer.appendChild(row);
    });
}

// 이벤트 연결
searchInput.addEventListener('input', filterAndSearchData);
filterSelect.addEventListener('change', filterAndSearchData);
sortSelect.addEventListener('change', filterAndSearchData);

// 초기 실행
loadAndRenderApplications();