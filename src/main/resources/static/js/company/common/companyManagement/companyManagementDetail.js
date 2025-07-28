document.addEventListener('DOMContentLoaded', () => {
  const editBtnEl = document.getElementById('edit-btn'); // 수정 버튼


  // 회사 데이터 로드
  axios.get('/ajax/company/company_management')
      .then( resp => {
        const company = resp.data;
		console.log(company);
        document.getElementById('com_name').textContent = company.comName;
        document.getElementById('com_create_year').textContent = company.comCreateYear;
        document.getElementById('com_email').textContent = company.comEmail;
        document.getElementById('com_num').textContent  = company.comNum 
        document.getElementById('com_url').textContent = company.comUrl || '정보 없음';
        document.getElementById('com_info').textContent    = company.comInfo || '정보 없음';
        document.getElementById('com_mem').textContent     = company.comMem + '명';
        document.getElementById('com_payment').textContent = company.comPayment ? '구독 중' : '구독 정보가 없습니다.';
        document.getElementById('industry_type').textContent = company.industryType;
        document.getElementById('com_type').textContent = company.comType || '정보 없음';
        document.getElementById('com_size').textContent = company.comSize || '정보 없음';
        document.getElementById('insurance_Yn').textContent = company.insuranceYn === 'Y' ? '가입' : '미가입';
        document.getElementById('ceo_name').textContent = company.ceoName || '정보 없음';
        document.getElementById('com_addr').textContent = company.comAddr || '정보 없음';
        document.getElementById('com_capital').textContent = company.comCapital != null ? formatCapital(company.comCapital) : '정보 없음';
        document.getElementById('com_main_biz').textContent = company.comMainBiz || '정보 없음';
		document.getElementById('com_logo').src = company.comLogo;

  }).catch(err => {
    console.error('에러체킁', err);
  });

  editBtnEl.addEventListener('click', () =>{
    window.location.href = '/company/company_management/edit';
  })
 
 
  function formatCapital(amount) {
    amount = Number(amount);
    if (isNaN(amount) || amount === 0) return '면접 후 결정';
    if (amount < 10000) {
      return `${amount.toString()}만원`;
    }
    const eok = Math.floor(amount / 10000);
    const man = amount % 10000;
    return man > 0 ? `${eok}억 ${man.toString()}만원` : `${eok}억원`;
  }
});
