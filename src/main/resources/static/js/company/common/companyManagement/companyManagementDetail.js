document.addEventListener('DOMContentLoaded', () => {
  const editBtnEl = document.getElementById('edit-btn'); // 수정 버튼


  // 회사 데이터 로드
  axios.get('/ajax/company/company_management')
      .then( resp => {
        const company = resp.data;
		console.log(company);
        document.getElementById('com_name').textContent = company.comName;
        document.getElementById('com_create_year').textContent = company.createYear;
        document.getElementById('com_email').textContent = company.comEmail;
        document.getElementById('com_num').textContent  = company.comNum;
        document.getElementById('com_url').textContent = company.comUrl;
        document.getElementById('com_info').textContent    = company.comInfo;
        document.getElementById('com_mem').textContent     = company.comMem + '명';
        document.getElementById('com_payment').textContent = company.comPayment ? '구독 중' : '구독 정보가 없습니다.';
        document.getElementById('industry_type').textContent = company.industryType;

  }).catch(err => {
    console.error('에러체킁', err);
  });

  editBtnEl.addEventListener('click', () =>{
    window.location.href = '/company/company_management/edit';
  })
 
});
