/**
 * 
 */

 document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('companyForm');
    const industrySelect = document.getElementById('industryType');
    const cancelBtnEl = document.getElementById('cancelBtn');

    axios.get('/ajax/code/indu')
           .then(({ data }) => {
          data.forEach(c => {
            const o = document.createElement('option');
            o.value = c.induNo;
            o.textContent = c.induName;
            industrySelect.appendChild(o);
          });
        })
        .catch(console.error);


   axios.get('/ajax/company/company_management')
        .then(({ data: c }) => {
          ['comName','comCreateYear','comInfo','comNum','comEmail',
           'comUrl','comMem','industryType','comPayment']
          .forEach(key => {
            const el = document.getElementById(key);
            if(el) el.value = c[key];
          });
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
        axios.post('/ajax/company/company_management', payload)
          .then(() => {
            window.location.href = '/company/company_management';
          })
          .catch(err => {
            if(err.response?.status === 400) {
              const errors = err.response.data;
              Object.entries(errors).forEach(([f, msg]) => {
                const span = document.querySelector(`span[data-field="${f}"]`);
                if(span) span.textContent = Array.isArray(msg) ? msg.join(', ') : msg;
              });
            }
          });
      });
 });