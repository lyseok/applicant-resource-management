/**
 * 
 */

 document.addEventListener("DOMContentLoaded", () => {
    const companyListEl = document.getElementById('company-list');
    const companyTitleEl = document.getElementById('company-title');
    const autoEl = document.getElementById('autocomplete-list');
    const formBtnEl = document.getElementById('review_form_btn');
    let selectedCareer = null;
    const careerListDiv = document.getElementById('careerList');
    const selectedCareerCard = document.getElementById('selectedCareerCard');
    const btnShowCareerList = document.getElementById('btnShowResumeList');


    axios.get('/ajax/member/company_review')
         .then(resp => {
          const companyList = resp.data;
          console.log(companyList);

          companyTitleEl.textContent = `${companyList.length}개 기업의 리뷰가 등록되어 있습니다.`;
		      companyListEl.innerHTML = '';
          companyList.forEach( c=>{
            const li = document.createElement('li');
            li.className = 'item';
            li.onclick = () => {
              location.href = `/member/company_review/detail?companyId=${c.userId}`;
            };
           li.innerHTML = `
                  <div class="info">
                  ${c.comLogo
                    ? `<img class="logo" src="${c.comLogo}" alt="${c.comName}"/>`
                    : `<div class="logo"></div>`}
                  <div class="text">
                    <div class="name">
                    ${c.comName}
                      <span style="color:var(--violet70);font-size:.8rem;">(채용중)</span>
                    </div>
                    <div class="meta">
                    ${c.induName || ''} | 사원수 ${c.comMem || 0}명
                    </div>
                  </div>
                  </div>
                  
                `;
                  companyListEl.append(li);
            });
    })



    axios.get('/ajax/member/company_review/my_career')
         .then(resp => {
            const resumes = resp.data;
            const careers = [];
            
            resumes.forEach(resume => {
                resume.careerList?.forEach(career => {
                  career.resumeName = resume.resumeName;
                  careers.push(career);
                  console.log(careers);
                });
            });
            if(careers.length === 0){
              careerListDiv.innerHTML = `<div class="text-secondary py-2">등록된 경력이 없습니다.</div>`
              careerListDiv.style.display = 'block';
              return;
            }

            careerListDiv.innerHTML = careers.map(career => `
                <div class="card mb-2 resume-card ${selectedCareer?.careerNo === career.careerNo ? 'selected-card' : ''}" data-career-no="${career.careerNo}">
                   <div class="card-body py-2 px-3">
                    <h6 class ="mb-1">${career.company.comName} (${career.jobCodeName || ''})</h6>
                      <p class="text-secondary" style="font-size:.96em;">
                        ${career.startWorkDate || ''} ~ ${career.retireDate || '재직중'}<br>
                        ${career.jobGradeCodeName || ''} / ${career.positionCodeName || ''} / ${career.careerYearName || ''}
                      </p>
                  </div>
                </div>
                `).join('');

            careerListDiv.querySelectorAll('.resume-card').forEach(card => {
              card.onclick = function () {
                  const careerNo = this.getAttribute('data-career-no');
                  selectedCareer = careers.find(c => c.careerNo === careerNo);
                  renderSelectedCareerCard();
                  selectedCareerCard.style.display = 'block';
                  careerListDiv.style.display = 'none';
              }
            })

         })

    function renderSelectedCareerCard(){
      const div =  document.getElementById('selectedCareerCard');
      if(!selectedCareer){
        div.innerHTML = '<span class="text-secondary">경력을 선택하세요</span>';
        return;
      }

       div.innerHTML = `
      <div class="card mb-0 selected-card">
        <div class="card-body py-2 px-3">
          <h6 class="mb-1">${selectedCareer.company?.comName || ''} (${selectedCareer.jobCodeName || ''})</h6>
          <p class="mb-0 text-secondary" style="font-size:.92em;">
            ${selectedCareer.startWorkDate || ''} ~ ${selectedCareer.retireDate || '재직중'}<br>
            ${selectedCareer.jobGradeCodeName || ''} / ${selectedCareer.positionCodeName || ''} / ${selectedCareer.careerYearName || ''}
          </p>
        </div>
      </div>
    `;
    }

    formBtnEl.addEventListener('click', () =>{
       const modal = new bootstrap.Modal(document.getElementById('careerModal'));
      modal.show();
    })

    btnShowCareerList.addEventListener('click', () => {
    careerListDiv.style.display = 'block';
    });

    document.getElementById('btnSaveCareer').addEventListener('click', () => {
      console.log(selectedCareer.careerNo);

      location.href = `/member/company_review/form?careerNo=${careerNo}`;
    })
 })