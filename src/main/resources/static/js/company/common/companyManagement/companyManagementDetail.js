document.addEventListener('DOMContentLoaded', () => {
  const editBtnEl = document.getElementById('edit-btn'); // 수정 버튼
  const companyForm = document.getElementById('companyForm'); // 수정 폼
  const saveBtnEl = document.getElementById('saveBtn'); // 저장 버튼
  const cancelBtnEl = document.getElementById('cancelBtn'); // 취소 버튼
  const companyDataWrap = document.querySelector('.company-info-wrap'); // 기존 데이터 영역

  // DOM 요소들을 전역 변수로 저장
  const comNameEl = document.getElementById('com_name');
  const comInfoEl = document.getElementById('com_info');
  const comNumEl = document.getElementById('com_num');
  const comEmailEl = document.getElementById('com_email');
  const comUrlEl = document.getElementById('com_url');
  const comCreateYearEl = document.getElementById('com_create_year');
  const comMemEl = document.getElementById('com_mem');
  const comPaymentEl = document.getElementById('com_payment');
  const industryTypeEl = document.getElementById('industry_type');

  const companyFields = {
    name: comNameEl,
    info: comInfoEl,
    phone: comNumEl,
    email: comEmailEl,
    url: comUrlEl,
    year: comCreateYearEl,
    mem: comMemEl,
    payment: comPaymentEl,
    industry: industryTypeEl
  };

  // 회사 데이터 로드
  axios.get('/ajax/company/company_management').then((resp) => {
    const company = resp.data;
    console.log('체킁', company);

    // 데이터를 전역 변수로 설정한 DOM 요소에 채우기
    companyFields.name.textContent = company.comName || '데이터 없음';
    companyFields.info.textContent = company.comInfo || '데이터 없음';
    companyFields.phone.textContent = company.comNum || '데이터 없음';
    companyFields.email.textContent = company.comEmail || '데이터 없음';
    companyFields.url.innerHTML = company.comUrl || 'URL 없음';
    companyFields.year.textContent = company.comCreateYear || '데이터 없음';
    companyFields.mem.textContent = company.comMem || '데이터 없음';
    companyFields.payment.textContent = company.comPayment ? '구독 중' : '구독 안 함';
    companyFields.industry.textContent = company.industryType || '데이터 없음';
  }).catch(err => {
    console.error('에러체킁', err);
  });

  // 수정 버튼 클릭 시 수정 폼 활성화
  editBtnEl.addEventListener('click', () => {
    // 기존 데이터 수정 폼에 채우기 (수정 불가능한 필드는 disabled)
    document.getElementById('company_name').value = companyFields.name.textContent;
    document.getElementById('company_info').value = companyFields.info.textContent;
    document.getElementById('company_phone').value = companyFields.phone.textContent;
    document.getElementById('company_email').value = companyFields.email.textContent;
    document.getElementById('company_url').value = companyFields.url.textContent;
    document.getElementById('company_year').value = companyFields.year.textContent;
    document.getElementById('company_employee_count').value = companyFields.mem.textContent;
    document.getElementById('industry_code').value = companyFields.industry.textContent;

    // 기업명, 설립년도, 구독여부는 수정 불가능하게 설정
    document.getElementById('company_name').disabled = true;
    document.getElementById('company_year').disabled = true;
    document.getElementById('company_payment').disabled = true;

    // 수정 폼 활성화 및 기존 정보 숨기기
    companyDataWrap.style.display = 'none'; // 기존 정보 영역 숨기기
    companyForm.style.display = 'block'; // 수정 폼 표시
    saveBtnEl.style.display = 'inline-block'; // 저장 버튼 표시
    cancelBtnEl.style.display = 'inline-block'; // 취소 버튼 표시
    editBtnEl.style.display = 'none'; // 수정 버튼 숨기기
  });

  // 취소 버튼 클릭 시 수정 폼 숨기기
  cancelBtnEl.addEventListener('click', () => {
    companyForm.style.display = 'none'; // 수정 폼 숨기기
    companyDataWrap.style.display = 'block'; // 기존 정보 영역 다시 표시
    editBtnEl.style.display = 'inline-block'; // 수정 버튼 다시 표시
  });

  // 저장 버튼 클릭 시 수정된 데이터 저장
  saveBtnEl.addEventListener('click', (e) => {
    e.preventDefault(); // 폼 제출을 막음

    const updatedCompany = {
      comName: document.getElementById('company_name').value, // 기업명은 수정 불가능
      comInfo: document.getElementById('company_info').value,
      comNum: document.getElementById('company_phone').value,
      comEmail: document.getElementById('company_email').value,
      comUrl: document.getElementById('company_url').value,
      comCreateYear: document.getElementById('company_year').value, // 설립년도는 수정 불가능
      comMem: document.getElementById('company_employee_count').value,
      industryType: document.getElementById('industry_code').value
    };

    // 서버로 수정된 데이터 전송
    axios.post('/ajax/company/company_management/edit', updatedCompany)
      .then(response => {
        console.log(response);
        alert('기업 정보가 수정되었습니다.');

        // 수정된 값 화면에 반영
        companyFields.name.textContent = updatedCompany.comName;
        companyFields.info.textContent = updatedCompany.comInfo;
        companyFields.phone.textContent = updatedCompany.comNum;
        companyFields.email.textContent = updatedCompany.comEmail;
        companyFields.url.textContent = updatedCompany.comUrl;
        companyFields.year.textContent = updatedCompany.comCreateYear;
        companyFields.mem.textContent = updatedCompany.comMem;
        companyFields.industry.textContent = updatedCompany.industryType;

        // 수정 폼 숨기기
        companyForm.style.display = 'none';
        companyDataWrap.style.display = 'block'; // 기존 정보 영역 다시 표시
        editBtnEl.style.display = 'inline-block'; // 수정 버튼 다시 표시
      })
      .catch(error => {
        console.error('수정 실패', error);
        alert('수정에 실패했습니다.');
      });
  });
});
