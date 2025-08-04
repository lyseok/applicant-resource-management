document.addEventListener("DOMContentLoaded", () => {
    const companyListEl = document.getElementById('company-list');
    const companyTitleEl = document.getElementById('company-title');
    const formBtnEl = document.getElementById('review_form_btn');
    const sortSelectEl = document.getElementById('sortSelect'); // 정렬 select
    const pageBoxEl = document.querySelector('.PageBox');
    const keywordInputEl = document.getElementById('listKeyword');
    const searchBtnEl = document.querySelector('.searchBarBtn');

    // 경력 선택 관련
    let selectedCareer = null;
    const careerListDiv = document.getElementById('careerList');
    const selectedCareerCard = document.getElementById('selectedCareerCard');
    const btnShowCareerList = document.getElementById('btnShowResumeList');

    // 요청 파라미터
    const params = {
        page: 1,
        pageSize: 6,
        sort: 'name', // 기본값
        keyword: '',
        industry: ''
    };

    let totalPage = 1;

    // 기업 리뷰 리스트 불러오기
    function fetchCompanyList() {
        axios.get('/ajax/member/company_review', { params })
            .then(resp => {
                const { list, total } = resp.data;

                // 타이틀 수정
                companyTitleEl.textContent = `${total}개 기업의 리뷰가 등록되어 있습니다.`;

                // 리스트 렌더링
                renderCompanyList(list);

                // 총 페이지 계산
                totalPage = Math.ceil(total / params.pageSize);
                renderPager(totalPage, params.page);
            })
            .catch(err => console.error(err));
    }

    // 리스트 렌더링
    function renderCompanyList(data) {
        companyListEl.innerHTML = data.map(c => {
            const isRecruiting = c.IS_RECRUITING === 'Y' ? '채용 중' : '채용 정보 없음';
            return `
                <li class="item" onclick="location.href='/member/company_review/detail?company=${c.USER_ID}'">
                    <div class="info">
                      ${c.COM_LOGO
                        ? `<img class="logo" src="${c.COM_LOGO}" alt="${c.COM_NAME}"/>`
                        : `<img class="logo opacity-25" src="/dist/assets/images/favicon.png" alt="${c.COM_NAME}"/>`}
                      <div class="text">
                        <div class="name mb-1">
                            ${c.COM_NAME}
                            <span class="text-violet90 fs-14">${isRecruiting}</span>
                        </div>
                        <p class="fw-500 text-muted fs-14 mb-3">
                            총 <b>${c.TOTAL_REVIEW_COUNT}</b> 개의 리뷰가 등록되어 있습니다.
                        </p>
                        <div class="d-flex gap-3 fs-13">
                            <p class="d-flex gap-2 fw-500 text-muted">
                                <b class="text-dark">업종</b>${c.INDU_NAME ? `<span>${c.INDU_NAME}</span>` : ''}
                            </p>
                            <p class="d-flex gap-2 fw-500 text-muted">
                                <b class="text-dark">사원수</b>${c.COM_MEM ? `<span>${c.COM_MEM} 명</span>` : `0 명`}
                            </p>
                        </div>
                        <div class="d-flex gap-3 fs-13">
                            <p class="d-flex gap-2 fw-500 text-muted">
                                <b class="text-dark">평균점수</b>
                                <span class="">${c.OVERALL_AVG_SCORE || 0}</span>
                            </p>
                            <p class="d-flex gap-2 fw-500 text-muted">
                                <b class="text-dark">답변인원</b>
                                <span class="">${c.REVIEW_USER_COUNT}</span>
                            </p>
                        </div>
                      </div>
                    </div>
                </li>
            `;
        }).join('');
    }

    // 페이지네이션 렌더링
    function renderPager(totalPages, page) {
        let pagerHtml = '';
        for (let i = 1; i <= totalPages; i++) {
            pagerHtml += (i === page)
                ? `<span class="BtnType SizeS active">${i}</span>`
                : `<button class="BtnType SizeS page" data-page="${i}">${i}</button>`;
        }
        if (page < totalPages) {
            pagerHtml += `<button data-page="${page + 1}" class="BtnType SizeS BtnNext btnNext">다음</button>`;
        }
        pageBoxEl.innerHTML = pagerHtml;
    }

    // 이벤트: 페이지네이션 클릭
    pageBoxEl.addEventListener('click', e => {
        if (e.target.classList.contains('page')) {
            params.page = Number(e.target.dataset.page);
            fetchCompanyList();
        } else if (e.target.classList.contains('BtnNext')) {
            params.page += 1;
            fetchCompanyList();
        }
    });

    // 이벤트: 정렬 변경
    sortSelectEl.addEventListener('change', function () {
        params.sort = this.value;
        params.page = 1;
        fetchCompanyList();
    });

    // 이벤트: 검색
    searchBtnEl.addEventListener('click', () => {
        params.keyword = keywordInputEl.value.trim();
        params.page = 1;
        fetchCompanyList();
    });

    // **리뷰 등록 모달 열기**
    formBtnEl.addEventListener('click', () =>{
       const modal = new bootstrap.Modal(document.getElementById('careerModal'));
       modal.show();
    });

    // **경력 선택/변경 버튼**
    btnShowCareerList.addEventListener('click', () => {
        careerListDiv.style.display = 'block';
    });

    // **리뷰 등록 저장 버튼**
    document.getElementById('btnSaveCareer').addEventListener('click', () => {
        if (!selectedCareer) {
            alert("경력을 선택해주세요!");
            return;
        }
        location.href = `/member/company_review/form?careerNo=${selectedCareer.careerNo}`;
    });

    // 경력 리스트 불러오기
    axios.get('/ajax/member/company_review/my_career')
         .then(resp => {
            const resumes = resp.data;
            console.log(resumes);
            const careers = [];
            resumes.forEach(resume => {
                resume.careerList?.forEach(career => {
                   if (career.careerNo && (career.company?.comName || career.jobCodeName)) {
                    career.resumeName = resume.resumeName;
                    careers.push(career);
                }
                });
            });
            if(careers.length === 0){
                careerListDiv.innerHTML = `<div class="text-secondary py-2">등록된 경력이 없습니다.</div>`
                careerListDiv.style.display = 'block';
                return;
            }

            careerListDiv.innerHTML = careers.map(career => `
                <div class="card mb-2 resume-card ${selectedCareer?.careerNo === career.careerNo ? 'selected-card' : ''}" data-career-no="${career.careerNo}">
                   <div class="card-body p-3">
                    <h6 class ="mb-1 fw-500 fs-16">
                        ${career.company.comName} (${career.jobCodeName || ''})
                        <small class="ms-2 text-muted">${career.careerYearName || ''}</small>
                    </h6>
                      <p class="text-secondary fs-13 fw-500 lh1-4">
                        ${career.startWorkDate || ''} ~ ${career.retireDate || '재직중'}<br>
                        ${career.jobGradeCodeName || ''} ${career.positionCodeName ? '/' + career.positionCodeName : ''}
                      </p>
                  </div>
                </div>
            `).join('');

            careerListDiv.querySelectorAll('.resume-card').forEach(card => {
                card.onclick = function () {
                    const careerNo = this.getAttribute('data-career-no');
                    selectedCareer = careers.find(c => c.careerNo === careerNo);
                    renderSelectedCareerCard();
                    selectedCareerCard.style.display = 'block';
                    careerListDiv.style.display = 'none';
                }
            });
         });

    // 선택된 경력 카드 렌더링
    function renderSelectedCareerCard(){
        const div =  document.getElementById('selectedCareerCard');
        if(!selectedCareer){
            div.innerHTML = '<span class="text-secondary">경력을 선택하세요</span>';
            return;
        }
        div.innerHTML = `
            <div class="card mb-0 selected-card pointer">
                <div class="card-body p-3">
                    <h6 class="mb-1 fw-500 fs-16">
                        ${selectedCareer.company?.comName || ''} (${selectedCareer.jobCodeName || ''})
                        <small class="ms-2 text-muted">${selectedCareer.careerYearName || ''}</small>
                    </h6>
                    <p class="text-secondary fs-13 fw-500 lh1-4">
                        ${selectedCareer.startWorkDate || ''} ~ ${selectedCareer.retireDate || '재직중'}<br>
                        ${selectedCareer.jobGradeCodeName || ''} ${selectedCareer.positionCodeName ? '/' + selectedCareer.positionCodeName :  ''}
                    </p>
                </div>
            </div>
        `;
    }

    // 초기 데이터 로딩
    fetchCompanyList();
});
