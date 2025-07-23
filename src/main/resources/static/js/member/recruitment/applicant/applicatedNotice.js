/**
 * 
 */
const searchInput = document.getElementById('search-title');
const filterSelect = document.getElementById('filter-period');
const sortSelect = document.getElementById('sort-date');

let recruitmentData = [];
let filteredData = [];

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

    for (let i = 1; i <= totalPages; i++) {
        const btn = document.createElement('button');
        btn.className = 'btn btn-sm btn-outline-secondary mx-1';
        btn.textContent = i;
        btn.addEventListener('click', () => renderPage(i));
        paginationContainer.appendChild(btn);
    }
}

function renderPage(page) {
    const listContainer = document.getElementById('recruitment-list');
    const itemsPerPage = 5;
    listContainer.innerHTML = '';
    const start = (page - 1) * itemsPerPage;
    const end = start + itemsPerPage;
    const paginatedItems = filteredData.slice(start, end);

    paginatedItems.forEach(item => {
        const row = document.createElement('div');
        row.className = 'd-flex align-items-center justify-content-between border rounded p-3 mb-3 bg-white shadow-sm';

        const statusDiv = document.createElement('div');
        statusDiv.className = 'text-end small text-muted';

        const isPassedFinal = item.PASS === 'Y' && item.FINAL === 'Y';
        const accepted = item.RECRUIT_ACCEPT_YN === 'Y';
        const hasPasser = item.PASS_ALARM_YN || item.RECRUIT_ACCEPT_YN || item.HIRE_DATE;

        let label = '';
        if (accepted) {
            label = '🎉 최종 합격 • 수락 완료';
        } else if (isPassedFinal) {
            label = `${item.STEP}단계 ${mapStepType(item.STEP_TYPE)} 합격`;
        } else {
            label = `${item.STEP}단계 ${mapStepType(item.STEP_TYPE)} 심사중`;
        }

        statusDiv.innerHTML = `<div>${label}</div>`;

        // PASSER 정보 표시
        if (isPassedFinal && hasPasser) {
            statusDiv.innerHTML += `
                <div class="mt-1 small">
                    📥 수락여부: ${item.RECRUIT_ACCEPT_YN === 'Y' ? '✔ 수락 완료' : '❗ 수락 대기'}<br/>
                    🗓 채용일자: ${item.HIRE_DATE ?? '-'}
                </div>
            `;
        }

        // 채용 수락 버튼
        if (isPassedFinal && !accepted) {
            const acceptBtn = document.createElement('button');
            acceptBtn.className = 'btn btn-sm btn-success mt-1';
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
                        statusDiv.innerHTML = `<div>🎉 최종 합격 • 수락 완료</div>`;
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

        row.innerHTML = `
            <div class="flex-grow-1" style="cursor:pointer;" onclick="location.href='/recruit_notice/${item.RECRUITMENTNO}'">
                <h5 class="fw-bold mb-1">${item.RECRUITMENTTITLE || '(제목 없음)'}</h5>
                <div class="text-muted small">
                    <strong>지역:</strong> ${item.cityCodeName || '-'} ${item.districtCodeName || '-'} |
                    <strong>직무:</strong> ${item.jobCodeName || '-'} |
                    <strong>경력:</strong> ${item.yearCodeName || '-'} |
                    <strong>급여:</strong> ${item.SALARY || '-'}<br/>
                    <strong>등록일:</strong> ${item.RECRUITMENTSTARTDATE?.substring(0, 10) || '-'} |
                    <strong>마감일:</strong> ${item.RECRUITMENTFINISHDATE?.substring(0, 10) || '-'}
                </div>
            </div>
        `;

        row.appendChild(statusDiv);
        listContainer.appendChild(row);
    });
}

// 이벤트 연결
searchInput.addEventListener('input', filterAndSearchData);
filterSelect.addEventListener('change', filterAndSearchData);
sortSelect.addEventListener('change', filterAndSearchData);

// 초기 실행
loadAndRenderApplications();