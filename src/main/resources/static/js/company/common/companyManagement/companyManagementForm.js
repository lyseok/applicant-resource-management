/**
 * 
 */

 document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('companyForm');
    const industrySelect = document.getElementById('industryType');
    const cancelBtnEl = document.getElementById('cancelBtn');
    const comTypeSelect = document.getElementById ('comType');
    const comSizeSelect = document.getElementById('comSize');
    const insuranceSelect = document.getElementById('insuranceYn');
    


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
          
          
          
        })
        .catch(console.error);



        axios.get('/ajax/code/cmncodegroup/COMT')
            .then(resp =>{
              const typeList = resp.data.cmnCodeList;
              console.log(typeList);

              typeList.forEach(type => {
                const option = document.createElement('option');
                option.value = type.codeDetailNo;
                option.textContent = type.codeName;
                comTypeSelect.appendChild(option);
              })
            }).catch(err =>{
              console.error(err);
        })

    

       axios.get('/ajax/code/cmncodegroup/SIZE')
            .then(resp =>{
              const sizeList = resp.data.cmnCodeList;
              console.log(sizeList);

               sizeList.forEach(size => {
                const option = document.createElement('option');
                option.value = size.codeDetailNo;
                option.textContent = size.codeName;
                comSizeSelect.appendChild(option);
              })
            }).catch(err =>{
              console.error(err);
        })


    axios.get('/ajax/company/company_management')
      .then(({ data: company }) => {
      
        ['comName', 'comCreateYear', 'comInfo', 'comNum', 'comEmail', 'comUrl', 'comMem', 'comPayment', 'comName', 'ceoName','comAddr', 'comCapital', 'comMainBiz'].forEach(key => {
          const el = document.getElementById(key);
           if (el) el.value = company[key] || '';
        });

       
        setTimeout(() => {
        if (company.industryType) industrySelect.value = company.industryType;
        if (company.comType) comTypeSelect.value = company.comType;
        if (company.comSize) comSizeSelect.value = company.comSize;
        if (company.insuranceYn) insuranceSelect.value = company.insuranceYn;
      }, 200); // 약간의 지연을 줘야 option 추가 후 value를 선택할 수 있음
    })
      .catch(console.error);

   cancelBtnEl.addEventListener('click', () =>{history.back()});

   function formToJSON(form) {
        return Array.from(new FormData(form).entries())
          .reduce((o,[k,v]) => {
           o[k] = (['comMem', 'comCapital'].includes(k))
              ? (v ? parseInt(v, 10) : null)
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