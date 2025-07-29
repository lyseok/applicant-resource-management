document
  .querySelector('thead .checkbox')
  .addEventListener('click', function () {
    const isChecked = this.checked;
    document.querySelectorAll('tbody .checkbox').forEach((checkbox) => {
      checkbox.checked = isChecked;
    });
  });

function renderTalentPoolTable(talentList) {
  const tbody = document.querySelector('table tbody');
  tbody.innerHTML = ''; // 기존 행 삭제

  talentList.forEach((item) => {
    // 회원
    const userId = item.userId || '';
    const userName = item.userName || '';
    const email = item.email || '';
    const tel = item.tel || '';

    // 최종 학력
    let edu = { schoolName: '', highestEducationCodeName: '', major: '' };
    if (item.education) {
      const eduObj = item.education;

      edu = {
        schoolName: eduObj.schoolName || '',
        highestEducationCodeName: eduObj.highestEducationCodeName || '',
        major: eduObj.departmentCode || '',
      };
    }

    // 어학 (최고 점수 기준 하나만, 예: TOEIC)
    let lang = { name: '', score: '' };
    if (item.languageSkillList && item.languageSkillList.length) {
      // 점수가 숫자이면 내림차순 정렬
      lang =
        item.languageSkillList
          .map((l) => ({
            name: l.languageExamName,
            score: Number(l.languageExamScore || 0),
          }))
          .sort((a, b) => b.score - a.score)[0] || lang;
    }

    // 최근 경력 (입사일 기준 가장 최근)
    let lastCareer = { company: '', job: '' };
    if (item.careerList && item.careerList.length) {
      lastCareer = item.careerList.sort((a, b) =>
        (b.startWorkDate || '').localeCompare(a.startWorkDate || '')
      )[0];
    }

    // 총 경력 (year 합계 또는 max)
    let totalYear = 0;
    if (item.careerList && item.careerList.length) {
      totalYear = item.careerList.reduce((sum, cur) => {
        const year = parseInt(
          cur.careerYearName?.replace(/[^0-9]/g, '') || '0',
          10
        );
        return sum + year;
      }, 0);
    }

    // 자격증
    const licenses = (item.myLicenseList || [])
      .map((l) => l.licenseCode)
      .filter(Boolean);

    // 보유기술
    const skills = (item.mySkillList || [])
      .map((s) => s.mySkillName)
      .filter(Boolean);

    // 테이블 row 만들기
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td><input type="checkbox" class="checkbox" data-user-id="${userId}" data-email="${email}" data-name="${userName}"></td>
      <td>
        <div class="candidate-info">
          <div class="candidate-name">${userName}</div>
          <div class="candidate-email">${email}</div>
        </div>
      </td>
      <td>
        <div class="experience-status">
          <div class="status-text resume-btn">확인</div>
        </div>
      </td>
      <td>
        <div class="data-info">
          <div class="data-name">${edu.schoolName}</div>
          <div class="data-sub">${edu.major || '-'}</div>
        </div>
      </td>
      <td>
        <div class="data-info">
          <div class="data-name">${lang.name || '-'}</div>
          <div class="data-sub">${lang.score}</div>
        </div>
      </td>
      <td>${tel}</td>
      <td>
        <div class="data-info">
          <div class="data-name">${lastCareer?.comName || '-'}</div>
          <div class="data-sub">
            ${lastCareer?.jobCodeName || ''} 
            ${lastCareer?.careerYearName || ''}
          </div>
        </div>
      </td>
      <td>
        <div class="my-list">
          ${licenses.map((l) => `<span class="skill-tag">${l}</span>`).join('')}
        </div>
      </td>
      <td>
        <div class="my-list">
          ${skills.map((s) => `<span class="skill-tag">${s}</span>`).join('')}
        </div>
      </td>
    `;
    tbody.appendChild(tr);
    tr.querySelector('.resume-btn').addEventListener('click', function () {
      window.open(
        '/popup/resume/' + item.resumeNo,
        'resumePopup',
        'width=950,height=800'
      );
    });
  });
}
{
  /* <td>${lastCareer ? totalYear + '년' : '-'}</td>; */
}
const params = {
  page: 1,
  pageSize: 10,
};

function fetchData() {
  showLoading();
  axios
    .get('/ajax/company/talentpool/company-scrab', { params })
    .then((res) => {
      const resp = res.data;
      renderTalentPoolTable(resp.data);

      totalPage = Math.ceil(resp.totalCnt / params.pageSize);
      console.log(totalPage, params.page);
      renderPager(totalPage, params.page);
    })
    .catch((err) => {
      console.error('데이터 로드 실패:', err);
      alert('데이터를 불러오는 데 실패했습니다.');
    })
    .finally(() => {
      hideLoading();
    });
}

let mailTemplates = [];
axios.get('/ajax/company/mail/list').then((res) => {
  if (Array.isArray(res.data)) {
    mailTemplates = res.data;
  }
});

// 로딩 show/hide 함수
function showLoading() {
  document
    .getElementById('loadingSpinner')
    .style.setProperty('display', 'flex', 'important');
}
function hideLoading() {
  document
    .getElementById('loadingSpinner')
    .style.setProperty('display', 'none', 'important');
}

function renderPager(totalPages, page) {
  let pagerHtml = '';
  for (let i = 1; i <= totalPages; i++) {
    if (i === page) {
      pagerHtml += `<span class="BtnType SizeS active">${i}</span>`;
    } else {
      pagerHtml += `<button class="BtnType SizeS page" data-page="${i}">${i}</button>`;
    }
  }
  if (page < totalPages) {
    pagerHtml += `<button data-page="${
      page + 1
    }" class="BtnType SizeS BtnNext btnNext">다음</button>`;
  }
  document.querySelector('.PageBox').innerHTML = pagerHtml;
}

// 페이저 클릭 (이벤트 위임)
document.querySelector('.PageBox').addEventListener('click', function (e) {
  if (e.target.classList.contains('page')) {
    const page = Number(e.target.dataset.page);
    params.page = page;
    fetchData(params.page, params.pageSize);
  } else if (e.target.classList.contains('BtnNext')) {
    params.page += 1;
    fetchData(params.page, params.pageSize);
  }
});

let jobSuggestions = [];
axios.get('/ajax/code/job').then((res) => {
  jobSuggestions = Array.isArray(res.data) ? res.data : [];
});

document.getElementById('job-offer').addEventListener('click', function () {
  // 체크된 사용자 목록 추출
  const checkedUsers = Array.from(document.querySelectorAll('tbody .checkbox'))
    .filter((checkbox) => checkbox.checked)
    .map((checkbox) => ({
      userId: checkbox.dataset.userId,
      email: checkbox.dataset.email,
      name: checkbox.dataset.name,
    }));

  if (checkedUsers.length === 0) {
    alert('입사 제안을 보낼 회원을 선택해주세요.');
    return;
  }
  console.log('입사 제안 대상:', checkedUsers);

  // 모달 DOM 없으면 추가
  if (!document.getElementById('jobOfferModal')) {
    const modalHTML = `
      <div class="modal fade" id="jobOfferModal" tabindex="-1" aria-labelledby="jobOfferModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="jobOfferModalLabel">입사 제안 보내기</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
            </div>
            <div class="modal-body">
              <form id="jobOfferForm">
                <table class="table">
                  <thead>
                    <tr>
                      <th>이름</th>
                      <th>이메일</th>
                      <th>직무 선택</th>
                      <th>메일 템플릿</th>
                    </tr>
                  </thead>
                  <tbody id="jobOfferTableBody">
                    <!-- 동적 생성 -->
                  </tbody>
                </table>
              </form>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn_gray_line" data-bs-dismiss="modal">취소</button>
              <button type="button" class="btn btn_violet_line" id="sendJobOffers">메일 발송</button>
            </div>
          </div>
        </div>
      </div>
    `;
    document.body.insertAdjacentHTML('beforeend', modalHTML);
  }

  // tbody 채우기
  const tbody = document.getElementById('jobOfferTableBody');
  tbody.innerHTML = '';
  checkedUsers.forEach((user, idx) => {
    const row = document.createElement('tr');
    row.innerHTML = `
      <td>${user.name}</td>
      <td>
        <input type="hidden" name="userId" value="${user.email}">
        ${user.email}
      </td>
      <td style="position: relative;">
        <input type="text" class="form-control job-input" name="jobInput" placeholder="직무 입력" autocomplete="off">
        <div class="job-suggestion-box list-group" style="position:absolute;top:100%;left:0;width:100%;z-index:1000;display:none;"></div>
      </td>
      <td>
        <select class="form-select template-select" name="template">
          <option value="">템플릿 선택</option>
          ${mailTemplates
            .map(
              (template) =>
                `<option value="${template.temNo}">${template.temTitle}</option>`
            )
            .join('')}
        </select>
      </td>
    `;
    tbody.appendChild(row);

    setTimeout(() => {
      const input = row.querySelector('.job-input');
      const suggestionBox = row.querySelector('.job-suggestion-box');

      input.addEventListener('input', () => {
        const value = input.value.trim().toLowerCase();
        if (!value) {
          suggestionBox.style.display = 'none';
          return;
        }

        const matches = jobSuggestions
          .filter((j) => j.jobName && j.jobName.toLowerCase().includes(value))
          .slice(0, 5);

        if (matches.length === 0) {
          suggestionBox.style.display = 'none';
          return;
        }

        suggestionBox.innerHTML = matches
          .map(
            (j) =>
              `<button type="button" class="list-group-item list-group-item-action">${j.jobName}</button>`
          )
          .join('');
        suggestionBox.style.display = 'block';
      });

      suggestionBox.addEventListener('click', (e) => {
        if (e.target.matches('.list-group-item')) {
          input.value = e.target.textContent;
          suggestionBox.style.display = 'none';
        }
      });

      document.addEventListener('click', (e) => {
        if (!suggestionBox.contains(e.target) && e.target !== input) {
          suggestionBox.style.display = 'none';
        }
      });
    }, 100);
  });

  const modal = new bootstrap.Modal(document.getElementById('jobOfferModal'));
  modal.show();

  // 발송 버튼 처리
  document.getElementById('sendJobOffers').onclick = () => {
    const rows = document.querySelectorAll('#jobOfferTableBody tr');
    const payload = [];

    rows.forEach((row) => {
      const userId = row.querySelector('input[name="userId"]').value;
      const job = row.querySelector('input.job-input').value;
      const template = row.querySelector('select[name="template"]').value;

      payload.push({ userId, job, template });
    });

    console.log('입사 제안 데이터:', payload);
    axios
      .post('/ajax/company/talentpool/joboffer', payload)
      .then(() => {
        alert('입사 제안 메일이 발송되었습니다.');
        // eslint-disable-next-line no-undef
        bootstrap.Modal.getInstance(
          document.getElementById('jobOfferModal')
        ).hide();
      })
      .catch(() => {
        alert('메일 발송 중 오류가 발생했습니다.');
      });
  };
});

// init
fetchData();
