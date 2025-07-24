// /js/company/common/companyManagement/companyManagementForm.js

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

  const backInput    = document.getElementById('backInput');
  const backPreview  = document.getElementById('backPreview');
  const backUrlInput = document.getElementById('backUrl');

  const extraImagesInput = document.getElementById('extraImagesInput');
  const extraImagePreviewContainer = document.getElementById('extraImagePreviewContainer');

  let logoFile = null;
  let backFile = null;
  let extraFiles = [];

  // 파일 선택 시 preview & 파일 객체 저장
  logoInput.addEventListener('change', e => {
    const file = e.target.files[0];
    if (!file) return;
    logoFile = file;

    const reader = new FileReader();
    reader.onload = ev => {
      logoPreview.src = ev.target.result;
      logoPreview.style.display = 'block';
    };
    reader.readAsDataURL(file);
  });

  backInput.addEventListener('change', e => {
    const file = e.target.files[0];
    if (!file) return;
    backFile = file;

    const reader = new FileReader();
    reader.onload = ev => {
      backPreview.src = ev.target.result;
      backPreview.style.display = 'block';
    };
    reader.readAsDataURL(file);
  });

  extraImagesInput.addEventListener('change', e => {
    const files = Array.from(e.target.files);
    if (!files.length) return;

    files.forEach(file => {
      extraFiles.push(file);

      const reader = new FileReader();
      reader.onload = ev => {
        const wrapper = document.createElement('div');
        wrapper.style.position = 'relative';

        const img = document.createElement('img');
        img.src = ev.target.result;
        img.style.height = '80px';
        img.style.border = '1px solid #ccc';
        img.style.borderRadius = '6px';

        const delBtn = document.createElement('button');
        delBtn.textContent = '❌';
        Object.assign(delBtn.style, {
          position: 'absolute',
          top: '0',
          right: '0',
          background: 'rgba(0,0,0,0.6)',
          color: 'white',
          border: 'none',
          cursor: 'pointer',
          borderRadius: '50%',
          width: '20px',
          height: '20px',
          fontSize: '12px'
        });

        delBtn.addEventListener('click', () => {
          const idx = extraFiles.indexOf(file);
          if (idx > -1) extraFiles.splice(idx, 1);
          wrapper.remove();
        });

        wrapper.appendChild(img);
        wrapper.appendChild(delBtn);
        extraImagePreviewContainer.appendChild(wrapper);
      };
      reader.readAsDataURL(file);
    });
  });

  // select 옵션 로딩
  async function loadSelectOptions() {
    const [induRes, typeRes, sizeRes] = await Promise.all([
      axios.get('/ajax/code/indu'),
      axios.get('/ajax/code/cmncodegroup/COMT'),
      axios.get('/ajax/code/cmncodegroup/SIZE'),
    ]);

    induRes.data.forEach(i => {
      industrySelect.appendChild(new Option(i.induName, i.induNo));
    });
    typeRes.data.cmnCodeList.forEach(i => {
      comTypeSelect.appendChild(new Option(i.codeName, i.codeDetailNo));
    });
    sizeRes.data.cmnCodeList.forEach(i => {
      comSizeSelect.appendChild(new Option(i.codeName, i.codeDetailNo));
    });
  }

  // select 초기값 매핑
  function setSelectValue(selectEl, value) {
    const opt = Array.from(selectEl.options)
      .find(o => o.value === value || o.textContent.trim() === value);
    if (opt) selectEl.value = opt.value;
  }

  // 회사 데이터 로드 후 폼에 채우기
  async function loadCompanyDataAndApply() {
    const { data: company } = await axios.get('/ajax/company/company_management');

    // 텍스트/숨김 필드
    [
      'comName','comCreateYear','comInfo','comNum','comEmail',
      'comUrl','comMem','comPayment','ceoName','comAddr',
      'insuranceYn','comCapital','comMainBiz'
    ].forEach(id => {
      const el = document.getElementById(id);
      if (el) el.value = company[id] ?? '';
    });

    setSelectValue(industrySelect, company.industryType);
    setSelectValue(comTypeSelect, company.comType);
    setSelectValue(comSizeSelect, company.comSize);
    setSelectValue(insuranceSelect, company.insuranceYn);

    // 기존 이미지 URL 세팅 및 preview
    if (company.comLogo) {
      logoPreview.src = company.comLogo;
      logoPreview.style.display = 'block';
      logoUrlInput.value = company.comLogo;
    }
    if (company.comBackgroundImg) {
      backPreview.src = company.comBackgroundImg;
      backPreview.style.display = 'block';
      backUrlInput.value = company.comBackgroundImg;
    }
  }

  // 초기 실행
  (async () => {
    try {
      await loadSelectOptions();
      await loadCompanyDataAndApply();
    } catch (err) {
      console.error('데이터 로딩 오류:', err);
    }
  })();

  // 취소 버튼
  cancelBtnEl.addEventListener('click', () => history.back());

  // 저장 (submit) 핸들러
  form.addEventListener('submit', async e => {
    e.preventDefault();
    document.querySelectorAll('.text-danger').forEach(el => el.remove());

    // 1) 텍스트 + 기존 URL
    const payload = {
      comName:          form.elements.comName.value,
      comCreateYear:    form.elements.comCreateYear.value,
      comInfo:          form.elements.comInfo.value,
      comNum:           form.elements.comNum.value,
      comEmail:         form.elements.comEmail.value,
      comUrl:           form.elements.comUrl.value,
      comMem:           parseInt(form.elements.comMem.value, 10) || null,
      comPayment:       form.elements.comPayment.value,
      ceoName:          form.elements.ceoName.value,
      comAddr:          form.elements.comAddr.value,
      insuranceYn:      form.elements.insuranceYn.value,
      comCapital:       parseInt(form.elements.comCapital.value, 10) || null,
      comMainBiz:       form.elements.comMainBiz.value,
      industryType:     form.elements.industryType.value,
      comType:          form.elements.comType.value,
      comSize:          form.elements.comSize.value,
      comLogo:          form.elements.logoUrl.value,
      comBackgroundImg: form.elements.backUrl.value
    };

    // 2) FormData 구성
    const formData = new FormData();
    formData.append(
      'company',
      new Blob([JSON.stringify(payload)], { type: 'application/json' })
    );
    if (logoFile) formData.append('comLogoFile', logoFile);
    if (backFile) formData.append('comBackgroundImgFile', backFile);
    extraFiles.forEach(file => formData.append('extraFiles', file));

    // 3) PUT 요청
    try {
      await axios.post(
        '/ajax/company/company_management/edit',
        formData,
        { headers: { 'Content-Type': 'multipart/form-data' } }
      );
      window.location.href = '/company/company_management';
    } catch (err) {
      if (err.response?.status === 400 && err.response.data) {
        Object.entries(err.response.data).forEach(([field, messages]) => {
          const el = document.getElementById(field);
          if (!el) return;
          const span = document.createElement('span');
          span.className = 'text-danger small';
          span.textContent = Array.isArray(messages)
            ? messages.join(', ')
            : messages;
          el.insertAdjacentElement('afterend', span);
        });
      } else {
        console.error(err);
      }
    }
  });
});
