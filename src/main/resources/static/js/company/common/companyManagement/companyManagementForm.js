document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('companyForm');
  const cancelBtnEl = document.getElementById('cancelBtn');

  const industrySelect = document.getElementById('industryType');
  const comTypeSelect   = document.getElementById('comType');
  const comSizeSelect   = document.getElementById('comSize');
  const insuranceSelect = document.getElementById('insuranceYn');
  const logoInput    = document.getElementById('logoInput');
  const logoPreview  = document.getElementById('logoPreview');
  const logoUrlInput = document.getElementById('logoUrl');
  
  // 로고 파일 선택 시 S3 업로드 후 미리보기
  logoInput.addEventListener('change', async e => {
    const file = e.target.files[0];
    if (!file) return;
    const fd = new FormData();
    fd.append('file', file);
    try {
      const res = await axios.post(
        '/upload/company/editor',
        fd,
        { headers: { 'Content-Type': 'multipart/form-data' } }
      );
      logoUrl = res.data.url;
      logoPreview.src = logoUrl;
      logoPreview.style.display = 'block';
      logoUrlInput.value = logoUrl;
    } catch {
      alert('로고 업로드 실패');
    }
  });

  // 서버 값과 정확히 매칭되지 않더라도 select에서 찾도록 설정
  function setSelectValue(selectEl, value) {
    const matched = [...selectEl.options].find(
      opt => opt.value == value || opt.textContent.trim() == value
    );
    if (matched) selectEl.value = matched.value;
  }

  // select 옵션 불러오기
  async function loadSelectOptions() {
    const [induRes, typeRes, sizeRes] = await Promise.all([
      axios.get('/ajax/code/indu'),
      axios.get('/ajax/code/cmncodegroup/COMT'),
      axios.get('/ajax/code/cmncodegroup/SIZE'),
    ]);
    induRes.data.forEach(i => {
      const o = new Option(i.induName, i.induNo);
      industrySelect.appendChild(o);
    });
    typeRes.data.cmnCodeList.forEach(i => {
      const o = new Option(i.codeName, i.codeDetailNo);
      comTypeSelect.appendChild(o);
    });
    sizeRes.data.cmnCodeList.forEach(i => {
      const o = new Option(i.codeName, i.codeDetailNo);
      comSizeSelect.appendChild(o);
    });
  }

  // 회사 데이터 불러오기 + 폼 세팅
  async function loadCompanyDataAndApply() {
    const { data: company } = await axios.get('/ajax/company/company_management'); 
    [
      'comName','comCreateYear','comInfo','comNum','comEmail',
      'comUrl','comMem','comPayment','ceoName','comAddr',
      'insuranceYn','comCapital','comMainBiz'
    ].forEach(id => {
      const el = document.getElementById(id);
      if (el) el.value = company[id] ?? '';
    });
    setSelectValue(industrySelect, company.industryType);
    setSelectValue(comTypeSelect,   company.comType);
    setSelectValue(comSizeSelect,   company.comSize);
    setSelectValue(insuranceSelect, company.insuranceYn);

    if (company.logoUrl) {
      logoUrl = company.logoUrl;
      logoPreview.src = logoUrl;
      logoPreview.style.display = 'block';
      logoUrlInput.value = logoUrl;
    }
  }

  // 초기 실행: 옵션 → 데이터
  (async () => {
    try {
      await loadSelectOptions();
      await loadCompanyDataAndApply();
    } catch (err) {
      console.error('데이터 로딩 오류:', err);
    }
  })();


 
  // 취소
  cancelBtnEl.addEventListener('click', () => history.back());

  // 저장 버튼
  form.addEventListener('submit', async e => {
    e.preventDefault();
   
    // 기존 에러 메시지 전부 제거
    document.querySelectorAll('.text-danger').forEach(el => el.remove());
   
    // FormData → payload 객체
    const formData = new FormData(form);
    const payload = {
      comName:        formData.get('comName'),
      comCreateYear:  formData.get('comCreateYear'),
      comInfo:        formData.get('comInfo'),
      comNum:         formData.get('comNum'),
      comEmail:       formData.get('comEmail'),
      comUrl:         formData.get('comUrl'),
      comMem:         formData.get('comMem') ? parseInt(formData.get('comMem'), 10) : null,
      comPayment:     formData.get('comPayment'),
      ceoName:        formData.get('ceoName'),
      comAddr:        formData.get('comAddr'),
      insuranceYn:    formData.get('insuranceYn'),
      comCapital:     formData.get('comCapital')  ? parseInt(formData.get('comCapital'), 10) : null,
      comMainBiz:     formData.get('comMainBiz'),
      industryType:   formData.get('industryType'),
      comType:        formData.get('comType'),
      comSize:        formData.get('comSize'),

      fileList: logoUrl ? [{ filePath: logoUrl }] : []
    };

    try {
      await axios.put('/ajax/company/company_management/edit', payload);
      window.location.href = '/company/company_management';
    } catch (err) {
      if (err.response?.status === 400 && err.response.data) {
        Object.entries(err.response.data).forEach(([field, messages]) => {
          const el = document.getElementById(field);
          if (!el) return;
          // 기존 에러가 있으면 제거
          const nxt = el.nextElementSibling;
          if (nxt?.classList.contains('text-danger')) nxt.remove();
          // 새 에러 span 생성
          const span = document.createElement('span');
          span.className = 'text-danger small';
          span.textContent = Array.isArray(messages)
            ? messages.join(', ')
            : messages;
          el.insertAdjacentElement('afterend', span);
        });
      }
    }
  });

});
