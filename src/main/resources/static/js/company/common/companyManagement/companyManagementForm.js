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

  
  // 로고 파일 선택 시 S3 업로드 후 미리보기
logoInput.addEventListener('change', e => {
  const file = e.target.files[0];
  if (!file) return;

  logoFile = file;

  const reader = new FileReader();
  reader.onload = e => {
    logoPreview.src = e.target.result;
    logoPreview.style.display = 'block';
  };
  reader.readAsDataURL(file);
});

backInput.addEventListener('change', e => {
  const file = e.target.files[0];
  if (!file) return;

  backFile = file;

  const reader = new FileReader();
  reader.onload = e => {
    backPreview.src = e.target.result;
    backPreview.style.display = 'block';
  };
  reader.readAsDataURL(file);
});
  
  
extraImagesInput.addEventListener('change', e => {
  const files = Array.from(e.target.files);
  if (!files.length) return;

  for (const file of files) {
    extraFiles.push(file); // 추가

    const reader = new FileReader();
    reader.onload = e => {
      const wrapper = document.createElement('div');
      wrapper.style.position = 'relative';

      const img = document.createElement('img');
      img.src = e.target.result;
      img.style.height = '80px';
      img.style.border = '1px solid #ccc';
      img.style.borderRadius = '6px';

      const delBtn = document.createElement('button');
      delBtn.textContent = '❌';
      Object.assign(delBtn.style, {
        position: 'absolute', top: '0', right: '0', background: 'rgba(0,0,0,0.6)',
        color: 'white', border: 'none', cursor: 'pointer',
        borderRadius: '50%', width: '20px', height: '20px', fontSize: '12px'
      });

      delBtn.addEventListener('click', () => {
        const idx = extraImagePreviewContainer.childNodes.length;
        extraFiles.splice(idx, 1);
        wrapper.remove();
      });

      wrapper.appendChild(img);
      wrapper.appendChild(delBtn);
      extraImagePreviewContainer.appendChild(wrapper);
    };
    reader.readAsDataURL(file);
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
    
    if (company.comLogo) {
      logoPreview.src = company.comLogo;
      logoPreview.style.display = 'block';
      logoUrlInput.value = company.comLogo; // 기존 로고 URL 저장
    }
    
    if (company.comBackgroundImg) {
      backPreview.src = company.comBackgroundImg;
      backPreview.style.display = 'block';
      backUrlInput.value = company.comBackgroundImg;
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
    
    const fileList = [];
	let logoPath = null;
	let backPath = null;
	if(logoFile){
		const formData = new FormData();
		formData.append('file', logoFile);
		const res = await axios.post('/upload/company/editor', formData, {
			headers: {'Content-Type' : 'multipart/form-data'}
		});
		logoPath = res.data.url;
		fileList.push({filePath: res.data.url})
	} else {
      // 기존 로고 유지
      const oldLogo = logoUrlInput.value;
      if (oldLogo) {
        logoPath = oldLogo;
        fileList.push({ filePath: oldLogo });
      }
    }
    
     if (backFile) {
      const fd = new FormData();
      fd.append('file', backFile);
      const res = await axios.post('/upload/company/editor', fd, {
        headers: { 'Content-Type': 'multipart/form-data' }
      });
      backPath = res.data.url;
    } else {
      const oldBack = backUrlInput.value;
      if (oldBack) backPath = oldBack;
    }
	
	 for (const file of extraFiles) {
	    const fd = new FormData();
	    fd.append('file', file);
	    const res = await axios.post('/upload/company/editor', fd, {
	      headers: { 'Content-Type': 'multipart/form-data' }
	    });
	    fileList.push({ filePath: res.data.url });
	  }
    
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
	  comLogo:        logoPath,
	  comBackgroundImg: backPath,
      fileList
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
