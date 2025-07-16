/**
 * 
 */

 document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('companyForm');
    const industrySelect = document.getElementById('industryType');
    const cancelBtnEl = document.getElementById('cancelBtn');

    axios.get('/ajax/code/indu')
           .then(({ data }) => {
          data.forEach(item => {

            const option = document.createElement('option');
            option.value = item.induNo;
            console.log("v", item.induNo);
            option.textContent = item.induName;
             console.log("v", item.induName);
            industrySelect.appendChild(option);
          });
          
          
              // 업종 코드가 잘 삽입되었는지 확인
            console.log('업종 select 태그:', industrySelect.innerHTML);
        })
        .catch(console.error);


    axios.get('/ajax/company/company_management')
      .then(({ data: company }) => {
        // 회사의 각 정보 로드
        ['comName', 'comCreateYear', 'comInfo', 'comNum', 'comEmail', 'comUrl', 'comMem', 'comPayment'].forEach(key => {
          const el = document.getElementById(key);
          if (el) el.value = company[key];
        });

        // 업종 코드 세팅
        const industrySelect = document.getElementById('industryType');
        const selectedOption = industrySelect.querySelector(`option[textContent='${company.industryType}']`);
        if (selectedOption) {
          selectedOption.selected = true; // 해당 업종을 선택
        } else {
          console.error('업종 코드에 맞는 옵션을 찾을 수 없습니다.', company.industryType);
        }
      })
      .catch(console.error);

   cancelBtnEl.addEventListener('click', () =>{history.back()});

   function formToJSON(form) {
        return Array.from(new FormData(form).entries())
          .reduce((o,[k,v]) => {
            o[k] = (k==='comMem') 
              ? (v ? parseInt(v,10) : null) 
              : v;
            return o;
          }, {});
    }

    form.addEventListener('submit', e =>{
         e.preventDefault();
        // 에러 초기화
        document.querySelectorAll('.text-danger').forEach(s=>s.textContent='');

        const payload = formToJSON(form);
        console.log(payload);
        alert('수정');
       axios.put('/ajax/company/company_management/edit', payload)
      .then(() => window.location.href = '/company/company_management')
      .catch(err => {
        if(err.response?.status===400 && err.response.data) {
          Object.entries(err.response.data).forEach(([field, messages]) => {
            const el = document.getElementById(field);
            if(!el) return;
            // 기존 span 제거
            const nxt = el.nextElementSibling;
            if(nxt && nxt.classList.contains('text-danger')) nxt.remove();
            // 새 span 삽입
            const span = document.createElement('span');
            span.className = 'text-danger small';
            span.textContent = Array.isArray(messages) ? messages.join(', ') : messages;
            el.insertAdjacentElement('afterend', span);
          });
        }
      });
  });
});