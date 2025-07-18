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
       document.getElementById('com_capital').textContent = company.comCapital != null ? formatCapitalKRW(company.comCapital) : '정보 없음';

        document.getElementById('com_main_biz').textContent = company.comMainBiz || '정보 없음';


  }).catch(err => {
    console.error('에러체킁', err);
  });

  editBtnEl.addEventListener('click', () =>{
    window.location.href = '/company/company_management/edit';
  })
 
 
 
 function formatCapitalKRW(amount) {
  if (amount == null || isNaN(amount)) return '정보 없음';

  const 조 = Math.floor(amount / 1_0000_0000_0000);
  const 억 = Math.floor((amount % 1_0000_0000_0000) / 1_0000_0000);
  const 만 = Math.floor((amount % 1_0000_0000) / 10_000);

  let result = '';
  if (조 > 0) result += `${조.toLocaleString()}조 `;
  if (억 > 0) result += `${억.toLocaleString()}억 `;
  if (조 === 0 && 억 === 0) result += `${만.toLocaleString()}만원`;

  return result.trim();
  
}
});
