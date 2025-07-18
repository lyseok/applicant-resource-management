document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('companyForm');
  const cancelBtnEl = document.getElementById('cancelBtn');

  const industrySelect = document.getElementById('industryType');
  const comTypeSelect = document.getElementById('comType');
  const comSizeSelect = document.getElementById('comSize');
  const insuranceSelect = document.getElementById('insuranceYn');

  // ✅ 서버 값과 정확히 매칭되지 않더라도 select에서 찾도록 설정
  function setSelectValue(selectEl, value) {
    const matchedOption = [...selectEl.options].find(
      opt => opt.value == value || opt.textContent.trim() == value
    );
    if (matchedOption) {
      selectEl.value = matchedOption.value;
    }
  }

  // ✅ select 옵션 불러오기
  async function loadSelectOptions() {
    const [induRes, typeRes, sizeRes] = await Promise.all([
      axios.get('/ajax/code/indu'),
      axios.get('/ajax/code/cmncodegroup/COMT'),
      axios.get('/ajax/code/cmncodegroup/SIZE'),
    ]);

    // 업종
    induRes.data.forEach(item => {
      const option = document.createElement('option');
      option.value = item.induNo;
      option.textContent = item.induName;
      industrySelect.appendChild(option);
    });

    // 기업 형태
    typeRes.data.cmnCodeList.forEach(item => {
      const option = document.createElement('option');
      option.value = item.codeDetailNo;
      option.textContent = item.codeName;
      comTypeSelect.appendChild(option);
    });

    // 기업 규모
    sizeRes.data.cmnCodeList.forEach(item => {
      const option = document.createElement('option');
      option.value = item.codeDetailNo;
      option.textContent = item.codeName;
      comSizeSelect.appendChild(option);
    });
  }

  // ✅ 회사 데이터 불러오기 + 폼 세팅
  async function loadCompanyDataAndApply() {
    const { data: company } = await axios.get('/ajax/company/company_management');

    // input, textarea 채우기
    const textFields = [
      'comName', 'comCreateYear', 'comInfo', 'comNum', 'comEmail', 'comUrl',
      'comMem', 'comPayment', 'ceoName', 'comAddr', 'insuranceYn' , 'comCapital', 'comMainBiz'
    ];
    textFields.forEach(id => {
      const el = document.getElementById(id);
      if (el) el.value = company[id] || '';
    });

    // select 값 설정
    setSelectValue(industrySelect, company.industryType);
    setSelectValue(comTypeSelect, company.comType);
    setSelectValue(comSizeSelect, company.comSize);
    setSelectValue(insuranceSelect, company.insuranceYn);
  }

  // ✅ 실행 순서 보장: 옵션 먼저 → 값 세팅
  (async () => {
    try {
      await loadSelectOptions();
      await loadCompanyDataAndApply();
    } catch (err) {
      console.error('데이터 로딩 오류:', err);
    }
  })();

  // 취소 버튼 → 이전 페이지로
  cancelBtnEl.addEventListener('click', () => history.back());

  // form 데이터 변환
  function formToJSON(form) {
    return Array.from(new FormData(form).entries()).reduce((obj, [k, v]) => {
      obj[k] = ['comMem', 'comCapital'].includes(k) ? parseInt(v, 10) || null : v;
      return obj;
    }, {});
  }

  // 저장 버튼
  form.addEventListener('submit', e => {
    e.preventDefault();
    document.querySelectorAll('.text-danger').forEach(s => s.textContent = '');

    const payload = formToJSON(form);
    

    axios.put('/ajax/company/company_management/edit', payload)
      .then(() => window.location.href = '/company/company_management')
      .catch(err => {
        if (err.response?.status === 400 && err.response.data) {
          Object.entries(err.response.data).forEach(([field, messages]) => {
            const el = document.getElementById(field);
            if (!el) return;

            const nxt = el.nextElementSibling;
            if (nxt && nxt.classList.contains('text-danger')) nxt.remove();

            const span = document.createElement('span');
            span.className = 'text-danger small';
            span.textContent = Array.isArray(messages) ? messages.join(', ') : messages;
            el.insertAdjacentElement('afterend', span);
          });
        }
      });
  });
  
});
