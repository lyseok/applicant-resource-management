

/**
 * 
 */
document.addEventListener('DOMContentLoaded', function() {

    axios.get('/ajax/member/company_view/testCompany')
         .then(resp => {
            const company = resp.data;
            console.log(company);

             document.getElementById('industryType').textContent  = company.industryType || '-';
             document.getElementById('comMem').textContent        = company.comMem + '명';
             document.getElementById('comType').textContent       = company.comType || '-';
             document.getElementById('comCreateYear').textContent = company.comCreateYear || '-';
             document.getElementById('comCapital').textContent    = formatCapitalKRW(company.comCapital) || '-';
             document.getElementById('ceoName').textContent       = company.ceoName || '-';
             document.getElementById('comMainBiz').textContent    = company.comMainBiz || '-';
             document.getElementById('insuranceYn').textContent   = company.insuranceYn === 'Y' ? '국민연금, 건강보험, 고용보험, 산재보험' : '-';     
			 document.getElementById('comUrl').textContent    	  = company.comUrl || '-';
			 document.getElementById('comAddr').textContent    	  = company.comAddr || '-';

         }).catch(err => {
            console.error('회사정보 로드 실패', err);
    });
	
	
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
