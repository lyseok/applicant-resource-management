/**
 *
 */
document.addEventListener('DOMContentLoaded', () => {
  const editBtnEl = document.getElementById('edit-btn');
  const companyForm = document.getElementById('companyForm');
  const saveBtnEl = document.getElementById('saveBtn');
  const cancelBtnEl = document.getElementById('cancelBtn');
  const companyDataWrap = document.querySelector('.company-info-wrap')

  axios.get('/ajax/company/company_management').then((resp) => {
    const company = resp.data;
    console.log('체킁', company);

    document.getElementById('com_name').textContent = company.comName || '데이터 없음';
    document.getElementById('com_info').textContent =company.comInfo || '데이터 없음';
    document.getElementById('com_num').textContent = company.comNum || '데이터 없음';
    document.getElementById('com_email').textContent = company.comEmail || '데이터 없음';
    document.getElementById('com_url').innerHTML =company.comUrl || 'URL 없음';
    document.getElementById('com_create_year').textContent = company.comCreateYear || '데이터 없음';
    document.getElementById('com_mem').textContent = company.comMem || '데이터 없음';
    document.getElementById('com_payment').textContent = company.comPayment ? '구독 중' : '구독 안 함';
    document.getElementById('industry_type').textContent = company.industryType || '데이터 없음';
    
  }).catch(err =>{
    console.error('에러체킁', err);
  })

  editBtnEl.addEventListener('click', () =>{
       // 기존 데이터 수정 폼에 채우기
    document.getElementById('company_name').value = document.getElementById('com_name').textContent;
    document.getElementById('company_info').value = document.getElementById('com_info').textContent;
    document.getElementById('company_phone').value = document.getElementById('com_num').textContent;
    document.getElementById('company_email').value = document.getElementById('com_email').textContent;
    document.getElementById('company_url').value = document.getElementById('com_url').textContent;
    document.getElementById('company_year').value = document.getElementById('com_create_year').textContent;
    document.getElementById('company_employee_count').value = document.getElementById('com_mem').textContent;
    document.getElementById('industry_code').value = document.getElementById('industry_type').textContent;
    
    companyForm.style.display = 'block';
  companyDataWrap.style.display = 'none';
  saveBtnEl.style.display = 'inline-block'; // 저장 버튼 표시
  cancelBtnEl.style.display = 'inline-block'; // 취소 버튼 표시
  editBtnEl.style.display = 'none'; // 기존 수정 버튼 숨기기
  
  
  })


  
  saveBtnEl.addEventListener('click', () =>{
    const updatedCompany = {
       comName: document.getElementById('company_name').value,
      comInfo: document.getElementById('company_info').value,
      comNum: document.getElementById('company_phone').value,
      comEmail: document.getElementById('company_email').value,
      comUrl: document.getElementById('company_url').value,
      comCreateYear: document.getElementById('company_year').value,
      comMem: document.getElementById('company_employee_count').value,
      industryType: document.getElementById('industry_code').value
    };

     axios.post('/ajax/company/company_management/edit', updatedCompany)
          .then(resp => {
                console.log(resp);

              document.getElementById('com_name').textContent = updatedCompany.comName;
              document.getElementById('com_info').textContent = updatedCompany.comInfo;
              document.getElementById('com_num').textContent = updatedCompany.comNum;
              document.getElementById('com_email').textContent = updatedCompany.comEmail;
              document.getElementById('com_url').textContent = updatedCompany.comUrl;
              document.getElementById('com_create_year').textContent = updatedCompany.comCreateYear;
              document.getElementById('com_mem').textContent = updatedCompany.comMem;
              document.getElementById('industry_type').textContent = updatedCompany.industryType;
    
              companyForm.style.display = 'none';
              editBtnEl.style.display = 'inline-block';
          })
  }).catch(err =>{
    console.error('에러 체킁: ', err);
  })


 

});
